import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
//import java.util.*;
import java.util.List;
import java.rmi.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import javax.swing.table.TableRowSorter;
import java.util.Comparator;
import javax.swing.table.TableColumnModel;



/*                  CLASE INTERFAZ MODIFICAR ITEM
 *
 *     - Muestra la interfaz para modificar Items
 *
 */


public class InterfazModificarItem extends javax.swing.JFrame {


  /*                  VARIABLES
     *
     *    - Variables de la interfaz grafica
     *    - Argumentos
     *    - Referencia a servicio Insertar y Buscar
     *
     */
  
   private ServicioInsertarBuscar srv;
  private String argumentos[];
  List<Item> listaItems=new ArrayList<>();
  private javax.swing.JButton Cerrar_Sesion;
  private javax.swing.JButton Confirmar;
  private javax.swing.JButton Volver;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTable1;


  /*                  CONSTRUCTOR
     * 
     *    - Inicializa la Interfaz Modificar Item, así como asigna valores a variables de interés
     *
     */

    public InterfazModificarItem(String args[]) {
       argumentos=args;
    try {
        srv = (ServicioInsertarBuscar) Naming.lookup("//" + argumentos[0] + ":" + argumentos[1] + "/ServicioInsertarBuscar");
        listaItems=srv.filtrar("","",10);
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
        Volver = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Cerrar_Sesion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Confirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("MODIFICAR ITEM");

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

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Tipo");
        model.addColumn("Cantidad");
        model.addColumn("Precio €");

        for (Item item : listaItems) {
      model.addRow(new Object[]{item.getReferencia(), item.getNombre(), item.getTipoArticulo(), item.getUnidades(), item.getPrecio()});
        }


    jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
     jTable1 = new javax.swing.JTable(model);
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
      TableColumnModel columnModel = jTable1.getColumnModel();
       columnModel.getColumn(1).setPreferredWidth(400);
 
      
      
      
      // Crear un RowSorter personalizado
      RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model) {
          @Override
          public boolean isSortable(int column) {
              // Solo permite que la columna 1 sea ordenable
              return column == 1;
          }
      };

      // Aplicar el RowSorter a la tabla
      jTable1.setRowSorter(sorter);


      
   jTable1.revalidate();
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setRowHeight(40);
        jScrollPane1.setViewportView(jTable1);

        Confirmar.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        Confirmar.setText("CONFIRMAR");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(657, 657, 657)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Cerrar_Sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(500, 500, 500)
                                .addComponent(Confirmar)))
                        .addGap(0, 1500, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(Confirmar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cerrar_Sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 726, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }                       

  /*                 CONFIRMAR
     *
     *   - Listener que lleva a cabo los cambios realizados en los items
     *
     */

    private void ConfirmarActionPerformed(java.awt.event.ActionEvent evt) {                                              
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        List<Item> nuevosItems = new ArrayList<>();

        model.fireTableDataChanged();

        for (int row = 0; row < model.getRowCount(); row++) {
            int id = Integer.parseInt(model.getValueAt(row, 0).toString());				    
            String nombre = model.getValueAt(row, 1).toString();
            String tipo = model.getValueAt(row, 2).toString();
            int cantidad = Integer.parseInt(model.getValueAt(row, 3).toString());
            double precio = Double.parseDouble(model.getValueAt(row, 4).toString());

            nuevosItems.add(new Item(id, nombre, tipo, cantidad, precio));
        }

        // Crear un conjunto para almacenar los IDs únicos
        Set<Integer> idsUnicos = new HashSet<>();

        boolean todosIdsUnicos = true;

        // Recorrer la lista de items
        for (Item item : nuevosItems) {
            // Verificar si el ID ya está en el conjunto
            if (idsUnicos.contains(item.getReferencia())) {
          // El ID ya está duplicado, establecer la bandera a false y salir del bucle
          todosIdsUnicos = false;
          break;
            } else {
          // Agregar el ID al conjunto
          idsUnicos.add(item.getReferencia());
            }
        }

        try
            {
          if(todosIdsUnicos == true)
              {
            srv.actualizarItems(nuevosItems);

       JOptionPane.showMessageDialog(null, "Se ha actualizado la tabla de items", "BigBox", JOptionPane.INFORMATION_MESSAGE);

              }
          else
              {
           JOptionPane.showMessageDialog(null, "Item no actualizado,compruebe que no hay ninguna referencia repetida", "BigBox", JOptionPane.WARNING_MESSAGE);
              }
            }
        catch(RemoteException ex)
            {
          ex.printStackTrace();
            }

    }  

  /*                  EMPEZAR
     * 
     *    - Metodo que carga la interfaz
     *
     */
  
    public void empezar(){
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazModificarItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazModificarItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazModificarItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazModificarItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazModificarItem(argumentos).setVisible(true);
            }
        });
    }
                  
}

