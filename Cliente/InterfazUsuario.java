
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
import java.util.HashSet;
import java.util.Set;
import javax.swing.table.TableColumnModel;


/*                  CLASE INTERFAZ USUARIO
 *
 *     - Muestra la interfaz del Usuario
 *
 */

public class InterfazUsuario extends javax.swing.JFrame {

  /*                  VARIABLES
     *
     *    - Variables de la interfaz grafica
     *    - Argumentos
     *    - Referencia a servicio Insertar y Buscar
     *
     */

    private String[] listaFinalTipos;
  private String argumentos[];
    private DefaultTableModel model;
    private ServicioInsertarBuscar srv_insertar_buscar;
  List<Item> listaItems=new ArrayList<>();
    private Usuario usuario;
    private int comienzo=1;
    private javax.swing.JButton Actualizar_Busqueda;
    private javax.swing.JButton Cerrar_Sesion;
    private javax.swing.JButton Comprar;
    private javax.swing.JButton Resenas;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;

  /*                  CONSTRUCTOR
     * 
     *    - Inicializa la Interfaz Usuario, así como asigna valores a variables de interés
     *
     */

  
    public InterfazUsuario(String args[],Usuario usuario) {
    argumentos=args;
    this.usuario=usuario;

    try {
         srv_insertar_buscar = (ServicioInsertarBuscar) Naming.lookup("//" + argumentos[0] + ":" + argumentos[1] + "/ServicioInsertarBuscar");
        listaItems=srv_insertar_buscar.filtrar("","",10);

 

  Set<String> listaTiposUnicos = new HashSet<>();


    for (Item item : listaItems) {

  if (!listaTiposUnicos.contains(item.getTipoArticulo()))
      listaTiposUnicos.add(item.getTipoArticulo());

    }


    List<String> listaTipos = new ArrayList<>(listaTiposUnicos);

    listaFinalTipos=listaTipos.toArray(new String[0]);

        String nuevoElemento = "-";


        String[] arrayConcatenado = new String[listaFinalTipos.length + 1];

  
        System.arraycopy(listaFinalTipos, 0, arrayConcatenado, 1, listaFinalTipos.length);


        arrayConcatenado[0] = nuevoElemento;

  listaFinalTipos=arrayConcatenado;

    } catch (MalformedURLException e) {
        e.printStackTrace(); 
    } catch (RemoteException e) {
        e.printStackTrace(); 
    } catch (NotBoundException e) {
        e.printStackTrace(); 
    }
    initComponents();

    }

  /*                  INITCOMPONENTS
     * 
     *    - Método que crea los componentes de la Interfaz Administrador (Interfaz Grafica NetBeans)
     *
     */

    
    @SuppressWarnings("unchecked")                        
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Cerrar_Sesion = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        Actualizar_Busqueda = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        Comprar = new javax.swing.JButton();
        Resenas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("MENU");

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
    timer.start(); 





            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Tipo");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(listaFinalTipos));
        
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Nombre");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(64, 28));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Precio");

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "0 a 20€", "20 a 50€", "50 a 100€", ">100€" }));
        

        Actualizar_Busqueda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Actualizar_Busqueda.setText("ACTUALIZAR BUSQUEDA");
        Actualizar_Busqueda.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

   Actualizar_Busqueda.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

    actualizar_busqueda();



            }
        });

    model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Tipo");
        model.addColumn("Cantidad");
        model.addColumn("Precio €");
   actualizar_tabla();

        Comprar.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        Comprar.setText("COMPRAR");
 Comprar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
    compra();	
            }
        });
        Resenas.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        Resenas.setText("RESEÑAS");
  Resenas.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
    resena();	
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()

                                .addGap(808, 808, 808)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Cerrar_Sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel4)
                                        .addGap(48, 48, 48)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(226, 226, 226)
                                .addComponent(Actualizar_Busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(168, 168, 168)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Comprar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Resenas))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1043, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(270, 270, 270)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addComponent(Actualizar_Busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Resenas)
                            .addComponent(Comprar))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(Cerrar_Sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))

                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 726, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }                       


  /*                  ACTUALIZAR TABLA
     * 
     *    - Metodo que actualiza la tabla de items
     *
     */
    private void actualizar_tabla(){

  model.setRowCount(0);

        for (Item item : listaItems) {
      model.addRow(new Object[]{item.getReferencia(), item.getNombre(), item.getTipoArticulo(), item.getUnidades(), item.getPrecio()});
        }


    jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
     jTable1 = new javax.swing.JTable(model);

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
      TableColumnModel columnModel = jTable1.getColumnModel();
       columnModel.getColumn(1).setPreferredWidth(400);
   jTable1.revalidate();
  jTable1.setDefaultEditor(Object.class, null);
    jTable1.repaint(); // Opcional: repintar la tabla
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setRowHeight(40);
        jScrollPane1.setViewportView(jTable1);


  if (listaItems.isEmpty() && comienzo==0){
    JOptionPane.showMessageDialog(null, "No esisten coincidencias.", "BigBox", JOptionPane.WARNING_MESSAGE);
  }

    }

  /*                  ACTUALIZAR BUSQUEDA
     * 
     *    - Metodo que filtra los items mostrados originalmente, en base a los criterios de busqueda seleccionados
     *
     */

    private void actualizar_busqueda(){

  String nombre = jTextField1.getText();
    String tipo = (String) jComboBox1.getSelectedItem();
    if (tipo=="-")
        tipo="";
    int rango_precio = jComboBox2.getSelectedIndex()-1;


    try{
        
    listaItems = srv_insertar_buscar.filtrar(nombre,tipo,rango_precio);


    comienzo=0;
    actualizar_tabla();

     } catch (RemoteException ex){}
    catch(Exception ex){}
    }


  /*                 RESEÑA
     *
     *   - Listener que llama a la interfaz de Reseña cuando se pulsa el botón "Reseña"
     *
     */
  
    private void resena(){

  int[] indiceSeleccionado = jTable1.getSelectedRows();

  if (indiceSeleccionado.length>1 || indiceSeleccionado.length==0) {

    JOptionPane.showMessageDialog(null, "Por favor, seleccione un solo item para continuar", "BigBox", JOptionPane.ERROR_MESSAGE);
  }
  else{

  Item itemSeleccionado = new Item();

  itemSeleccionado.setReferencia((int) model.getValueAt(indiceSeleccionado[0],0));
  itemSeleccionado.setNombre((String) model.getValueAt(indiceSeleccionado[0],1));
  itemSeleccionado.setTipoArticulo((String) model.getValueAt(indiceSeleccionado[0],2));
  itemSeleccionado.setUnidades((int) model.getValueAt(indiceSeleccionado[0],3));
  itemSeleccionado.setPrecio((Double) model.getValueAt(indiceSeleccionado[0],4));


  InterfazResena app = new InterfazResena(argumentos,itemSeleccionado,usuario);

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

    }


  /*                 COMPRA
     *
     *   - Listener que llama a la interfaz de Compra cuando se pulsa el botón "Comprar"
     *
     */

private void compra(){

  int[] indiceSeleccionado = jTable1.getSelectedRows();

  if (indiceSeleccionado.length>1 || indiceSeleccionado.length==0) {
  
    JOptionPane.showMessageDialog(null, "Por favor, seleccione un solo item para continuar", "BigBox", JOptionPane.ERROR_MESSAGE);
  }
  else{

  Item itemSeleccionado = new Item();

  itemSeleccionado.setReferencia((int) model.getValueAt(indiceSeleccionado[0],0));
  itemSeleccionado.setNombre((String) model.getValueAt(indiceSeleccionado[0],1));
  itemSeleccionado.setTipoArticulo((String) model.getValueAt(indiceSeleccionado[0],2));
  itemSeleccionado.setUnidades((int) model.getValueAt(indiceSeleccionado[0],3));
  itemSeleccionado.setPrecio((Double) model.getValueAt(indiceSeleccionado[0],4));


  InterfazCompra app = new InterfazCompra(argumentos,itemSeleccionado,usuario);
 
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

    }


  /*                  EMPEZAR
     * 
     *    - Metodo que carga la interfaz
     *
     */


    public void empezar(){
      
        try {
      listaItems=srv_insertar_buscar.filtrar("","",10);
      actualizar_tabla();
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
 java.util.logging.Logger.getLogger(InterfazUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

  } catch (Exception ex){
 java.util.logging.Logger.getLogger(InterfazUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
  }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazUsuario(argumentos,usuario).setVisible(true);
            }
        });
    }
                 
}

