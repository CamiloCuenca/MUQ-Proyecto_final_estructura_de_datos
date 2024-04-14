package Controller;

import Model.objetos.Factura;
import Model.objetos.FacturaAux;
import Model.objetos.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaGestorPremio implements Initializable {

    public ArrayList<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(ArrayList<Producto> productoList) {
        this.productoList = productoList;
    }

    private ArrayList<Producto> productoList;

    public VentanaGestorPremio (ArrayList<Producto> productoList){
        this.productoList = productoList;
    }


    public VentanaGestorPremio (){

    }
    @FXML
    private Button ButtonActualizar;

    @FXML
    private Button ButtonBuscar;

    @FXML
    private Button ButtonEscogerPremio;

    @FXML
    private Button ButtonSalir;

    //Columna tabla 1- IDCliente
    @FXML
    private TableColumn<FacturaAux, String> colCliente;



    @FXML
    private TableColumn<FacturaAux, String> colFactura;

    //Columna nombre cliente

    @FXML
    private TableColumn<FacturaAux, String> colNombre;
    //Columna premio que es producto en String
    @FXML
    private TableColumn<FacturaAux, String> colPremio;
    //Tipo producto

    @FXML
    private TableColumn<FacturaAux, String> colTipo;


    @FXML
    private TableColumn<Producto, String> columProducto;

    @FXML
    private TableColumn<Producto, String> columTipo;

    @FXML
    private TableView<FacturaAux> tblPremiosPersonas;

    @FXML
    private TableView<Producto> tblProductos;

    @FXML
    private TextField txtBuscarCliente;

    public ObservableList<Factura> listaFacturas = FXCollections.observableArrayList();

    //Convertir a observable list

    public static ObservableList<Factura> convertirToObservable(ArrayList<Factura>facturas){
        ObservableList<Factura>aux= FXCollections.observableArrayList();

        for(int i=0; i<facturas.size(); i++){
            aux.add(facturas.get(i));
        }

        return aux;
    }


    @FXML
    void OnActualizar(ActionEvent event) {

    }

    @FXML
    void OnBuscar(ActionEvent event) {

    }

    @FXML
    void OnEscoger(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }


}
