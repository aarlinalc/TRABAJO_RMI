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
            System.err.println("Error de conexión RMI");
            e.printStackTrace();
        }
    }

    // -------- MENÚ PRINCIPAL --------
    private static boolean menuPrincipal(Scanner ent) {
        System.out.println("\n=================================");
        System.out.println("      BIENVENIDO A ROOM RMI      ");
        System.out.println("=================================");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrarse (Nuevo Cliente)");
        System.out.println("3. Salir del sistema");
        System.out.print("Elige una opción: ");

        switch (ent.nextLine()) {
            case "1": iniciarSesion(ent); break;
            case "2": ejecutarRegistro(ent); break;
            case "3": return true;
            default: System.out.println("[!] Opción inválida.");
        }
        return false;
    }

    private static void iniciarSesion(Scanner ent) {
        try {
            System.out.print("Introduce tu DNI: ");
            String dni = ent.nextLine();
            System.out.print("Introduce tu Contraseña: ");
            String pass = ent.nextLine();

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
        } catch (Exception e) {
            System.out.println("[-] Error en registro.");
        }
    }

    // -------- MENÚ USUARIO --------
    private static boolean menuUsuario(Scanner ent) {
        System.out.println("\n--- PANEL DE CLIENTE ---");
        System.out.println("Usuario: " + usuarioLogeado.getNombre() + " | Saldo: " + usuarioLogeado.getSaldo() + "€");
        System.out.println("1. Ver Hoteles Disponibles");
        System.out.println("2. Reservar Habitación");
        System.out.println("3. Nueva Opinión");
        System.out.println("4. Ver Opiniones");
        System.out.println("5. Cerrar Sesión");
        System.out.print("Elige una opción: ");

        switch (ent.nextLine()) {
            case "1": listarHabitaciones(); break;
            case "2": ejecutarReserva(ent); break;
            case "3": publicarOpinion(ent); break;
            case "4": verOpiniones(ent); break;
            case "5": usuarioLogeado = null; break;
            default: System.out.println("[!] Opción inválida.");
        }
        return false;
    }

    private static void listarHabitaciones() {
        try {
            List<Habitacion> habs = srv_inventario.buscarDisponibilidad("", "", 9999);
            System.out.println("\n--- HOTELES Y HABITACIONES ---");

            if (habs.isEmpty())
                System.out.println("No hay habitaciones disponibles en este momento.");

            for (Habitacion h : habs) {
                System.out.println("Ref: [" + h.getReferencia() + "] | " +
                        h.getHotel() + " (" + h.getTipo() + ") | " +
                        h.getPrecioPorNoche() + "€/noche | Plazas libres: " +
                        h.getPlazasDisponibles());
            }
        } catch (RemoteException e) {
            System.out.println("[-] Error obteniendo el inventario del servidor.");
        }
    }

    private static void ejecutarReserva(Scanner ent) {
        try {
            System.out.print("\nIntroduzca la Referencia de la habitación a reservar: ");
            String ref = ent.nextLine();

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

            System.out.print("Introduzca el número de noches: ");
            int noches = Integer.parseInt(ent.nextLine());

            double coste = habSeleccionada.getPrecioPorNoche() * noches;

            System.out.println("-> Coste total: " + coste + "€");

            System.out.print("¿Confirmar? (S/N): ");
            if (ent.nextLine().equalsIgnoreCase("S")) {
                if (srv_usuarios.procesarReserva(usuarioLogeado.getDni(), ref, coste)) {
                    System.out.println("[+] RESERVA CONFIRMADA.");
                    usuarioLogeado.setSaldo(usuarioLogeado.getSaldo() - coste);
                } else {
                    System.out.println("[-] ERROR EN LA RESERVA.");
                }
            }

        } catch (Exception e) {
            System.out.println("[-] Error en la reserva.");
        }
    }

    // -------- OPINIONES (OBJETOS RMI) --------

    private static void publicarOpinion(Scanner ent) {
        try {
            System.out.print("Referencia habitación: ");
            String ref = ent.nextLine();

            System.out.print("Título: ");
            String titulo = ent.nextLine();

            System.out.print("Comentario: ");
            String comentario = ent.nextLine();

            System.out.print("Valoración (1-5): ");
            int estrellas = Integer.parseInt(ent.nextLine());

            Opinion op = new Opinion(ref, usuarioLogeado.getDni(), titulo, comentario, estrellas);

            srv_opiniones.publicarOpinion(op);

            System.out.println("[+] Opinión publicada correctamente.");

        } catch (Exception e) {
            System.out.println("[-] Error publicando opinión.");
        }
    }

    private static void verOpiniones(Scanner ent) {
        try {
            System.out.print("Referencia habitación: ");
            String ref = ent.nextLine();

            List<Opinion> lista = srv_opiniones.obtenerOpiniones(ref);

            if (lista.isEmpty()) {
                System.out.println("No hay opiniones.");
                return;
            }

            int suma = 0;

            for (Opinion op : lista) {
                System.out.println("Usuario: " + op.getUsuario());
                System.out.println("Título: " + op.getTitulo());
                System.out.println("Comentario: " + op.getComentario());
                System.out.println("Valoración: " + op.getEstrellas());
                System.out.println("Fecha: " + op.getFecha());
                System.out.println("---");

                suma += op.getEstrellas();
            }

            System.out.println("Media: " + (suma / lista.size()));

        } catch (RemoteException e) {
            System.out.println("[-] Error obteniendo opiniones.");
        }
    }

    // -------- ADMIN --------
    private static boolean menuAdmin(Scanner ent) {
        System.out.println("\n--- PANEL DE ADMINISTRACIÓN ---");
        System.out.println("Usuario: " + usuarioLogeado.getNombre() + " (ADMIN)");
        System.out.println("1. Ver Inventario Completo");
        System.out.println("2. Añadir Nueva Habitación al Sistema");
        System.out.println("3. Cerrar Sesión");
        System.out.print("Elige una opción: ");

        switch (ent.nextLine()) {
            case "1": listarHabitaciones(); break;
            case "2": ejecutarCreacionHabitacion(ent); break;
            case "3": usuarioLogeado = null; break;
            default: System.out.println("[!] Opción inválida.");
        }
        return false;
    }

    private static void ejecutarCreacionHabitacion(Scanner ent) {
        try {
            System.out.print("Referencia: "); String ref = ent.nextLine();
            System.out.print("Hotel: "); String hotel = ent.nextLine();
            System.out.print("Tipo: "); String tipo = ent.nextLine();
            System.out.print("Plazas: "); int plazas = Integer.parseInt(ent.nextLine());
            System.out.print("Precio: "); double precio = Double.parseDouble(ent.nextLine());

            srv_inventario.registrarHabitacion(new Habitacion(ref, hotel, tipo, plazas, precio));

            System.out.println("[+] Habitación añadida correctamente.");

        } catch (Exception e) {
            System.out.println("[-] Error creando habitación.");
        }
    }
}
