import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioOpiniones extends Remote {
    void publicarOpinion(String referenciaHabitacion, String titulo, String comentario, int estrellas, String dniUsuario) throws RemoteException;
    String[] obtenerUltimaOpinion(String referenciaHabitacion) throws RemoteException;
}
