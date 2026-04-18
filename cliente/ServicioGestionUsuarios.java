import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioGestionUsuarios extends Remote {
    Usuario autenticarUsuario(String dni, String password) throws RemoteException;
    boolean registrarCliente(String nombre, String apellido, String dni, double saldoInicial, String password) throws RemoteException;
    
    // El método transaccional crítico
    boolean procesarReserva(String dniCliente, String referenciaHabitacion, double costeTotal) throws RemoteException;
}
