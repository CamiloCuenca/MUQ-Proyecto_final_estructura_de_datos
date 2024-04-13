package Controller;

import Model.enums.Genero;
import Model.enums.Paises;
import Model.enums.TipoProducto;
import Model.objetos.Cliente;
import Model.objetos.Factura;
import Model.objetos.Premio;
import Model.objetos.Producto;
import Model.utils.DataUtils;
import Model.utils.GeneradorFacturas;
import Model.utils.SceneUtils;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaAdministradores implements Initializable {
    @FXML
    private TableColumn<Factura, String> colAnio;

    @FXML
    private TableColumn<Cliente, String> colCiudad;

    @FXML
    private TableColumn<Cliente, String> colCliente;

    @FXML
    private TableColumn<Factura, String> colDia;

    @FXML
    private TableColumn<Cliente, Integer> colEdad;

    @FXML
    private TableColumn<Factura, Integer> colFactura;

    @FXML
    private TableColumn<Cliente, Genero> colGenero;

    @FXML
    private TableColumn<Cliente, String> colIdCliente;

    @FXML
    private TableColumn<Factura, String> colIdFactura;

    @FXML
    private TableColumn<Factura, String> colMes;

    @FXML
    private TableColumn<Cliente, Paises> colPais;

    @FXML
    private TableColumn<Producto, String> colProductos;

    @FXML
    private TableColumn<Producto, TipoProducto> colTipoProducto;

    @FXML
    private TableColumn<Factura, Double> colValorTotal;


    @FXML
    private Button btnCargarfactura;

    @FXML
    private Button btnEliminarFactura;

    @FXML
    private Button btnProcesarFactura;

    @FXML
    private Button btnRegresar;

    @FXML
    private ImageView viwLogo;

    @FXML
    private ImageView viwLogo1;

    @FXML
    private ImageView viwLogo2;

    @FXML
    private ScrollPane scpTabla;

    @FXML
    private static TableView<Factura> tblTabla;


    private static final ObservableList<Factura> listaFacturas = FXCollections.observableArrayList();


    @FXML
    void cargarFactura(ActionEvent event) {
        cargarArchivoCSV();
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

    public static void cargarArchivoCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt", "*.csv")); // Filtro para archivos de texto
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            try {
                System.out.println("Ruta del archivo seleccionado: " + selectedFile.getAbsolutePath());

                // Reemplazar la lectura de archivo con la generación de facturas
                List<Factura> nuevasFacturas = GeneradorFacturas.generarFacturas();
                listaFacturas.addAll(nuevasFacturas);
                tblTabla.setItems(listaFacturas);

            } catch (Exception e) { // Manejo de excepciones genérico
                e.printStackTrace();
            }
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tblTabla.setItems(listaFacturas);
        colIdFactura.setCellValueFactory(new PropertyValueFactory<>("idFactura"));
        colIdCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("Genero"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("Paises"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colProductos.setCellValueFactory(new PropertyValueFactory<>("productos"));
        colTipoProducto.setCellValueFactory(new PropertyValueFactory<>("TipoProducto"));
        colValorTotal.setCellValueFactory(new PropertyValueFactory<>("e valorTotal"));
        colDia.setCellValueFactory(new PropertyValueFactory<>("DIA"));
        colMes.setCellValueFactory(new PropertyValueFactory<>("MES"));
        colAnio.setCellValueFactory(new PropertyValueFactory<>("ANIO"));


        // Configurar la altura de la tabla para que se ajuste automáticamente al contenido




        CoreMethod.girarImagen(viwLogo);
        CoreMethod.girarImagen(viwLogo1);
        CoreMethod.girarImagen(viwLogo2);



    }
}
