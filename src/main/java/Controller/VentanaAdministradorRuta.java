package Controller;

import Model.enums.Paises;
import Model.enums.TipoProducto;
import Model.objetos.Arista;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class VentanaAdministradorRuta implements Initializable {
    @FXML
    public Label lblRuta;

    @FXML
    public TextField txtIncio;
    @FXML
    public TableView<RutaGandor> tblRutas;

    @FXML
    private AnchorPane ApGrafo;

    @FXML
    private Button btnGenerarRuta;

    @FXML
    private javafx.scene.image.ImageView img1;

    @FXML
    private ImageView img2;

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
        CoreMethod.girarImagen(img1);
        CoreMethod.girarImagen(img2);
    }

    @FXML
    public void generaRuta(ActionEvent actionEvent) {
        try {
            String inicioText = txtIncio.getText().trim().toUpperCase();
            if (inicioText.isEmpty()) {
                mostrarMensajeError("Ingrese un país de inicio válido.");
                return;
            }

            Paises origen = convertirAPaises(inicioText);
            if (origen == null) {
                mostrarMensajeError("País de inicio no válido.");
                return;
            }

            List<Vertice<Paises>> vertices = new ArrayList<>();
            for (Paises pais : Paises.values()) {
                vertices.add(new Vertice<>(pais));
            }

            List<Arista<Paises>> aristas = new ArrayList<>();
            for (int i = 0; i < vertices.size(); i++) {
                for (int j = i + 1; j < vertices.size(); j++) {
                    int peso = numeroAleatorio();
                    aristas.add(new Arista<>(vertices.get(i), vertices.get(j), peso));
                }
            }

            DijkstraConPaises dijkstra = new DijkstraConPaises(aristas);
            dijkstra.ejecutarDijkstra(origen);

            Set<String> nombresPaises = DataUtils.leerDestinos("src/main/resources/CSVFiles/CargaAvion.csv");
            Set<Paises> paisesDestino = new HashSet<>();
            for (String nombrePais : nombresPaises) {
                Paises pais = convertirAPaises(nombrePais);
                if (pais != null) {
                    paisesDestino.add(pais);
                }
            }

            dijkstra.visualizarResultados(paisesDestino);
        } catch (Exception e) {
            mostrarMensajeError("Error al generar la ruta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void Regresar(ActionEvent event) throws IOException {
        SceneUtils.cargarVentana("/Controller/login.fxml");
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public static int numeroAleatorio() {
        Random random = new Random();
        return random.nextInt(91) + 10; // 91 porque nextInt(91) genera un número entre 0 y 90
    }

    public static Paises convertirAPaises(String nombrePais) {
        try {
            return Paises.valueOf(nombrePais.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}