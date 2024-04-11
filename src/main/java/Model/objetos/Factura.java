package Model.objetos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura {
    //Atributos
    private ArrayList<Producto> productos = new ArrayList<>();
    private Date fecha;
    private String numFactura;
    private Cliente cliente;

    //Constructor

    public Factura(List<Producto> productos, Date fecha, String numFactura, Cliente cliente) {
        this.productos = (ArrayList<Producto>) productos;
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

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return
                productos +
                        ", fecha= " + fecha +
                        ", numFactura='" + numFactura + '\'' +
                        ", cliente=" + cliente ;
    }
}
