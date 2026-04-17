import javax.swing.*;
import java.awt.*;
import java.rmi.*;
import java.awt.event.*;

/* CLASE INTERFAZ RESERVA (ROOM RMI)
 * - Muestra la interfaz de reserva de habitaciones
 */
public class InterfazReserva extends javax.swing.JFrame {

    private Habitacion habitacion_mod;
    private String argumentos[];
    private String saldo;
    private Usuario usuario;
    
    // Nuevos servicios adaptados a RoomRMI
    private ServicioGestionUsuarios srv_usuarios;
    private ServicioInventarioHabitaciones srv_inventario;
    
    private javax.swing.JButton Botonpagar;
    private javax.swing.JButton Cerrar_Sesion;
    private javax.swing.JTextField CodigoPostal;
    private javax.swing.JTextField Direccion;
    private javax.swing.JLabel Nombreameterdelartiucloquecompro;
    private javax.swing.JTextField Num_unidades;
    private javax.swing.JButton Volver;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;

    public InterfazReserva(String args[], Habitacion habitacion, Usuario usuario) {
        try {
            habitacion_mod = habitacion;
            argumentos = args;
            this.usuario = usuario;
            saldo = String.valueOf(usuario.getSaldo());
            initComponents();

            srv_usuarios = (ServicioGestionUsuarios) Naming.lookup("//" + argumentos[0] + ":" + argumentos[1] + "/ServicioGestionUsuarios");
            srv_inventario = (ServicioInventarioHabitaciones) Naming.lookup("//" + argumentos[0] + ":" + argumentos[1] + "/ServicioInventarioHabitaciones");

        } catch (RemoteException | java.net.MalformedURLException | NotBoundException ex) {
            System.err.println("Error de conexión RMI en reservas.");
        }
    }

    @SuppressWarnings("unchecked")                      
    private void initComponents() {
        Nombreameterdelartiucloquecompro = new javax.swing.JLabel();
        Volver = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Cerrar_Sesion = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Num_unidades = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Botonpagar = new javax.swing.JButton();
        Direccion = new javax.swing.JTextField();
        CodigoPostal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // TEXTO ACTUALIZADO PARA ROOMRMI
        Nombreameterdelartiucloquecompro.setFont(new java.awt.Font("Segoe UI", 1, 20)); 
        Nombreameterdelartiucloquecompro.setText("Hotel: " + habitacion_mod.getHotel() + " - Noche: " + habitacion_mod.getPrecioPorNoche() + "€ - Tu Saldo: " + saldo + "€ - Disp: " + habitacion_mod.getPlazasDisponibles());

        Volver.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        Volver.setText("VOLVER");
        Volver.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InterfazUsuario app = new InterfazUsuario(argumentos, usuario);
                app.empezar();
                Timer timer = new Timer(1000, e -> {
                    setVisible(false);
                    dispose();
                });
                timer.start();
            }
        });

        jLabel2.setText("Logo RoomRMI");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(102, 102, 102), null, null));

        Cerrar_Sesion.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        Cerrar_Sesion.setText("CERRAR SESION");
        Cerrar_Sesion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Cerrar_Sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InterfazPrincipal app = new InterfazPrincipal(argumentos);
                app.empezar();
                Timer timer = new Timer(1000, e -> {
                    setVisible(false);
                    dispose();
                });
                timer.start(); 
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 30)); 
        jLabel3.setText("Titular Reserva");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 30)); 
        jLabel4.setText("Noches");

        Num_unidades.setFont(new java.awt.Font("Segoe UI", 0, 30)); 
        Num_unidades.setPreferredSize(new java.awt.Dimension(64, 28));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 30)); 
        jLabel5.setText("Teléfono");

        Botonpagar.setFont(new java.awt.Font("Segoe UI", 1, 30)); 
        Botonpagar.setText("RESERVAR");
        Botonpagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envioPago();
            }
        });

        Direccion.setFont(new java.awt.Font("Segoe UI", 0, 30)); 
        Direccion.setText("Nombre Completo");
       
        CodigoPostal.setFont(new java.awt.Font("Segoe UI", 0, 30)); 

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(392, 392, 392)
                        .addComponent(Nombreameterdelartiucloquecompro, javax.swing.GroupLayout.PREFERRED_SIZE, 1171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cerrar_Sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(696, 696, 696)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Direccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Num_unidades, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(CodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(Botonpagar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1137, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cerrar_Sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 726, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Nombreameterdelartiucloquecompro, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Num_unidades, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(CodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(87, 87, 87)
                        .addComponent(Botonpagar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }                        

    public static boolean comprobacion_entero(int number) {
        try {
            Integer.parseInt(String.valueOf(number));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void envioPago() {
        String titular = Direccion.getText();
        String telefono = CodigoPostal.getText();
        
        if(titular.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe introducir titular y teléfono válidos", "RoomRMI", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                int noches = Integer.parseInt(Num_unidades.getText()); 

                if (noches <= 0) throw new NumberFormatException();

                double coste = Math.round(noches * habitacion_mod.getPrecioPorNoche() * 100) / 100.0;

                if (noches <= habitacion_mod.getPlazasDisponibles() && usuario.getSaldo() >= coste) {
                    
                    // LÓGICA RMI ACTUALIZADA Y TRANSACCIONAL
                    boolean reservaOk = srv_usuarios.procesarReserva(usuario.getDni(), habitacion_mod.getReferencia(), coste);

                    if(reservaOk) {
                        usuario.setSaldo(Math.round((usuario.getSaldo() - coste) * 100) / 100.0);
                        habitacion_mod.setPlazasDisponibles(habitacion_mod.getPlazasDisponibles() - noches);
                        
                        JOptionPane.showMessageDialog(null, "¡Reserva confirmada en " + habitacion_mod.getHotel() + "!", "RoomRMI", JOptionPane.INFORMATION_MESSAGE);
                        Nombreameterdelartiucloquecompro.setText("Hotel: " + habitacion_mod.getHotel() + " - Noche: " + habitacion_mod.getPrecioPorNoche() + "€ - Tu Saldo: " + usuario.getSaldo() + "€ - Disp: " + habitacion_mod.getPlazasDisponibles());
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en el servidor procesando la reserva.", "RoomRMI", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (noches > habitacion_mod.getPlazasDisponibles()) {
                    JOptionPane.showMessageDialog(null, "No hay disponibilidad suficiente para esas fechas.", "RoomRMI", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente en su tarjeta.", "RoomRMI", JOptionPane.WARNING_MESSAGE);
                }

            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(null, "Error conectando con el servidor RoomRMI", "Error Red", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe introducir un número de noches válido", "RoomRMI", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void empezar() {
        java.awt.EventQueue.invokeLater(() -> {
            new InterfazReserva(argumentos, habitacion_mod, usuario).setVisible(true);
        });
    }
}
