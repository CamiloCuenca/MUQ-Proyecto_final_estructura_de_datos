package Model.objetos;

import Controller.VentanaOptionPremio;
import Model.enums.Genero;
import Model.enums.Paises;
import Model.enums.TipoProducto;
import Model.utils.GeneradorFacturas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GanadorPremio {

    private String idFactura;

    private String idCliente;
    private String nombre;
    private int edad;
    private Genero sexo;
    private Paises pais;
    private String ciudad;

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Genero getSexo() {
        return sexo;
    }

    public void setSexo(Genero sexo) {
        this.sexo = sexo;
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

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    private String premio;

    @Override
    public String toString() {
        return "GanadorPremio{" +
                "idFactura='" + idFactura + '\'' +
                ", idCliente='" + idCliente + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", sexo=" + sexo +
                ", pais=" + pais +
                ", ciudad='" + ciudad + '\'' +
                ", premio='" + premio + '\'' +
                ", tipoProducto=" + tipoProducto +
                '}';
    }

    private TipoProducto tipoProducto;



    public GanadorPremio(String idFactura, String idCliente, String nombre, int edad, Genero sexo, Paises pais, String ciudad, String premio, TipoProducto tipoProducto) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.pais = pais;
        this.ciudad = ciudad;
        this.premio = premio;
        this.tipoProducto = tipoProducto;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }


    //FUNCIONES QUE VAN PARA ADMIN PREMIO



    //BOTON QUE VA PARA EL CONTROLLER DE GESTOR DE PREMIOS


    @FXML
    void OnGenerar(ActionEvent event) throws IOException {

        Stage stage=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("ventanaOptionPremio.fxml"));
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();

    }
}

