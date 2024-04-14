package Controller;

import Model.enums.Genero;
import Model.enums.Paises;
import Model.enums.TipoProducto;
import Model.objetos.Cliente;
import Model.objetos.Factura;
import Model.objetos.Producto;
import Model.utils.DataUtils;
import Model.utils.GeneradorFacturas;
import Model.utils.SceneUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaAdministradores implements Initializable {
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
    private TableView<Factura> tblFacturas;

    //  ObservableList de tipo factura que se usara para cargar las facturas de un arraylist en la tabla de factura
    private static final ObservableList<Factura> listaFacturas = FXCollections.observableArrayList(DataUtils.leerFacturasDesdeCSV("src/main/resources/CSVFiles/Facturas.txt"));

    /** Esta función es el evento del botón de cargar un archivo de texto (csv)
     *
     * @param event
     */
    @FXML
    void cargarFactura(ActionEvent event) {
        cargarArchivoCSV();
    }

    /** Esta función carga un archivo de texto al proyecto.
     *
     */
    public void cargarArchivoCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt", "*.csv")); // Filtro para archivos de texto
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            try {
                System.out.println("Ruta del archivo seleccionado: " + selectedFile.getAbsolutePath());

                // Reemplazar la lectura de archivo con la generación de facturas
                List<Factura> nuevasFacturas = GeneradorFacturas.generarFacturas();
                listaFacturas.addAll(nuevasFacturas);
                tblFacturas.setItems(listaFacturas);

            } catch (Exception e) { // Manejo de excepciones genérico
                e.printStackTrace();
            }
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }

    @FXML
    void eliminarFactura(ActionEvent event) {

    }

    @FXML
    void procesarFactura(ActionEvent event) {

    }

    /** Esta función es el evento del botón de regresar, el cual en este caso me regresa a la ventana del login
     *
     * @param event
     * @throws IOException
     */
    public void Regresar(ActionEvent event) throws IOException {
        SceneUtils.cargarVentana("/Controller/login.fxml");
        // en esta linea, esconde la ventana actual
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //listaFacturasCSV.add(DataUtils.escribirFacturaCSV());

        // PropertyValueFactory me vincula los valores de las celdas a las propiedades de los elementos de la tabla

        //Carga de satos a la tabla
        tblFacturas.setItems(listaFacturas);
        colIdFactura.setCellValueFactory(new PropertyValueFactory<>("idFactura"));
        colIdCliente.setCellValueFactory(cellData   -> new SimpleStringProperty(cellData.getValue().getCliente().getIdCliente()));
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


        // Configurar la altura de la tabla para que se ajuste automáticamente al contenido
        CoreMethod.girarImagen(viwLogo);
        CoreMethod.girarImagen(viwLogo1);
        CoreMethod.girarImagen(viwLogo2);



    }
}
