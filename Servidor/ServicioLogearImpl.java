import java.util.*;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;


/*                  CLASE IMPLEMENTACIÓN SERVICIO LOGGING
 *
   *     - Crear usuario
   *     - Buscar usuario por DNI
   *     - Leer Usuarios desde fichero
   *     - Leer Administradores desde fichero
   *     - Modificar Saldo
 *
 */


class ServicioLogearImpl extends UnicastRemoteObject implements ServicioLogear
{
    File archivo_usuarios;
    List<Usuario> listaUsuarios;
    List<String> listaDniAdministradores;

    ServicioLogearImpl() throws RemoteException
    {
  archivo_usuarios = new File("usuarios.txt");
  listaDniAdministradores = new LinkedList<String>();
  leerDniAdministradores("administradores.txt");

  listaUsuarios = new LinkedList<Usuario>();
  leerUsuarios("usuarios.txt");
    }




    public boolean crearUsuario(String nombre, String apellido, String dni, Double saldo, String contrasena) throws RemoteException
    {
  boolean creado;

  try
      {
    FileWriter writer = new FileWriter("usuarios.txt", true);
    writer.write(nombre + "," + apellido + "," + dni + "," + saldo + "," + contrasena + "\n");
    writer.close();
    leerUsuarios("usuarios.txt");
    
    creado = true;
      }
  catch (IOException e)
      {
    System.err.println("Error al crear usuario: " + e.getMessage());
    creado = false;
      }

  return creado;
    }

    public Usuario buscarUsuarioPorDNI(String dni) throws RemoteException
    {

  Usuario usuario = null;
  try {
      usuario = new UsuarioImpl();

  for (Usuario usuarioEncontrado : listaUsuarios)
      {
    if (dni.equals(usuarioEncontrado.getDni()))
        {

      usuario.setNombre(usuarioEncontrado.getNombre());
      usuario.setApellido(usuarioEncontrado.getApellido());
      usuario.setDni(usuarioEncontrado.getDni());
      usuario.setContrasena(usuarioEncontrado.getContrasena());
      usuario.setTipo(usuarioEncontrado.getTipo());
      usuario.setSaldo(usuarioEncontrado.getSaldo());
        }
      }
  } catch (RemoteException ex){}

  return usuario;
    }

    public void leerUsuarios(String archivo_usuarios)
    {
  listaUsuarios.clear();
  System.out.println("Leyendo del archivo " + archivo_usuarios);

  try (BufferedReader br = new BufferedReader(new FileReader(archivo_usuarios)))
      {
    String linea;
    listaUsuarios.clear();
    leerDniAdministradores("administradores.txt");
    while ((linea = br.readLine()) != null)
        {
      String[] partes = linea.split(",");
      if (partes.length == 5)
          {
        String nombre = partes[0].trim();
        String apellido = partes[1].trim();
        String dni = partes[2].trim();
        Double saldo = Double.parseDouble(partes[3].trim());
        String contrasena = partes[4].trim();

        Usuario usuario = new UsuarioImpl();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDni(dni);
        usuario.setSaldo(saldo);
        usuario.setContrasena(contrasena);
        usuario.setTipo(false);

        for (String dniAdministrador : listaDniAdministradores)
            {

          if (dni.equals(dniAdministrador))
              {
            usuario.setTipo(true);
              }
            }

        listaUsuarios.add(usuario);
          }
      else
          {
        System.err.println("Error al leer la linea " + linea + " del archivo " + archivo_usuarios);
          }
        }
      }
  catch (IOException | NumberFormatException e)
      {
    System.err.println("Error al leer el archivo: " + e.getMessage());
      }
    }

    public void leerDniAdministradores(String archivo_administradores)
    {	
  listaDniAdministradores.clear();
  System.out.println("Leyendo del archivo " + archivo_administradores);

  try (BufferedReader br = new BufferedReader(new FileReader(archivo_administradores)))
      {
    String linea;

    while ((linea = br.readLine()) != null)
        {
      String formato = "\\d{8}[A-HJ-NP-TV-Z]";

      if (linea.matches(formato))
          {
        listaDniAdministradores.add(linea);
          }
      else
          {
        System.err.println("Error al leer la linea " + linea + " del archivo " + archivo_administradores);
          }
        }
      }
  catch (IOException | NumberFormatException e)
      {
    System.err.println("Error al leer el archivo: " + e.getMessage());
      }
    }

    public void modificarSaldo (String dni, double coste, String archivo_usuarios)
    {

  try {
  for (Usuario usuario : listaUsuarios)
      {
    if (dni.equals(usuario.getDni()))
        {
      usuario.setSaldo(Math.round((usuario.getSaldo()-coste)*100)/100.0);
        }
      }

  PrintWriter writer = new PrintWriter(new FileWriter(archivo_usuarios));

    for (Usuario usuario : listaUsuarios)
        {
      writer.write(usuario.getNombre() + "," + usuario.getApellido() + "," + usuario.getDni() + "," + usuario.getSaldo() + "," +usuario.getContrasena() + "\n");
        }
    writer.close();
      }catch (RemoteException e){}
  catch (IOException e)
      {
    System.err.println("Error al escribir en el archivo: " + e.getMessage());
      } 
    }
}