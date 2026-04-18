import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServicioInventarioHabitaciones extends Remote {
    List<Habitacion> buscarDisponibilidad(String hotel, String tipo, double precioMax) throws RemoteException;
    void registrarHabitacion(Habitacion habitacion) throws RemoteException;
    void modificarHabitacion(Habitacion habitacion) throws RemoteException;
}
