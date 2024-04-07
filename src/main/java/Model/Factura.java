package Model;

import java.util.ArrayList;
import java.util.Date;

public class Factura {
    //Atributos
    private ArrayList<Producto> productos = new ArrayList<>();
    private Date fecha;
    private int numFactura;
    private Cliente cliente;

    //Constructor

    public Factura(ArrayList<Producto> productos, Date fecha, int numFactura, Cliente cliente) {
        this.productos = productos;
        this.fecha = fecha;
        this.numFactura = numFactura;
        this.cliente = cliente;
    }

    //Getters and Setters

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
