package Controller;

import Model.objetos.AdminPremio;
import Model.objetos.Factura;
import Model.objetos.GanadorPremio;
import Model.objetos.PersonaPremio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaOptionPremio implements Initializable {
    @FXML
    private AnchorPane anchorPanePremios;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnActualizar;

    @FXML
    private TableColumn<GanadorPremio, String> colCiudad;

    @FXML
    private TableColumn<GanadorPremio, String> colCliente;

    @FXML
    private TableColumn<GanadorPremio, String> colEdad;

    @FXML
    private TableColumn<GanadorPremio, String> colGenero;

    @FXML
    private TableColumn<GanadorPremio, String> colIdCliente;

    @FXML
    private TableColumn<GanadorPremio, String> colIdFactura;

    @FXML
    private TableColumn<GanadorPremio, String> colPais;

    @FXML
    private TableColumn<GanadorPremio, String> colPremio;

    @FXML
    private TableColumn<GanadorPremio, String> colTipoProducto;


    @FXML
    private ScrollPane scpTabla;

    @FXML
    private TableView<GanadorPremio> tblFacturas;

    public static boolean aux = false;

    public static ObservableList<GanadorPremio> ganadorPremioObservableList2;


    ObservableList<GanadorPremio> ganadorPremioObservableList;

    @FXML
    void OnSalir(ActionEvent event) {

    }

    // se hizo algo;

    public static void ActualizarDatosTabla(ArrayList<GanadorPremio> ganadorPremioArrayList) {

        ganadorPremioObservableList2 = FXCollections.observableArrayList(ganadorPremioArrayList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CoreMethod.animarComponente(btnActualizar);
        CoreMethod.animarComponente(btnSalir);
        ganadorPremioObservableList = FXCollections.observableArrayList();

        colIdFactura.setCellValueFactory(new PropertyValueFactory<>("idFactura"));
        colIdCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPremio.setCellValueFactory(new PropertyValueFactory<>("premio"));
        colTipoProducto.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));


        if (aux == true) {

            ganadorPremioObservableList.addAll(ganadorPremioObservableList2);

            tblFacturas.setItems(ganadorPremioObservableList);

        }


    }


    @FXML
    void OnActualizar(ActionEvent event) {

        ArrayList<PersonaPremio> facturaArrayList = new ArrayList<>();
        ArrayList<Factura> facturaArrayList3 = new ArrayList<>();
        AdminPremio adminPremio = new AdminPremio("Santiago", "123", "1234", facturaArrayList3);

        facturaArrayList = adminPremio.FacturaToPersonaPremio();
        //aqui se obtienen las facturas en arrayList <Factura>

        ArrayList<Factura> facturaArrayList1 = adminPremio.convertirFactura(facturaArrayList);

        System.out.println("GANADORES");
        System.out.println(facturaArrayList1);

        System.out.println("Con Premio");

        ArrayList<GanadorPremio> ganadorPremios = adminPremio.devolverGanadorConPremio(facturaArrayList1);

        VentanaOptionPremio.aux = true;

        System.out.println(ganadorPremios);

        ganadorPremioObservableList = ganadorPremioObservableList2;

        tblFacturas.setItems(ganadorPremioObservableList);

    }
}