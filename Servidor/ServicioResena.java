import java.rmi.*;

interface ServicioResena extends Remote
{
    void anadirResena (Item item, String titulo, String resena, int valoracion, String usuario) throws RemoteException;

    String[] extraerUltimaResena (Item item) throws RemoteException;
}
