package Model.objetos;

import Model.enums.TipoProducto;
import Model.objetos.Cliente;

public class Factura {
    //Atributos
    private String idFactura;
    private String productos;
    private TipoProducto tipoProducto;
    private double valorTotal;
    private String DIA;
    private String MES;
    private String ANIO;
    private Cliente cliente;

    //Constructor


    public Factura(String IdFactura, String productos, TipoProducto tipoProducto, double valorTotal, String DIA, String MES, String ANIO, Cliente cliente) {
        this.idFactura = IdFactura;
        this.productos = productos;
        this.tipoProducto = tipoProducto;
        this.valorTotal = valorTotal;
        this.DIA = DIA;
        this.MES = MES;
        this.ANIO = ANIO;
        this.cliente = cliente;
    }

    public Factura() {

    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
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

    public String getANIO() {
        return ANIO;
    }

    public void setANIO(String ANIO) {
        this.ANIO = ANIO;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "ID='" + idFactura + '\'' +
                ", productos='" + productos + '\'' +
                ", tipoProducto=" + tipoProducto +
                ", valorTotal=" + valorTotal +
                ", DIA='" + DIA + '\'' +
                ", MES='" + MES + '\'' +
                ", ANO='" + ANIO + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}
