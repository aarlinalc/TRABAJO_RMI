import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;

class UsuarioImpl extends UnicastRemoteObject implements Usuario{

    String nombre;
    String apellido;
    String dni;
    String contraseña;
    boolean tipo;      // 1 ADMINSTRADOR, 0 NORMAL
    double saldo;

    public UsuarioImpl() throws RemoteException{
    }

    public void setNombre(String nombre){
  this.nombre = nombre;
    }
    public String getNombre(){
  return nombre;
    }

    public void setApellido(String apellido){
  this.apellido = apellido;
    }
    public String getApellido(){
  return apellido;
    }

    public void setDni(String dni){
  String formato = "\\d{8}[A-HJ-NP-TV-Z]";

  if(dni.matches(formato))
      {
    this.dni = dni;
      }
    }
    public String getDni(){
  return dni;
    }

    public void setContrasena(String contraseña){
  this.contraseña = contraseña;
    }
    public String getContrasena(){
  return contraseña;
    }

    public void setTipo(boolean tipo){
  this.tipo = tipo;
    }
    public boolean getTipo(){
  return tipo;
    }

    public void setSaldo(double saldo){
  this.saldo = saldo;
    }
    public double getSaldo(){
  return saldo;
    }
}