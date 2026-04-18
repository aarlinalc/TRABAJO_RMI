import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ServicioInventarioHabitacionesImpl extends UnicastRemoteObject implements ServicioInventarioHabitaciones {
    
    private List<Habitacion> listaHabitaciones;
    private final String ARCHIVO_HABITACIONES = "habitaciones.txt";

    public ServicioInventarioHabitacionesImpl() throws RemoteException {
        super();
        listaHabitaciones = new ArrayList<>();
        cargarInventario();
    }

    // Sincronizado: Nadie lee mientras otro escribe
    private synchronized void cargarInventario() {
        listaHabitaciones.clear();
        File f = new File(ARCHIVO_HABITACIONES);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split(",");
                if (p.length == 5) {
                    listaHabitaciones.add(new Habitacion(p[0].trim(), p[1].trim(), p[2].trim(), Integer.parseInt(p[3].trim()), Double.parseDouble(p[4].trim())));
                }
            }
        } catch (Exception e) {
            System.err.println("Error leyendo inventario: " + e.getMessage());
        }
    }

    // Sincronizado: Exclusión mutua total para escribir
    public synchronized void guardarInventario() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_HABITACIONES))) {
            for (Habitacion h : listaHabitaciones) {
                writer.write(h.getReferencia() + "," + h.getHotel() + "," + h.getTipo() + "," + h.getPlazasDisponibles() + "," + h.getPrecioPorNoche() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error guardando inventario: " + e.getMessage());
        }
    }

    @Override
    public List<Habitacion> buscarDisponibilidad(String hotel, String tipo, double precioMax) throws RemoteException {
        cargarInventario(); // Refrescar datos en vivo
        List<Habitacion> resultados = new ArrayList<>();
        for (Habitacion h : listaHabitaciones) {
            if (h.getHotel().toLowerCase().contains(hotel.toLowerCase()) && 
                h.getTipo().toLowerCase().contains(tipo.toLowerCase()) && 
                h.getPrecioPorNoche() <= precioMax &&
                h.getPlazasDisponibles() > 0) {
                resultados.add(h);
            }
        }
        return resultados;
    }

    @Override
    public synchronized void registrarHabitacion(Habitacion h) throws RemoteException {
        cargarInventario();
        listaHabitaciones.add(h);
        guardarInventario();
    }

    @Override
    public synchronized void modificarHabitacion(Habitacion h) throws RemoteException {
        cargarInventario();
        for (int i = 0; i < listaHabitaciones.size(); i++) {
            if (listaHabitaciones.get(i).getReferencia().equals(h.getReferencia())) {
                listaHabitaciones.set(i, h);
                break;
            }
        }
        guardarInventario();
    }
}
