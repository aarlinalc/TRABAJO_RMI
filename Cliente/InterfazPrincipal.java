import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.rmi.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import javax.swing.table.TableRowSorter;


/*                  CLASE INTERFAZ PRINCIPAL
 *
 *     - Muestra las opciones de Inicio de Sesión y Registro
 *
 */
public class InterfazPrincipal extends javax.swing.JFrame
{

    /*                  VARIABLES
     *
     *    - Variables de la interfaz grafica
     *    - Variables para la funcionalidad de los servicios
     */
    private javax.swing.JButton Iniciar_Sesion;
    private javax.swing.JButton Registrarse;
    private javax.swing.JButton Cerrar_App;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;

    private String argumentos[];


    /*                  CONSTRUCTOR
     * 
     *    - Inicializa la Interfaz Principal, así como asigna valores a variables de interés
     *
     */
    public InterfazPrincipal(String args[])
    {
  argumentos=args;
  initComponents();
    }

    /*                  INITCOMPONENTS
     * 
     *    - Método que crea los componentes de la Interfaz Principal (Interfaz Grafica NetBeans)
     *
     */
    @SuppressWarnings("unchecked")
    private void initComponents()
    {
  Iniciar_Sesion = new javax.swing.JButton();
        Registrarse = new javax.swing.JButton();
  Cerrar_App = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
  jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("Logo.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
           jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
               .addContainerGap(135, Short.MAX_VALUE)
               .addComponent(jLabel1)
               .addGap(163, 163, 163))
           );
        jPanel2Layout.setVerticalGroup(
               jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel2Layout.createSequentialGroup()
             .addGap(202, 202, 202)
             .addComponent(jLabel1)
             .addContainerGap(1133, Short.MAX_VALUE))
               );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        Iniciar_Sesion.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        Iniciar_Sesion.setText("INICIAR SESION");
        Iniciar_Sesion.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt)
    {
        Iniciar_SesionActionPerformed(evt);
    }
      });

        Registrarse.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        Registrarse.setText("REGISTRARSE");
        Registrarse.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt)
    {
        RegistrarseActionPerformed(evt);
    }
      });

  Cerrar_App.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        Cerrar_App.setText("CERRAR APP");
        Cerrar_App.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt)
    {
        System.exit(0);
    }
      });

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 60)); // NOI18N
        jLabel3.setText("BIENVENIDO A BIGBOX");

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
                       .addGap(299, 299, 299)
                       .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(Registrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(Iniciar_Sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(Cerrar_App, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)))
                   .addGroup(jPanel3Layout.createSequentialGroup()
                       .addGap(146, 146, 146)
                       .addComponent(jLabel3)))
               .addContainerGap(5783, Short.MAX_VALUE))
           );

        jPanel3Layout.setVerticalGroup(
               jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPanel3Layout.createSequentialGroup()
             .addGap(93, 93, 93)
             .addComponent(jLabel3)
             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
             .addComponent(jLabel2)
             .addGap(100, 100, 100)
             .addComponent(Iniciar_Sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
             .addGap(40, 40, 40)
             .addComponent(Registrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)

             .addGap(40, 40, 40)
             .addComponent(Cerrar_App, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
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
              .addGap(0, 0, Short.MAX_VALUE))
          );

        layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 46, Short.MAX_VALUE))
        );

        pack();
    }

    /*               INICIAR SESIÓN
     *
     *   - Listener que llama a la interfaz de Inicio de Sesión cuando se pulsa el botón "Iniciar Sesión"
     *
     */
    private void Iniciar_SesionActionPerformed(java.awt.event.ActionEvent evt)
    {                                               
  InterfazInicioSesion app = new InterfazInicioSesion(argumentos);
  app.empezar();

  Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e)
    {
        setVisible(false);
        dispose();
    }
      });

  timer.start(); // Iniciar el temporizador
    }

    /*                 REGISTRARSE
     *
     *   - Listener que llama a la interfaz de Registro cuando se pulsa el botón "Registrarse"
     *
     */
    private void RegistrarseActionPerformed(java.awt.event.ActionEvent evt)
    {
  InterfazRegistro app = new InterfazRegistro(argumentos);
  app.empezar();

  Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e)
    {
        setVisible(false);
        dispose();
    }
      });

  timer.start(); // Iniciar el temporizador
    }

    /*                  EMPEZAR
     * 
     *    - Metodo que carga la interfaz
     *
     */
    public void empezar()
    {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
    {
        if ("Nimbus".equals(info.getName()))
      {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
      }
    }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (Exception ex) {
      java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
  }

  setExtendedState(JFrame.MAXIMIZED_BOTH);

        java.awt.EventQueue.invokeLater(new Runnable() {
    public void run() {
        new InterfazPrincipal(argumentos).setVisible(true);
    }
      });
    }
}
