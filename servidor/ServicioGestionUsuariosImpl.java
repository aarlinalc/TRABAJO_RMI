import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ServicioGestionUsuariosImpl extends UnicastRemoteObject implements ServicioGestionUsuarios {

    private List<Usuario> listaUsuarios;
    private final String ARCHIVO_USUARIOS = "usuarios.txt";
    private final String ARCHIVO_ADMINS = "administradores.txt";
    private ServicioInventarioHabitaciones srv_inventario;

    public ServicioGestionUsuariosImpl(ServicioInventarioHabitaciones inventario) throws RemoteException {
        super();
        this.srv_inventario = inventario;
        this.listaUsuarios = new ArrayList<>();
    }

    private synchronized void cargarUsuarios() {
        listaUsuarios.clear();
        List<String> dnisAdmins = new ArrayList<>();
        
        // 1. Cargar DNI de administradores
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_ADMINS))) {
            String linea;
            while ((linea = br.readLine()) != null) dnisAdmins.add(linea.trim());
        } catch (Exception e) {}

        // 2. Cargar Usuarios
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(",");
                if (p.length == 5) {
                    boolean esAdmin = dnisAdmins.contains(p[2].trim());
                    listaUsuarios.add(new Usuario(p[0].trim(), p[1].trim(), p[2].trim(), p[4].trim(), Double.parseDouble(p[3].trim()), esAdmin));
                }
            }
        } catch (Exception e) {}
    }

    private synchronized void guardarUsuarios() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_USUARIOS))) {
            for (Usuario u : listaUsuarios) {
                writer.write(u.getNombre() + "," + u.getApellido() + "," + u.getDni() + "," + u.getSaldo() + "," + u.getContrasena() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error guardando usuarios: " + e.getMessage());
        }
    }

    @Override
    public synchronized Usuario autenticarUsuario(String dni, String password) throws RemoteException {
        cargarUsuarios();
        for (Usuario u : listaUsuarios) {
            if (u.getDni().equals(dni) && u.getContrasena().equals(password)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public synchronized boolean registrarCliente(String nombre, String apellido, String dni, double saldo, String password) throws RemoteException {
        cargarUsuarios();
        for (Usuario u : listaUsuarios) {
            if (u.getDni().equals(dni)) return false; // DNI duplicado
        }
        listaUsuarios.add(new Usuario(nombre, apellido, dni, password, saldo, false));
        guardarUsuarios();
        return true;
    }

    // EL NÚCLEO DEL PROYECTO: TRANSACCIÓN SEGURA
    @Override
    public synchronized boolean procesarReserva(String dniCliente, String refHabitacion, double costeTotal) throws RemoteException {
        cargarUsuarios(); // Estado fresco de los saldos
        
        Usuario clienteObj = null;
        for (Usuario u : listaUsuarios) {
            if (u.getDni().equals(dniCliente)) {
                clienteObj = u; break;
            }
        }

        if (clienteObj == null || clienteObj.getSaldo() < costeTotal) return false;

        // Verificar inventario directamente a través del otro servicio
        List<Habitacion> habs = srv_inventario.buscarDisponibilidad("", "", 9999);
        Habitacion habTarget = null;
        for (Habitacion h : habs) {
            if (h.getReferencia().equals(refHabitacion) && h.getPlazasDisponibles() > 0) {
                habTarget = h; break;
            }
        }

        if (habTarget == null) return false; // Sin stock

        // Ejecutar la transacción
        clienteObj.setSaldo(Math.round((clienteObj.getSaldo() - costeTotal) * 100.0) / 100.0);
        habTarget.setPlazasDisponibles(habTarget.getPlazasDisponibles() - 1);

        guardarUsuarios();
        srv_inventario.modificarHabitacion(habTarget);
        
        System.out.println("Transacción bloqueada superada: Reserva procesada para " + dniCliente);
        return true;
    }
}
