package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaGestorPremio implements Initializable {
    @FXML
    private Button ButtonActualizar;

    @FXML
    private Button ButtonBuscar;

    @FXML
    private Button ButtonEscogerPremio;

    @FXML
    private Button ButtonSalir;

    @FXML
    private TableColumn<?, ?> colCantidad;

    @FXML
    private TableColumn<?, ?> colCliente;

    @FXML
    private TableColumn<?, ?> colFactura;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colPremio;

    @FXML
    private TableColumn<?, ?> colProducto;

    @FXML
    private TableColumn<?, ?> colTioo;

    @FXML
    private TableView<?> tblPremiosPersonas;

    @FXML
    private TableView<?> tblProductos;

    @FXML
    private TextField txtBuscarCliente;

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
