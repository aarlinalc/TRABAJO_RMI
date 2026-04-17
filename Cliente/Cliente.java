import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.rmi.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import javax.swing.table.TableRowSorter;


/*             CLASE CLIENTE
 *
 *     - Inicializa la Interfaz Principal
 */
class Cliente
{
    public static void main(String args[])
    {
        Cliente cliente = new Cliente();
        cliente.llamada(args);
    }

    public void llamada(String args[])
    {
        if (args.length != 2)
      {
    System.err.println("Uso: Cliente hostregistro puertoregistro");
    return;
      }

        if (System.getSecurityManager() == null)
      {
    System.setSecurityManager(new SecurityManager());
      }

        try {
      // INICIALIZACION DE LA INTERFAZ DE INICIO DE SESIÓN
            InterfazPrincipal app = new InterfazPrincipal(args);
            app.empezar();

        }  catch (Exception e) {
            System.err.println("Excepción en el cliente:");
            e.printStackTrace();
        }
    }
}
