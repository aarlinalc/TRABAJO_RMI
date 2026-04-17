import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
//import java.util.*;
import java.util.List;
import java.rmi.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import javax.swing.table.TableRowSorter;

/*                  CLASE INTERFAZ USUARIO
 *
 *     - Muestra la interfaz del Usuario
 *
 */


public class InterfazInsertarItem extends javax.swing.JFrame {


  /*                  VARIABLES
     *
     *    - Variables de la interfaz grafica
     *    - Argumentos
     *    - Referencia a servicio Insertar y Buscar
     *
     */

    private String argumentos[];

    private ServicioInsertarBuscar srv_insertar_buscar;

  private javax.swing.JTextField Cantidad_Articulo;
  private javax.swing.JButton Cerrar_sesion;
  private javax.swing.JButton Confirmar;
  private javax.swing.JTextField Nombre_articulo;
  private javax.swing.JTextField Precio_articulo;
  private javax.swing.JTextField Tipo_articulo;
  private javax.swing.JButton Volver;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;


    /*                  CONSTRUCTOR
       * 
       *    - Inicializa la Interfaz Insertar Item, así como asigna valores a variables de interés
       *
       */
    public InterfazInsertarItem(String args[]) {
        initComponents();
  argumentos=args;
  try {
  srv_insertar_buscar = (ServicioInsertarBuscar) Naming.lookup("//" + argumentos[0] + ":" + argumentos[1] + "/ServicioInsertarBuscar");
  } catch(RemoteException ex)
          {
              ex.printStackTrace();
          } catch (Exception ex){
   ex.printStackTrace();
  }
    }

  /*                  INITCOMPONENTS
     * 
     *    - Método que crea los componentes de la Interfaz Administrador (Interfaz Grafica NetBeans)
     *
     */
  
    @SuppressWarnings("unchecked")                       
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Volver = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Cerrar_sesion = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Nombre_articulo = new javax.swing.JTextField();
        Tipo_articulo = new javax.swing.JTextField();
        Cantidad_Articulo = new javax.swing.JTextField();
        Precio_articulo = new javax.swing.JTextField();
        Confirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("INSERTAR ITEM");

        Volver.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Volver.setText("VOLVER");
        Volver.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
   Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

      InterfazAdmin app = new InterfazAdmin(argumentos);

  app.empezar();
      Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
          setVisible(false);
          dispose();

            }
        });
    timer.start(); 

            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("Logochiquito.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(102, 102, 102), null, null));

        Cerrar_sesion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Cerrar_sesion.setText("CERRAR SESION");
        Cerrar_sesion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Cerrar_sesion.addActionListener(new java.awt.event.ActionListener() {
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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel4.setText("Tipo:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel5.setText("Cantidad:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel6.setText("Precio €:");

        Nombre_articulo.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        

        Tipo_articulo.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
       

        Cantidad_Articulo.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
       

        Precio_articulo.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N

        Confirmar.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        Confirmar.setText("CONFIRMAR");
        Confirmar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cerrar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(758, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(516, 516, 516))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(701, 701, 701)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Tipo_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Nombre_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cantidad_Articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Precio_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(840, 840, 840)
                        .addComponent(Confirmar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cerrar_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Nombre_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(Tipo_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Cantidad_Articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(Precio_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117)
                .addComponent(Confirmar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        


  /*                 CONFIRMAR
     *
     *   - Listener que procede a la inserción del objeto al pulsar en el botón "CONFIRMAR"
     *
     */

    private void ConfirmarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // Acciones


  try
          {

        if (Nombre_articulo.getText().isEmpty() || Tipo_articulo.getText().isEmpty() || Cantidad_Articulo.getText().isEmpty() || Precio_articulo.getText().isEmpty() || Integer.parseInt(Precio_articulo.getText())<=0){
      JOptionPane.showMessageDialog(null, "Revise que los campos sean correctos.", "BigBox", JOptionPane.ERROR_MESSAGE);


        }

        else{

  Item nuevoItem = new Item(0, Nombre_articulo.getText(), Tipo_articulo.getText(), Integer.parseInt(Cantidad_Articulo.getText()), Double.parseDouble(Precio_articulo.getText()));


        srv_insertar_buscar.insertarItem(nuevoItem);
          
          JOptionPane.showMessageDialog(null, "Nuevo item insertado correctamente.", "BigBox", JOptionPane.INFORMATION_MESSAGE);

Nombre_articulo.setText("");
Tipo_articulo.setText("");
Cantidad_Articulo.setText("");
Precio_articulo.setText("");
          }

    }
      catch (RemoteException ex)
          {
        ex.printStackTrace();
          }

      catch (NumberFormatException ex){

     JOptionPane.showMessageDialog(null, "Compruebe que el formato de los datos es correcto", "BigBox", JOptionPane.ERROR_MESSAGE);

      }
        }                                         


  /*                  EMPEZAR
     * 
     *    - Metodo que carga la interfaz
     *
     */
    
    public void empezar() {

      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazInsertarItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazInsertarItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazInsertarItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazInsertarItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazInsertarItem(argumentos).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
   
    // End of variables declaration                   
}



