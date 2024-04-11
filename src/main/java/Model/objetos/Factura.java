package Model.objetos;

import java.util.ArrayList;
import java.util.Date;

public class Factura {
    //Atributos
    private String ID ;
    private String productos;
    private String tipoProducto;
    private double valorTotal;
    private String DIA;
    private String MES;
    private String ANO;
    private Cliente cliente;

    //Constructor


    public Factura(String ID, String productos, String tipoProducto, double valorTotal, String DIA, String MES, String ANO, Cliente cliente) {
        this.ID = ID;
        this.productos = productos;
        this.tipoProducto = tipoProducto;
        this.valorTotal = valorTotal;
        this.DIA = DIA;
        this.MES = MES;
        this.ANO = ANO;
        this.cliente = cliente;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDIA() {
        return DIA;
    }

    public void setDIA(String DIA) {
        this.DIA = DIA;
    }

    public String getMES() {
        return MES;
    }

    public void setMES(String MES) {
        this.MES = MES;
    }

    public String getANO() {
        return ANO;
    }

    public void setANO(String ANO) {
        this.ANO = ANO;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
