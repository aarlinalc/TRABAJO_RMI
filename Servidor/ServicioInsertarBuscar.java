import java.rmi.*;
import java.util.List;

interface ServicioInsertarBuscar extends Remote {

    void insertarItem (Item item) throws RemoteException;
    public List<Item> filtrar(String nombre, String tipo, int rango_precio) throws RemoteException;
    public void leerItems(String nombreArchivo) throws RemoteException;
    public void actualizarItems (List<Item> listaItems) throws RemoteException;
	public void actualizarItem(Item item) throws RemoteException;
}
