package Controller;

import Model.objetos.Factura;
import Model.utils.DataUtils;
import Model.utils.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.Date;

public class VentanaAdministradores {

    @FXML
    private TableColumn<Factura, Date> ColFechaFactura;

    @FXML
    private TableColumn<Factura,String> Colfactura;

    @FXML
    private Button btnCargarfactura;

    @FXML
    private Button btnEliminarFactura;

    @FXML
    private Button btnProcesarFactura;
    @FXML
    private Button btnRegresar;


    @FXML
    private TableView<Factura> tblFacturas;

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
}
