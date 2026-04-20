import java.io.Serializable;
import java.util.Date;

public class Opinion implements Serializable {

    private static final long serialVersionUID = 1L;

    private String refHabitacion;
    private String usuario;
    private String titulo;
    private String comentario;
    private int estrellas;
    private Date fecha;

    public Opinion(String refHabitacion, String usuario, String titulo, String comentario, int estrellas) {
        this.refHabitacion = refHabitacion;
        this.usuario = usuario;
        this.titulo = titulo;
        this.comentario = comentario;
        this.estrellas = estrellas;
        this.fecha = new Date();
    }

    // Getters
    public String getRefHabitacion() { return refHabitacion; }
    public String getUsuario() { return usuario; }
    public String getTitulo() { return titulo; }
    public String getComentario() { return comentario; }
    public int getEstrellas() { return estrellas; }
    public Date getFecha() { return fecha; }
}
