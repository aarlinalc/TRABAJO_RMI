import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {
    public static void main(String[] args) {
      

        try {
            // Se levanta el registro automáticamente en el puerto indicado por argumento o 1099 por defecto
            int puerto = (args.length > 0) ? Integer.parseInt(args[0]) : 1099;
            LocateRegistry.createRegistry(puerto);
            System.out.println("Registro RMI iniciado en el puerto " + puerto);

            // 1. Instanciamos los servicios
            ServicioInventarioHabitacionesImpl srv_inventario = new ServicioInventarioHabitacionesImpl();
            ServicioOpinionesImpl srv_opiniones = new ServicioOpinionesImpl();
            
            // Inyectamos el inventario en el gestor de usuarios para las transacciones conjuntas
            ServicioGestionUsuariosImpl srv_usuarios = new ServicioGestionUsuariosImpl(srv_inventario);

            // 2. Publicamos los servicios
            Naming.rebind("rmi://localhost:" + puerto + "/ServicioInventarioHabitaciones", srv_inventario);
            Naming.rebind("rmi://localhost:" + puerto + "/ServicioOpiniones", srv_opiniones);
            Naming.rebind("rmi://localhost:" + puerto + "/ServicioGestionUsuarios", srv_usuarios);

            System.out.println("Servidor RoomRMI [ACTIVO] y esperando conexiones...");

        } catch (Exception e) {
            System.err.println("Excepción crítica en el arranque del servidor:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
