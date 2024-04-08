package Controller;

import Model.objetos.Factura;
import Model.utils.DataUtils;
import Model.utils.SceneUtils;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class VentanaAdministradores implements Initializable {

    @FXML
    private TableColumn<Factura, Date> ColFechaFactura;

    @FXML
    private TableColumn<Factura,String> Colfactura;

    @FXML
    private TableColumn<?, ?> colCliente;

    @FXML
    private TableColumn<?, ?> colNumeroFactura;

    @FXML
    private TableColumn<?, ?> colPremios;


    @FXML
    private Button btnCargarfactura;

    @FXML
    private Button btnEliminarFactura;

    @FXML
    private Button btnProcesarFactura;
    @FXML
    private Button btnRegresar;

    @FXML
    private TableView<String> tblFacturas;

    private ObservableList<Factura> listaFacturas = FXCollections.observableArrayList();


    @FXML
    void cargarFactura(ActionEvent event) {
        DataUtils.cargarArchivoCSV();
    }

    @FXML
    void eliminarFactura(ActionEvent event) {

    }

    @FXML
    void procesarFactura(ActionEvent event) {

    }

    public void Regresar(ActionEvent event) throws IOException {
        SceneUtils.cargarVentana("/Controller/login.fxml");
        // en esta linea, esconde la ventana actual
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}
