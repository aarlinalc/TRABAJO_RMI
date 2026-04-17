import java.util.*;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;


/*                  CLASE IMPLEMENTACIÓN SERVICIO INSERTAR BUSCAR
 *
   *     - Insertar item en la base de datos (ficheros txt)
   *     - Filtrar items por nombre, tipo, rango de precio
   *     - Actualizar items
   *     - Leer items desde fichero
 *
 */

class ServicioInsertarBuscarImpl extends UnicastRemoteObject implements ServicioInsertarBuscar {
  static List<Item> lista;
  File archivo;

  ServicioInsertarBuscarImpl() throws RemoteException {
    lista = new LinkedList<Item>();
    archivo = new File("items.txt");
    leerItems("items.txt");
  }

  public void insertarItem(Item item) throws RemoteException {
    try {

  List<Integer> ids = new ArrayList<>();
  int id_encontrado=0;
  int id_final = 1;

  for (Item item_lista : lista)
      ids.add(item_lista.getReferencia());

        while (id_encontrado==0){
      id_encontrado=1;
      for (int id : ids){
          if (id == id_final){
        id_encontrado=0;
          id_final+=1;

          break;
          }
          }
      }

      item.setReferencia(id_final);


      FileWriter writer = new FileWriter("items.txt", true);
      writer.write(id_final + "," + item.getNombre() + "," + item.getTipoArticulo() + "," + item.getUnidades() + "," +item.getPrecio() + "\n");
      writer.close();
      // leerItems("items.txt");
      lista.add(item);
      
    } catch (IOException e) {
      System.err.println("Error al escribir en la base de datos: " + e.getMessage());
    } catch (NumberFormatException e){
  System.err.println("Error al añadir item: " + e.getMessage());
    } catch (Exception e){
  System.err.println("Error al añadir item: " + e.getMessage());
    }
  }

    public List<Item> filtrar(String nombre, String tipo, int rango_precio) {
  List<Item> itemsEncontrados = new ArrayList<>();

  int rango1=0;
  int rango2=20;
  int rango3=50;
  int rango4=100;

  int rango_ini;
  int rango_fin;

  switch(rango_precio){
  case(0):
      rango_ini=rango1;
      rango_fin=rango2;
      break;
  case(1):
      rango_ini=rango2;
      rango_fin=rango3;
      break;
  case(2):
      rango_ini=rango3;
      rango_fin=rango4;
      break;
  case(3):
      rango_ini=rango4;
      rango_fin=1000000;
      break;
  default:
      rango_ini=0;
      rango_fin=1000000;
  }

  for (Item item : lista){
      if (item.getNombre().contains(nombre) && item.getTipoArticulo().contains(tipo) && item.getPrecio()>=rango_ini && item.getPrecio()<=rango_fin)
    itemsEncontrados.add(item);
  }

      return itemsEncontrados;

    }

  public void leerItems(String nombreArchivo) {
      lista.clear();
    try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
      String linea;
      while ((linea = br.readLine()) != null) {
        String[] partes = linea.split(",");
        if (partes.length == 5) {
          int id = Integer.parseInt(partes[0].trim());
          String nombre = partes[1].trim();
          String tipo = partes[2].trim();
    int unidades = Integer.parseInt(partes[3].trim());
    double precio = Double.parseDouble(partes[4].trim());
          Item item = new Item(id, nombre, tipo,unidades,precio);
          lista.add(item);

        } else {
          System.err.println("Error al leer línea del archivo: " + linea);
        }
      }


    } catch (IOException | NumberFormatException e) {
      System.err.println("Error al leer el archivo: " + e.getMessage());
    } catch (Exception e){
  System.err.println("Error al leer el archivo: " + e.getMessage());
    }
  }

 public void actualizarItem(Item nuevoItem) {
      // Buscar el índice del item con el ID especificado
      int indice = -1;
      for (int i = 0; i < lista.size(); i++) {
          if (lista.get(i).getReferencia() == nuevoItem.getReferencia()) {
              indice = i;
              break;
          }
      }
      if (indice != -1) {
          // Reemplazar la línea en la lista de items
          lista.set(indice, nuevoItem);
          // Escribir la lista actualizada en el archivo
          try (PrintWriter writer = new PrintWriter(new FileWriter("items.txt"))) {
              for (Item item : lista) {
                 writer.write(item.getReferencia() + "," + item.getNombre() + "," + item.getTipoArticulo() + "," + item.getUnidades() + "," +item.getPrecio() + "\n");
              }
           
      writer.close();
          } catch (IOException e) {
              System.err.println("Error al escribir en el archivo: " + e.getMessage());
          }
      } else {
          System.err.println("No se encontró ningún item con el ID especificado.");
      }
  }

    public void actualizarItems (List<Item> listaItems)
    {
  // Ordenar la lista de items en base al ID
        Collections.sort(listaItems, new Comparator<Item>() {
            public int compare(Item item1, Item item2) {
                // Comparar los IDs de los items
                return Integer.compare(item1.getReferencia(), item2.getReferencia());
            }
        });

        lista = listaItems;

        // Escribir la lista actualizada en el archivo
        try (PrintWriter writer = new PrintWriter(new FileWriter("items.txt"))) {
      for (Item item : lista) {
    writer.write(item.getReferencia() + "," + item.getNombre() + "," + item.getTipoArticulo() + "," + item.getUnidades() + "," +item.getPrecio() + "\n");
      }
     
      writer.close();
        } catch (IOException e) {
      System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
