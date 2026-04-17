import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;

class Item implements Serializable{

    int referencia;
    String nombre;
    String tipo_articulo;
    int unidades;
    double precio;


    public Item (){}
  public Item(int referencia, String nombre, String tipo_articulo,int unidades,double precio){
    this.referencia=referencia;
    this.nombre=nombre;
    this.tipo_articulo=tipo_articulo;
    this.unidades=unidades;
    this.precio=precio;
      }

  /* MÉTODOS SET */

    public void setReferencia(int referencia){
      this.referencia=referencia;
    }
    public void setNombre(String nombre){
      this.nombre=nombre;
    }
    public void setTipoArticulo(String tipo_articulo){
      this.tipo_articulo=tipo_articulo;
    }
    public void setTipoArticulo(int tipo_articulo){
  String tipo;
  switch(tipo_articulo){
  case 1:
      tipo="Mueble";
      break;
  case 2:
      tipo="Libro";
      break;
  default:
      tipo="N/D";
    break;
  }
      this.tipo_articulo=tipo;
    }
    public void setUnidades(int unidades){
  this.unidades=unidades;
    }
    public void setPrecio(double precio){
      this.precio=precio;
    }

  /*MÉTODOS GET*/

      public int getReferencia(){
    return referencia;
      }
      public String getNombre(){
    return nombre;
      }
      public String getTipoArticulo(){
    return tipo_articulo;
      }
    public int getUnidades(){
      return unidades;
    }
    public double getPrecio(){
      return precio;
    }

}
