import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class Servidor
{
    static public void main (String args[])
    {
  if (args.length!=1)
      {
    System.err.println("Uso: Servidor numPuertoRegistro");
    return;
      }

  if (System.getSecurityManager() == null)
      {
    System.setSecurityManager(new RMISecurityManager());
      }

  try
      {
        // Creación de los servicios y asociación en el registro
        
    ServicioResenaImpl srv_resena = new ServicioResenaImpl();
    ServicioLogearImpl srv_logear = new ServicioLogearImpl();
    ServicioInsertarBuscarImpl srv_insertar_buscar = new ServicioInsertarBuscarImpl();
    Naming.rebind("rmi://localhost:" + args[0] + "/ServicioResena", srv_resena);
    Naming.rebind("rmi://localhost:" + args[0] + "/ServicioLogear", srv_logear);
    Naming.rebind("rmi://localhost:" + args[0] + "/ServicioInsertarBuscar", srv_insertar_buscar);
      }
  catch (RemoteException e)
      {
    System.err.println("Error de comunicacion: " + e.toString());
    System.exit(1);
      }
  catch (Exception e)
      {
    System.err.println("Excepcion en ServidorLogear:");
    e.printStackTrace();
    System.exit(1);
      }
    }
}
