package Controller;

import Model.enums.TipoProducto;
import Model.objetos.*;
import Model.utils.DataUtils;
import Model.utils.SceneUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaGestorPremio implements Initializable {

    // Lista de personaPremios y facturas obtenidas al inicio
    private ArrayList<PersonaPremio> personaPremios = AdminPremio.FacturaToPersonaPremio();
    private ArrayList<Factura> facturas = AdminPremio.convertirFactura(personaPremios);

    // Indicador de si se han cargado las facturas
    public static boolean aux = false;

    @FXML
    private TextField txtBuscarCliente;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnGenerarPremio;

    @FXML
    private Button btnSalir;

    // Columnas de la tabla de facturas
    @FXML
    private TableColumn<Factura, String> colAnio;
    @FXML
    private TableColumn<Factura, String> colCiudad;
    @FXML
    private TableColumn<Factura, String> colCliente;
    @FXML
    private TableColumn<Factura, String> colDia;
    @FXML
    private TableColumn<Factura, String> colEdad;
    @FXML
    private TableColumn<Factura, String> colGenero;
    @FXML
    private TableColumn<Factura, String> colIdCliente;
    @FXML
    private TableColumn<Factura, String> colIdFactura;
    @FXML
    private TableColumn<Factura, String> colMes;
    @FXML
    private TableColumn<Factura, String> colPais;
    @FXML
    private TableColumn<Producto, String> colProductos;
    @FXML
    private TableColumn<Producto, TipoProducto> colTipoProducto;
    @FXML
    private TableColumn<Factura, Double> colValorTotal;
    @FXML
    private TableView<Factura> tblFacturas;


    //Tabla Premios:

    @FXML
    private TableView<GanadorPremio> tblPremios;

    @FXML
    private TableColumn<GanadorPremio, String> colPremioFacturaid;

    @FXML
    private TableColumn<GanadorPremio,  String> colPremio;

    @FXML
    private TableColumn<GanadorPremio,  String> colPremioCiudad;

    @FXML
    private TableColumn<GanadorPremio,  String> colPremioCliente;

    @FXML
    private TableColumn<GanadorPremio,  String> colPremioClienteid;

    @FXML
    private TableColumn<GanadorPremio,  String> colPremioEdad;

    @FXML
    private TableColumn<GanadorPremio,  String> colPremioGenero;

    @FXML
    private TableColumn<GanadorPremio,  String> colPremioPais;

    @FXML
    private TableColumn<GanadorPremio,  String> colPremioTipoPremio;



    // Lista observable para almacenar las facturas
    public ObservableList<Factura> listaFacturas = FXCollections.observableArrayList();

    // Método para convertir una lista de facturas en una lista observable
    public static ObservableList<Factura> convertirToObservable(ArrayList<Factura> facturas) {
        ObservableList<Factura> aux = FXCollections.observableArrayList();
        for (int i = 0; i < facturas.size(); i++) {
            aux.add(facturas.get(i));
        }
        return aux;
    }

    @FXML
    void OnBuscar(ActionEvent event) {
        // Lógica para realizar la búsqueda de facturas
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Si las facturas se han cargado previamente, se muestran en la tabla
        if (aux) {
            tblFacturas.setItems(convertirToObservable(facturas));
        } else {
            System.out.println("No se cargaron las facturas");
        }

        // Se configuran las propiedades de las columnas de la tabla de facturas
        colIdFactura.setCellValueFactory(new PropertyValueFactory<>("idFactura"));
        colIdCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getIdCliente()));
        colCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getNombre()));
        colEdad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCliente().getEdad())));
        colGenero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getSexo().name()));
        colPais.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getPais().name()));
        colCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getCiudad()));
        colProductos.setCellValueFactory(new PropertyValueFactory<>("productos"));
        colTipoProducto.setCellValueFactory(new PropertyValueFactory<>("TipoProducto"));
        colValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        colDia.setCellValueFactory(new PropertyValueFactory<>("DIA"));
        colMes.setCellValueFactory(new PropertyValueFactory<>("MES"));
        colAnio.setCellValueFactory(new PropertyValueFactory<>("ANIO"));

        // Se inicializan los efectos visuales de los botones
        CoreMethod.animarComponente(btnSalir);
        CoreMethod.animarComponente(btnBuscar);
        CoreMethod.animarComponente(btnGenerarPremio);

        // Se escribe la lista de facturas en un archivo CSV
        DataUtils.escribirFacturaCSV(facturas, "src/main/resources/CSVFiles/FacturasProcesadas.txt");

        colPremioFacturaid.setCellValueFactory(new PropertyValueFactory<>("idFactura"));
        colPremio.setCellValueFactory(new PropertyValueFactory<>("premio"));
        colPremioCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colPremioCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPremioClienteid.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colPremioEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colPremioGenero.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colPremioPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colPremioTipoPremio.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
    }

    @FXML
    void OnSalir(ActionEvent event) throws IOException {
        // Método para cargar la ventana de inicio de sesión y cerrar la ventana actual
        SceneUtils.cargarVentana("/Controller/login.fxml");
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void OnGenerar(ActionEvent event) throws IOException {
        generarPremio();
    }

    private void generarPremio() {
        ArrayList<PersonaPremio> facturaArrayList = new ArrayList<>();
        ArrayList<Factura> facturaArrayList3 = new ArrayList<>();
        AdminPremio adminPremio = new AdminPremio("Santiago", "123", "1234", facturaArrayList3);

        facturaArrayList = adminPremio.FacturaToPersonaPremio();
        // aquí se obtienen las facturas en ArrayList <Factura>

        ArrayList<Factura> facturaArrayList1 = adminPremio.convertirFactura(facturaArrayList);

        System.out.println("GANADORES");
        System.out.println(facturaArrayList1);

        System.out.println("Con Premio");

        ArrayList<GanadorPremio> ganadorPremios = adminPremio.devolverGanadorConPremio(facturaArrayList1);

        VentanaOptionPremio.aux = true;

        System.out.println(ganadorPremios);

        // Actualizar la tabla de premios
        VentanaOptionPremio.ActualizarDatosTabla(ganadorPremios);

        // Mostrar los ganadores en la tabla de premios
        ObservableList<GanadorPremio> listaGanadores = FXCollections.observableArrayList(ganadorPremios);
        tblPremios.setItems(listaGanadores);
    }
}