import java.rmi.*;
import java.util.List;

interface ServicioLogear extends Remote
{
    public boolean crearUsuario (String nombre, String apellido, String dni, Double saldo, String contraseña) throws RemoteException;
    public Usuario buscarUsuarioPorDNI(String dni) throws RemoteException;
    public void leerUsuarios(String archivo) throws RemoteException;
    public void leerDniAdministradores(String archivo) throws RemoteException;
    public void modificarSaldo (String dni, double coste, String archivo_usuarios) throws RemoteException;

}