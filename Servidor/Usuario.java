import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;

interface Usuario extends Remote{


    public void setNombre(String nombre)throws RemoteException;

    public String getNombre()throws RemoteException;


    public void setApellido(String apellido)throws RemoteException;

    public String getApellido()throws RemoteException;


    public void setDni(String dni)throws RemoteException;

    public String getDni()throws RemoteException;


    public void setContrasena(String contraseña)throws RemoteException;

    public String getContrasena()throws RemoteException;


    public void setTipo(boolean tipo)throws RemoteException;

    public boolean getTipo()throws RemoteException;


    public void setSaldo(double saldo)throws RemoteException;

    public double getSaldo()throws RemoteException;

}
