package Model.objetos;

import Model.enums.Genero;
import Model.enums.Paises;

public class GanadorPremio {
    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    private String idFactura;

    private Cliente cliente;

    private String premio;


    public GanadorPremio(String idFactura, Cliente cliente, String premio) {
        this.idFactura = idFactura;
        this.cliente = cliente;
        this.premio = premio;
    }


}
