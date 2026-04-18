import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private String apellido;
    private String dni;
    private String contrasena;
    private double saldo;
    private boolean esAdmin;

    public Usuario(String nombre, String apellido, String dni, String contrasena, double saldo, boolean esAdmin) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.contrasena = contrasena;
        this.saldo = saldo;
        this.esAdmin = esAdmin;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDni() { return dni; }
    public String getContrasena() { return contrasena; }
    public double getSaldo() { return saldo; }
    public boolean getTipo() { return esAdmin; } // true = admin
    
    public void setSaldo(double saldo) { this.saldo = saldo; }
}
