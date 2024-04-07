package Model.objetos;

import Model.enums.TipoProducto;

public class Producto {
    //Atributos
    private String nombre;
    private double valor;
    private TipoProducto tipo ;
    //Constructor
    public Producto(String nombre, double valor, TipoProducto tipo) {
        this.nombre = nombre;
        this.valor = valor;
        this.tipo = tipo;
    }
    //Getters and Setters


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }
}
