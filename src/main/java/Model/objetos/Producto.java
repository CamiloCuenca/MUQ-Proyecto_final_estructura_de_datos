package Model.objetos;

import Model.enums.TipoProducto;

public class Producto {
    //Atributos
    private String nombre;
    private TipoProducto tipo ;

    public Producto(String nombre, TipoProducto tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    //Constructor

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                '}';
    }


    //Getters and Setters



}
