package Controller;

import Model.enums.Paises;
import Model.enums.TipoProducto;
import Model.objetos.Arista;
import Model.objetos.Factura;
import Model.objetos.RutaGandor;
import Model.objetos.Vertice;
import Model.utils.DataUtils;
import Model.utils.SceneUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class VentanaAdministradorRuta implements Initializable {
    @FXML
    public Label lblRuta;

    public TextField txtIncio;
    @FXML
    public TableView<RutaGandor> tblRutas;

    @FXML
    private AnchorPane ApGrafo;


    @FXML
    private Button btnGenerarRuta;

    @FXML
    private TableColumn<RutaGandor, String> colCiudades;

    @FXML
    private TableColumn<RutaGandor, String> colIDClientes;

    @FXML
    private TableColumn<RutaGandor, Paises> colPaises;

    @FXML
    private TableColumn<RutaGandor, String> colPremios;

    @FXML
    private TableColumn<RutaGandor, TipoProducto> colTipoPremios;

    @FXML
    private TableColumn<RutaGandor, String> colFactura;

    @FXML
    private Button btnRegresar;


    public static final ObservableList<RutaGandor> listaFacturas = FXCollections.observableArrayList(DataUtils.leerRutasDesdeCSV("src/main/resources/CSVFiles/CargaAvion.csv"));



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblRutas.setItems(listaFacturas);
        colIDClientes.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colFactura.setCellValueFactory(new PropertyValueFactory<>("idFactura"));
        colPaises.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colCiudades.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        colPremios.setCellValueFactory(new PropertyValueFactory<>("premio"));
        colTipoPremios.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
        CoreMethod.animarComponente(btnRegresar);
        CoreMethod.animarComponente(btnGenerarRuta);
    }


    public void generaRuta(ActionEvent actionEvent) {
        List<Vertice<Paises>> vertices = new ArrayList<>();
        for (Paises pais : Paises.values()) {
            vertices.add(new Vertice<>(pais));
        }

        List<Arista<Paises>> aristas = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                int peso = numeroAleatorio();
                aristas.add(new Arista<>(vertices.get(i), vertices.get(j), peso));
            }
        }

        Paises origen = Paises.valueOf(txtIncio.getText().toUpperCase());
        DijkstraConPaises dijkstra = new DijkstraConPaises(aristas);

        dijkstra.ejecutarDijkstra(Paises.USA);

        Set<String> nombresPaises = DataUtils.leerDestinos("src/main/resources/CSVFiles/CargaAvion.csv");
        // Convertir el Set de String a un Set de Paises
        Set<Paises> paisesDestino = new HashSet<>();
        //paisesDestino.add(origen);
        for (String nombrePais : nombresPaises) {

            paisesDestino.add(convertirAPaises(nombrePais));
        }



        dijkstra.visualizarResultados(paisesDestino);
        // dijkstra.visualizarResultados(Paises.USA);

    }

    public static int numeroAleatorio() {
        Random random = new Random();
        return random.nextInt(91) + 10; // 91 porque nextInt(91) genera un número entre 0 y 90
    }
    public static Paises convertirAPaises(String nombrePais) {
        return Paises.valueOf(nombrePais.toUpperCase());
    }


    @FXML
    void Regresar(ActionEvent event) throws IOException {
        SceneUtils.cargarVentana("/Controller/login.fxml");
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
