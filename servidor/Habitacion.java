import java.io.Serializable;

public class Habitacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String referencia;
    private String hotel;
    private String tipo;
    private int plazasDisponibles;
    private double precioPorNoche;

    public Habitacion(String referencia, String hotel, String tipo, int plazasDisponibles, double precioPorNoche) {
        this.referencia = referencia;
        this.hotel = hotel;
        this.tipo = tipo;
        this.plazasDisponibles = plazasDisponibles;
        this.precioPorNoche = precioPorNoche;
    }

    public String getReferencia() { return referencia; }
    public String getHotel() { return hotel; }
    public String getTipo() { return tipo; }
    public int getPlazasDisponibles() { return plazasDisponibles; }
    public double getPrecioPorNoche() { return precioPorNoche; }
    
    public void setPlazasDisponibles(int plazasDisponibles) { this.plazasDisponibles = plazasDisponibles; }
}
