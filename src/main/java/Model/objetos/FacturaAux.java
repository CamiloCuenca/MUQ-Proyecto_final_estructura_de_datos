package Model.objetos;

import Model.enums.TipoProducto;

public class FacturaAux {
    private String ID;
    private String nombre;

    private String IDFactura ;

    private String Premio;
    private TipoProducto tipoProducto;

    public FacturaAux(String ID, String nombre, String IDFactura, String premio, TipoProducto tipoProducto) {
        this.ID = ID;
        this.nombre = nombre;
        this.IDFactura = IDFactura;
        Premio = premio;
        this.tipoProducto = tipoProducto;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIDFactura() {
        return IDFactura;
    }

    public void setIDFactura(String IDFactura) {
        this.IDFactura = IDFactura;
    }

    public String getPremio() {
        return Premio;
    }

    public void setPremio(String premio) {
        Premio = premio;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
}
