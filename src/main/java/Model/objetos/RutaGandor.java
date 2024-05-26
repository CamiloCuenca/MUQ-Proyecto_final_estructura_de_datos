package Model.objetos;

import Model.enums.Paises;
import Model.enums.TipoProducto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RutaGandor {

    private String idFactura;
    private String idCliente;
    private Paises pais;
    private String ciudad;
    private TipoProducto tipoProducto;
    private String premio;

    public RutaGandor(String idFactura, String idCliente, Paises pais, String ciudad, TipoProducto tipoProducto, String premio) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.pais = pais;
        this.ciudad = ciudad;
        this.tipoProducto = tipoProducto;
        this.premio = premio;
    }

    // Getters y setters
    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    @Override
    public String toString() {
        return "RutaGandor{" +
                "idFactura='" + idFactura + '\'' +
                ", idCliente='" + idCliente + '\'' +
                ", pais=" + pais +
                ", ciudad='" + ciudad + '\'' +
                ", tipoProducto=" + tipoProducto +
                ", premio='" + premio + '\'' +
                '}';
    }
}
