import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
import java.util.*;

public class ServicioOpinionesImpl extends UnicastRemoteObject implements ServicioOpiniones {

    private List<Opinion> listaOpiniones;
    private final String ARCHIVO_OPINIONES = "opiniones.txt";

    public ServicioOpinionesImpl() throws RemoteException {
        super();
        listaOpiniones = new ArrayList<>();
        cargarOpiniones();
    }

    // =========================
    // PUBLICAR OPINIÓN
    // =========================
    @Override
    public synchronized void publicarOpinion(Opinion op) throws RemoteException {
        listaOpiniones.add(op);
        guardarOpiniones();
    }

    // =========================
    // OBTENER OPINIONES POR HABITACIÓN
    // =========================
    @Override
    public synchronized List<Opinion> obtenerOpiniones(String refHabitacion) throws RemoteException {

        List<Opinion> resultado = new ArrayList<>();

        for (Opinion op : listaOpiniones) {
            if (op.getRefHabitacion().equalsIgnoreCase(refHabitacion)) {
                resultado.add(op);
            }
        }

        return resultado;
    }

    // =========================
    // CARGAR DESDE ARCHIVO TXT
    // Formato:
    // ref,usuario,titulo,comentario,estrellas
    // =========================
    private void cargarOpiniones() {

        File f = new File(ARCHIVO_OPINIONES);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(",");

                if (partes.length == 5) {
                    Opinion op = new Opinion(
                            partes[0], // refHabitacion
                            partes[1], // usuario
                            partes[2], // titulo
                            partes[3], // comentario
                            Integer.parseInt(partes[4]) // estrellas
                    );

                    listaOpiniones.add(op);
                }
            }

        } catch (Exception e) {
            System.err.println("Error cargando opiniones: " + e.getMessage());
        }
    }

    // =========================
    // GUARDAR EN ARCHIVO TXT
    // =========================
    private void guardarOpiniones() {

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_OPINIONES))) {

            for (Opinion op : listaOpiniones) {
                pw.println(
                        op.getRefHabitacion() + "," +
                        op.getUsuario() + "," +
                        op.getTitulo() + "," +
                        op.getComentario() + "," +
                        op.getEstrellas()
                );
            }

        } catch (IOException e) {
            System.err.println("Error guardando opiniones: " + e.getMessage());
        }
    }
}
