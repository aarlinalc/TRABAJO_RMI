import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServicioOpiniones extends Remote {

    void publicarOpinion(Opinion op) throws RemoteException;

    List<Opinion> obtenerOpiniones(String refHabitacion) throws RemoteException;
}
