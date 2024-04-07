package Model;

public class Premio {
    //Atributos
    private Producto producto;
    private Cliente cliente;
    //Constructor
    public Premio(Producto producto, Cliente cliente) {
        this.producto = producto;
        this.cliente = cliente;
    }
    //Geters and Setters

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
