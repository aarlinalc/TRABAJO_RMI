
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
//import java.util.*;
import java.util.List;
import java.rmi.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import javax.swing.table.TableRowSorter;
import java.util.Random;



/*                  CLASE INTERFAZ REGISTRO
*
*     - Muestra la interfaz de Registro
*
*/

public class InterfazRegistro extends javax.swing.JFrame {

   /*                  VARIABLES
    *
    *    - Variables de la interfaz
    *    - Argumentos     
    *    - Parámetros
    *    - Referencia al Servicio de Logging
    *
    */
  
    private String argumentos[];
    private ServicioLogear srv_logear;                   
  private javax.swing.JTextField Apellido;
  private javax.swing.JButton Confirmar;
  private javax.swing.JPasswordField Contrasena;
  private javax.swing.JTextField DNI;
  private javax.swing.JTextField Nombre;
  private javax.swing.JButton Volver;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3; 

  /*                  CONSTRUCTOR
    * 
    *    - Crea e inicializa la interfaz, así como asigna valores a variables de interés
    *
    */
  
    public InterfazRegistro(String args[]) {
        initComponents();
  argumentos=args;
  try{
   srv_logear = (ServicioLogear) Naming.lookup("//" + argumentos[0] + ":" + argumentos[1] + "/ServicioLogear");
   }catch (RemoteException ex){}
   catch (Exception ex){}
    }

    @SuppressWarnings("unchecked")                        
  /* Método que inicializa los componentes de la Interfaz de Registro */
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Volver = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Contrasena = new javax.swing.JPasswordField();
        Confirmar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Apellido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        DNI = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("Logo.jpg"))); // NOI18N

        Volver.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        Volver.setText("VOLVER");
        Volver.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

   InterfazPrincipal app = new InterfazPrincipal(argumentos);

   app.empezar();
      Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
          setVisible(false);
          dispose();

            }
        });
    timer.start(); // Iniciar el temporizador
            }
        });


        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(135, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(163, 163, 163))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140)
                .addComponent(jLabel1)
                .addContainerGap(1230, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 60)); // NOI18N
        jLabel3.setText("REGISTRARSE");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 30)); // NOI18N
        jLabel4.setText("Nombre:");

        Nombre.setFont(new java.awt.Font("sansserif", 0, 30)); // NOI18N
        

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 30)); // NOI18N
        jLabel5.setText("Apellido:");

        Contrasena.setFont(new java.awt.Font("sansserif", 0, 30)); // NOI18N
        


      /*             COMPROBACIÓN DE DATOS
        *
        *   - Listener que comprueba la validez de los datos, si son correctos crea un nuevo usuario con esos datos, asignándole un saldo aleatorio
        *
        */
      
        Confirmar.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        Confirmar.setText("CONFIRMAR");
        Confirmar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
  Confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

     String nombre = Nombre.getText();
      String apellido = Apellido.getText();
      String dni = DNI.getText();
      String contrasena = new String(Contrasena.getPassword());
      JLabel messageLabel;
      JOptionPane optionPane;

      Random random = new Random();
      // Generar un número aleatorio entre 0 y 300 
        double saldo = random.nextDouble() * 300;

        // Redondear el número a dos decimales
        saldo = Math.round(saldo * 100.0) / 100.0;


      String cadena = "\\d{8}[A-HJ-NP-TV-Z]";
      if (dni.matches(cadena)) {
          try {
        Usuario buscarUsuarioRegistro = srv_logear.buscarUsuarioPorDNI(dni);
        if (buscarUsuarioRegistro.getDni() == null) {
            if (srv_logear.crearUsuario(nombre, apellido, dni, saldo, contrasena)) {
          messageLabel = new JLabel("Usuario insertado correctamente.");
    messageLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Cambiar el tamaño de la letra
     optionPane = new JOptionPane(messageLabel, JOptionPane.INFORMATION_MESSAGE);
     JDialog dialog = optionPane.createDialog("BigBox");
     dialog.setSize(500, 400);
     dialog.setVisible(true);
            } else {
         messageLabel = new JLabel("Error al crear usuario.");
   messageLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Cambiar el tamaño de la letra
    optionPane = new JOptionPane(messageLabel, JOptionPane.ERROR_MESSAGE);
    JDialog dialog = optionPane.createDialog("BigBox");
    dialog.setSize(500, 400);
    dialog.setVisible(true);
            }
        } else {
            messageLabel = new JLabel("Ya existe un usuario con ese DNI");
      messageLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Cambiar el tamaño de la letra
       optionPane = new JOptionPane(messageLabel, JOptionPane.WARNING_MESSAGE);
       JDialog dialog = optionPane.createDialog("BigBox");
       dialog.setSize(500, 400);
       dialog.setVisible(true);
        }
          } catch (RemoteException ex) {
        ex.printStackTrace();
          }
      } else {
         messageLabel = new JLabel("¿Está seguro de que ha insertado correctamente su DNI?");
   messageLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Cambiar el tamaño de la letra
    optionPane = new JOptionPane(messageLabel, JOptionPane.QUESTION_MESSAGE);
    JDialog dialog = optionPane.createDialog("BigBox");
    dialog.setSize(900, 300);
    dialog.setVisible(true);
      }

        }		
            }
        );

        jLabel6.setFont(new java.awt.Font("sansserif", 0, 30)); // NOI18N
        jLabel6.setText("Contraseña:");

        Apellido.setFont(new java.awt.Font("sansserif", 0, 30)); // NOI18N
        

        jLabel7.setFont(new java.awt.Font("sansserif", 0, 30)); // NOI18N
        jLabel7.setText("DNI:");

        DNI.setFont(new java.awt.Font("sansserif", 0, 30)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(DNI, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Apellido, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Nombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel6)
                            .addComponent(Contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(362, 362, 362)
                        .addComponent(Confirmar))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(jLabel3)))
                .addContainerGap(5704, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addGap(20, 20, 20)
                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel5)
                .addGap(20, 20, 20)
                .addComponent(Apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel7)
                .addGap(20, 20, 20)
                .addComponent(DNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel6)
                .addGap(20, 20, 20)
                .addComponent(Contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(Confirmar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }                                                          

   /* Método que carga la interfaz*/
  
    public void empezar() {
     
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazRegistro(argumentos).setVisible(true);
            }
        });
    }

                     

}
