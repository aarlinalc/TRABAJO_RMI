import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class Cliente {

    private static Usuario usuarioLogeado = null;
    private static ServicioGestionUsuarios srv_usuarios;
    private static ServicioInventarioHabitaciones srv_inventario;
    private static ServicioOpiniones srv_opiniones;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Uso: java Cliente <hostregistro> <puertoregistro>");
            return;
        }

        try {
            String baseURI = "//" + args[0] + ":" + args[1] + "/";
            srv_usuarios = (ServicioGestionUsuarios) Naming.lookup(baseURI + "ServicioGestionUsuarios");
            srv_inventario = (ServicioInventarioHabitaciones) Naming.lookup(baseURI + "ServicioInventarioHabitaciones");
            srv_opiniones = (ServicioOpiniones) Naming.lookup(baseURI + "ServicioOpiniones");

            Scanner ent = new Scanner(System.in);
            boolean salir = false;

            while (!salir) {
                if (usuarioLogeado == null) {
                    salir = menuPrincipal(ent);
                } else if (usuarioLogeado.getTipo()) { 
                    salir = menuAdmin(ent);
                } else {
                    salir = menuUsuario(ent);
                }
            }
            
            System.out.println("Saliendo de RoomRMI. ¡Hasta pronto!");
            System.exit(0);

        } catch (Exception e) {
            System.err.println("Fallo de conexión RMI. ¿Está el servidor encendido?");
            e.printStackTrace();
        }
    }

    // --- 1. MENÚ PRINCIPAL ---
    private static boolean menuPrincipal(Scanner ent) {
        System.out.println("\n=================================");
        System.out.println("      BIENVENIDO A ROOM RMI      ");
        System.out.println("=================================");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrarse (Nuevo Cliente)");
        System.out.println("3. Salir del sistema");
        System.out.print("Elige una opción: ");
        
        String opcion = ent.nextLine();
        switch (opcion) {
            case "1":
                iniciarSesion(ent);
                break;
            case "2":
                ejecutarRegistro(ent);
                break;
            case "3": 
                return true;
            default: 
                System.out.println("[!] Opción inválida.");
        }
        return false;
    }

    private static void iniciarSesion(Scanner ent) {
        System.out.print("Introduce tu DNI: ");
        String dni = ent.nextLine();
        System.out.print("Introduce tu Contraseña: ");
        String pass = ent.nextLine();

        try {
            Usuario u = srv_usuarios.autenticarUsuario(dni, pass);
            if (u != null) {
                usuarioLogeado = u;
                System.out.println("[+] Autenticación correcta. Bienvenido, " + u.getNombre());
            } else {
                System.out.println("[-] Credenciales erróneas o usuario inexistente.");
            }
        } catch (RemoteException e) {
            System.out.println("[-] Error de red contactando al servidor.");
        }
    }

    private static void ejecutarRegistro(Scanner ent) {
        System.out.println("\n--- REGISTRO DE NUEVO CLIENTE ---");
        try {
            System.out.print("Nombre: "); String nombre = ent.nextLine();
            System.out.print("Apellido: "); String apellido = ent.nextLine();
            System.out.print("DNI: "); String dni = ent.nextLine();
            System.out.print("Contraseña: "); String pass = ent.nextLine();
            System.out.print("Saldo Inicial (€): "); double saldo = Double.parseDouble(ent.nextLine());

            boolean registrado = srv_usuarios.registrarCliente(nombre, apellido, dni, saldo, pass);
            if (registrado) {
                System.out.println("[+] Registro exitoso. Ya puedes iniciar sesión.");
            } else {
                System.out.println("[-] Error: Ya existe un usuario con ese DNI.");
            }
        } catch (NumberFormatException e) {
            System.out.println("[-] Error: Formato de saldo incorrecto.");
        } catch (RemoteException e) {
            System.out.println("[-] Error de red contactando al servidor.");
        }
    }

    // --- 2. MENÚ CLIENTE ---
    private static boolean menuUsuario(Scanner ent) {
        System.out.println("\n--- PANEL DE CLIENTE ---");
        System.out.println("Usuario: " + usuarioLogeado.getNombre() + " | Saldo: " + usuarioLogeado.getSaldo() + "€");
        System.out.println("1. Ver Hoteles Disponibles");
        System.out.println("2. Reservar Habitación");
        System.out.println("3. Cerrar Sesión");
        System.out.print("Elige una opción: ");
        
        String opcion = ent.nextLine();
        switch (opcion) {
            case "1":
                listarHabitaciones();
                break;
            case "2":
                ejecutarReserva(ent);
                break;
            case "3":
                usuarioLogeado = null;
                break;
            default: 
                System.out.println("[!] Opción inválida.");
        }
        return false;
    }

    private static void listarHabitaciones() {
        try {
            List<Habitacion> habs = srv_inventario.buscarDisponibilidad("", "", 9999);
            System.out.println("\n--- HOTELES Y HABITACIONES ---");
            if (habs.isEmpty()) System.out.println("No hay habitaciones disponibles en este momento.");
            for (Habitacion h : habs) {
                System.out.println("Ref: [" + h.getReferencia() + "] | " + h.getHotel() + " (" + h.getTipo() + ") | " + h.getPrecioPorNoche() + "€/noche | Plazas libres: " + h.getPlazasDisponibles());
            }
        } catch (RemoteException e) {
            System.out.println("[-] Error obteniendo el inventario del servidor.");
        }
    }

    private static void ejecutarReserva(Scanner ent) {
        System.out.print("\nIntroduzca la Referencia de la habitación a reservar: ");
        String ref = ent.nextLine();

        try {
            // 1. Buscamos la habitación para obtener su precio real
            List<Habitacion> habs = srv_inventario.buscarDisponibilidad("", "", 9999);
            Habitacion habSeleccionada = null;
            for (Habitacion h : habs) {
                if (h.getReferencia().equalsIgnoreCase(ref)) {
                    habSeleccionada = h;
                    break;
                }
            }

            if (habSeleccionada == null) {
                System.out.println("[-] Referencia no encontrada o sin disponibilidad.");
                return;
            }

            // 2. Pedimos noches y calculamos el precio internamente
            System.out.print("Introduzca el número de noches: ");
            int noches = Integer.parseInt(ent.nextLine());
            if (noches <= 0) throw new NumberFormatException();

            if (noches > habSeleccionada.getPlazasDisponibles()) {
                System.out.println("[-] No hay suficientes plazas/noches disponibles.");
                return;
            }

            double costeCalculado = habSeleccionada.getPrecioPorNoche() * noches;
            System.out.println("-> Coste total de la reserva: " + costeCalculado + "€");
            
            // 3. Confirmación
            System.out.print("¿Desea confirmar el pago? (S/N): ");
            if (ent.nextLine().equalsIgnoreCase("S")) {
                boolean exito = srv_usuarios.procesarReserva(usuarioLogeado.getDni(), ref, costeCalculado);
                if (exito) {
                    System.out.println("[+] RESERVA CONFIRMADA. Se ha descontado de tu saldo.");
                    // Actualizamos el saldo local para reflejar el cambio en la vista sin tener que reloguear
                    usuarioLogeado.setSaldo(usuarioLogeado.getSaldo() - costeCalculado); 
                } else {
                    System.out.println("[-] LA RESERVA HA FALLADO. Saldo insuficiente o concurrencia de red.");
                }
            } else {
                System.out.println("Reserva cancelada.");
            }

        } catch (NumberFormatException e) {
            System.out.println("[-] Error: Introduzca un número de noches válido.");
        } catch (RemoteException e) {
            System.out.println("[-] Error procesando la reserva con el servidor.");
        }
    }

    // --- 3. MENÚ ADMINISTRADOR ---
    private static boolean menuAdmin(Scanner ent) {
        System.out.println("\n--- PANEL DE ADMINISTRACIÓN ---");
        System.out.println("Usuario: " + usuarioLogeado.getNombre() + " (ADMIN)");
        System.out.println("1. Ver Inventario Completo");
        System.out.println("2. Añadir Nueva Habitación al Sistema");
        System.out.println("3. Cerrar Sesión");
        System.out.print("Elige una opción: ");
        
        String opcion = ent.nextLine();
        switch (opcion) {
            case "1":
                listarHabitaciones();
                break;
            case "2":
                ejecutarCreacionHabitacion(ent);
                break;
            case "3":
                usuarioLogeado = null;
                break;
            default:
                System.out.println("[!] Opción inválida.");
        }
        return false;
    }

    private static void ejecutarCreacionHabitacion(Scanner ent) {
        System.out.println("\n--- ALTA DE NUEVA HABITACIÓN ---");
        try {
            System.out.print("Referencia (Ej. REF03): "); String ref = ent.nextLine();
            System.out.print("Nombre del Hotel: "); String hotel = ent.nextLine();
            System.out.print("Tipo (Doble, Suite, etc): "); String tipo = ent.nextLine();
            System.out.print("Plazas Disponibles: "); int plazas = Integer.parseInt(ent.nextLine());
            System.out.print("Precio por noche (€): "); double precio = Double.parseDouble(ent.nextLine());

            Habitacion nuevaHab = new Habitacion(ref, hotel, tipo, plazas, precio);
            srv_inventario.registrarHabitacion(nuevaHab);
            
            System.out.println("[+] Habitación añadida al inventario global correctamente.");
            
        } catch (NumberFormatException e) {
            System.out.println("[-] Error: Formato numérico incorrecto en plazas o precio.");
        } catch (RemoteException e) {
            System.out.println("[-] Error de red contactando al servidor.");
        }
    }
}
