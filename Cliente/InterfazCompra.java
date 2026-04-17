import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.rmi.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableRowSorter;
import java.text.DecimalFormat;

/*                  CLASE INTERFAZ COMPRA
 *
 *     - Muestra la interfaz de compra del usuario
 *
 */


public class InterfazCompra extends javax.swing.JFrame {



  /*                  VARIABLES
     *
     *    - Variables de la interfaz grafica
     *    - Argumentos
     *    - Parámetros
     *    - Referencia a servicios
     *
     */

  
  private Item item_mod;
    private String argumentos[];
    private String saldo;

  private Usuario usuario;
  private ServicioLogear srv_logear;
  private ServicioInsertarBuscar srv_insertar_buscar;
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

  /*                  CONSTRUCTOR
     * 
     *    - Inicializa la Interfaz Compra, así como asigna valores a variables de interés
     *
     */
  
    public InterfazCompra(String args[],Item item, Usuario usuario) {
  try{
       item_mod=item;
    argumentos=args;
      this.usuario=usuario;
    saldo=String.valueOf(usuario.getSaldo());
      initComponents();

        srv_logear = (ServicioLogear) Naming.lookup("//"+argumentos[0]+":"+argumentos[1]+"/ServicioLogear");
    srv_insertar_buscar = (ServicioInsertarBuscar) Naming.lookup("//"+argumentos[0]+":"+argumentos[1]+"/ServicioInsertarBuscar");



  } catch (RemoteException ex){}
  catch (Exception ex){}
    }


  /*                  INITCOMPONENTS
     * 
     *    - Método que crea los componentes de la Interfaz Compra (Interfaz Grafica NetBeans)
     *
     */
  
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



        Nombreameterdelartiucloquecompro.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        Nombreameterdelartiucloquecompro.setText("Item: "+item_mod.getNombre()+" - Precio Unitario: "+String.valueOf(item_mod.getPrecio())+"€"+" - Saldo: "+saldo+"€"+ " - Unidades:"+item_mod.getUnidades());

        Volver.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Volver.setText("VOLVER");
        Volver.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    Volver.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
    InterfazUsuario app = new InterfazUsuario(argumentos,usuario);

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("Logochiquito.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(102, 102, 102), null, null));

        Cerrar_Sesion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Cerrar_Sesion.setText("CERRAR SESION");
        Cerrar_Sesion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Cerrar_Sesion.addActionListener(new java.awt.event.ActionListener() {

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
        jLabel3.setText("Dirección");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel4.setText("Nº Unidades");

        Num_unidades.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        Num_unidades.setPreferredSize(new java.awt.Dimension(64, 28));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel5.setText("Codigo Postal");

        Botonpagar.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        Botonpagar.setText("PAGAR");
       Botonpagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envioPago();
            }
        });

        Direccion.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        Direccion.setText("Calle/Nº");
       

        CodigoPostal.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        

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
        // Intenta convertir el entero a un String y luego de nuevo a un entero para verificar si es válido
        int parsedInt = Integer.parseInt(String.valueOf(number));
        return true;
    } catch (NumberFormatException e) {
        // Si se produce una excepción, significa que el entero no es válido
        return false;
    }
}

  /*                 ENVIO PAGO
     *
     *   - Listener que recoge los datos de la compra y efectua el pago
     *
     */

  private void envioPago(){

  String direccion=Direccion.getText();
  String codigo_postal=CodigoPostal.getText();
  if(direccion.isEmpty() || codigo_postal.isEmpty())
   JOptionPane.showMessageDialog(null, "Debe introducir una dirección y codigo postal válidos", "BigBox", JOptionPane.ERROR_MESSAGE);
  else{


  try {

  int unidades = Integer.parseInt(Num_unidades.getText()); //nº de unidades que selecciono para pagar

   if (unidades<=0)
       throw new NumberFormatException("El numero introducido es menor o igual que 0");


    if(unidades<=item_mod.getUnidades() && usuario.getSaldo()>=unidades*item_mod.getPrecio()){
                      item_mod.setUnidades(item_mod.getUnidades()-unidades);
                      srv_insertar_buscar.actualizarItem(item_mod);



          // Redondear el número a dos decimales
          double coste = Math.round(unidades*item_mod.getPrecio()*100) / 100.0;


          usuario.setSaldo(Math.round((usuario.getSaldo()-(coste))*100)/100.0);
                  srv_logear.modificarSaldo(usuario.getDni(),coste,"usuarios.txt");
           JOptionPane.showMessageDialog(null, "¡Compra efectuada ! Pase por el almacén cercano a partir de 3 días hábiles para recoger su compra", "BigBox", JOptionPane.INFORMATION_MESSAGE);
              Nombreameterdelartiucloquecompro.setText("Item: "+item_mod.getNombre()+" - Precio Unitario: "+String.valueOf(item_mod.getPrecio())+"€"+" - Saldo: "+String.valueOf(usuario.getSaldo())+"€"+ " - Unidades:"+item_mod.getUnidades());

                    }
   else if (unidades > item_mod.getUnidades()) {
                      
            JOptionPane.showMessageDialog(null, "No hay unidades disponibles de este producto,perdone las molestias", "BigBox", JOptionPane.INFORMATION_MESSAGE);

                                    }
                    else{
                  
                     JOptionPane.showMessageDialog(null, "No hay suficiente saldo en la cuenta", "BigBox", JOptionPane.INFORMATION_MESSAGE);


                  }


     } catch (RemoteException ex) {
    System.err.println("Error Remoto");
} catch (NumberFormatException e) {
    // Si se produce una excepción, significa que el entero no es válido
    JOptionPane.showMessageDialog(null, "Debe introducir un numero entero positivo", "BigBox", JOptionPane.ERROR_MESSAGE);
} catch (Exception exc) {
    System.err.println("Otro error");
}

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
            java.util.logging.Logger.getLogger(InterfazCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazCompra(argumentos,item_mod,usuario).setVisible(true);
            }
        });
    }

                   
   
                  
}