import java.util.*;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Date;


/*                  CLASE IMPLEMENTACIÓN SERVICIO RESEÑA
 *
   *     - Añadir reseña
   *     - Extraer ultima reseña desde fichero
 *
 */


class ServicioResenaImpl extends UnicastRemoteObject implements ServicioResena
{
    PrintWriter fd;
    ServicioResenaImpl() throws RemoteException{}

    public void anadirResena (Item item, String titulo, String resena, int valoracion, String usuario) throws RemoteException
    {

  try {
;
      Date fecha = new Date();
      fd= new PrintWriter(new FileWriter("Resenas/"+String.valueOf(item.getReferencia()) +" - "+ item.getNombre(),true));
      fd.println("Fecha de Reseña: "+fecha+"\n\nUsuario: "+usuario+"\nTítulo: "+titulo+"\nReseña: "+resena+"\nValoración: "+ String.valueOf(valoracion)+"\n\n");
      fd.flush();

  }
  catch (FileNotFoundException e) {
      System.err.println(e);
      System.exit(1);
  }

  catch (IOException e){

  }
    }

    public String[] extraerUltimaResena (Item item){

  String[] resultado = new String[]{"","",""};
  try {

        BufferedReader br = new BufferedReader(new FileReader("Resenas/"+String.valueOf(item.getReferencia()) +" - "+ item.getNombre()));
  String ultimaResena = "";
        String usuario = "";
    int valoracion_contador=0;
    int valoracion_aux=0;
            String linea;
            String ultimaLinea = null;

            // Leer el archivo línea por línea
            while ((linea = br.readLine()) != null) {
                // Buscar la línea que comienza con "Reseña:"
                if (linea.startsWith("Reseña:")) {
                    // Guardar la última reseña encontrada
                    ultimaResena = linea.substring("Reseña:".length()).trim();

                    // Reiniciar la variable de usuario
                } else if (linea.startsWith("Usuario:")) {
                    // Guardar la última línea que comienza con "Usuario:"
                    usuario = linea.substring("Usuario:".length()).trim();

                }

    else if (linea.startsWith("Valoración:")) {
                    // Guardar la última línea que comienza con "Usuario:"
        valoracion_aux  = valoracion_aux + Integer.parseInt( linea.substring("Valoracion: ".length()).trim());
        valoracion_contador= valoracion_contador+1;
            }
      }

      resultado = new String[]{ultimaResena, usuario,String.valueOf(Math.round(valoracion_aux/valoracion_contador))};


  } catch (IOException e) {
  } catch (Exception ex) {
      ex.printStackTrace();
  }



  return resultado;

    }

}
