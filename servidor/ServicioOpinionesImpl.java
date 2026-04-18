import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class ServicioOpinionesImpl extends UnicastRemoteObject implements ServicioOpiniones {

    public ServicioOpinionesImpl() throws RemoteException {
        super();
        File dir = new File("Opiniones");
        if (!dir.exists()) dir.mkdir();
    }

    @Override
    public synchronized void publicarOpinion(String refHabitacion, String titulo, String comentario, int estrellas, String dniUsuario) throws RemoteException {
        try (PrintWriter fd = new PrintWriter(new FileWriter("Opiniones/" + refHabitacion + ".txt", true))) {
            fd.println("Fecha: " + new Date());
            fd.println("Usuario: " + dniUsuario);
            fd.println("Título: " + titulo);
            fd.println("Reseña: " + comentario);
            fd.println("Valoración: " + estrellas);
            fd.println("---");
        } catch (IOException e) {
            System.err.println("Error escribiendo reseña: " + e.getMessage());
        }
    }

    @Override
    public synchronized String[] obtenerUltimaOpinion(String refHabitacion) throws RemoteException {
        String[] resultado = new String[]{"Sin opiniones", "N/A", "0"};
        File f = new File("Opiniones/" + refHabitacion + ".txt");
        if (!f.exists()) return resultado;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            String ultimaResena = "", usuario = "";
            int sumaValoracion = 0, contador = 0;

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Reseña:")) ultimaResena = linea.substring(7).trim();
                else if (linea.startsWith("Usuario:")) usuario = linea.substring(8).trim();
                else if (linea.startsWith("Valoración:")) {
                    sumaValoracion += Integer.parseInt(linea.substring(11).trim());
                    contador++;
                }
            }
            if (contador > 0) {
                resultado[0] = ultimaResena;
                resultado[1] = usuario;
                resultado[2] = String.valueOf(Math.round((float) sumaValoracion / contador));
            }
        } catch (Exception e) {
            System.err.println("Error leyendo reseña: " + e.getMessage());
        }
        return resultado;
    }
}
