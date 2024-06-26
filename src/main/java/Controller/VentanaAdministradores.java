package Controller;

import Model.enums.TipoProducto;
import Model.objetos.AdminPremio;
import Model.objetos.Factura;
import Model.objetos.Producto;
import Model.utils.DataUtils;
import Model.utils.GeneradorFacturas;
import Model.utils.SceneUtils;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaAdministradores implements Initializable {

    // Tabla Cargada
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

    // Otros elementos de la interfaz
    @FXML
    private Button btnCargarfactura;
    @FXML
    private Button btnHilo;
    @FXML
    private Button btnProcesarFactura;
    @FXML
    private Button btnRegresar;
    @FXML
    private ImageView viwLogo;
    @FXML
    private Label lblTexto;
    @FXML
    private ImageView viwLogo1;
    @FXML
    private ImageView viwLogo2;
    @FXML
    private ScrollPane scpTablaFactura;
    @FXML
    private AnchorPane anchorPaneAdmi;
    @FXML
    private TableView<Factura> tblFacturas;

    @FXML
    private TableView<?> tblProductos;

    @FXML
    private Label lblReloj;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    // ObservableList para cargar las facturas desde un archivo CSV
    public static final ObservableList<Factura> listaFacturas = FXCollections.observableArrayList(DataUtils.leerFacturasDesdeCSV("src/main/resources/CSVFiles/Facturas.csv"));

    @FXML
    void cargarFactura(ActionEvent event) {
        cargarArchivoCSV();
    }

    public void cargarArchivoCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            try {
                System.out.println("Ruta del archivo seleccionado: " + selectedFile.getAbsolutePath());

                // Generar nuevas facturas y agregarlas a la lista
                List<Factura> nuevasFacturas = GeneradorFacturas.generarFacturas();
                listaFacturas.addAll(nuevasFacturas);
                tblFacturas.setItems(listaFacturas);

            } catch (Exception e) {
                e.printStackTrace();
                mostrarMensajeError("Error al cargar el archivo: " + e.getMessage());
            }
        } else {
            mostrarMensajeError("No se seleccionó ningún archivo.");
        }
    }

    @FXML
    void eliminarFactura(ActionEvent event) {
        // Implementar lógica para eliminar factura
        Factura facturaSeleccionada = tblFacturas.getSelectionModel().getSelectedItem();
        if (facturaSeleccionada != null) {
            listaFacturas.remove(facturaSeleccionada);
            tblFacturas.refresh();
        } else {
            mostrarMensajeError("Seleccione una factura para eliminar.");
        }
    }

    @FXML
    void procesarFactura(ActionEvent event) {
        try {
            AdminPremio.lectorTXT();
            VentanaGestorPremio.aux = true;
            lblTexto.setText("Proceso Completado");
            CoreMethod.mostrarErrorTemporalmente(lblTexto);

            ArrayList<Factura> facturas = DataUtils.leerFacturasDesdeCSV("src/main/resources/CSVFiles/Facturas.csv");
            DataUtils.escribirFacturaCSV(facturas, "src/main/resources/CSVFiles/FacturasProcesadas.csv");

            // Registrar los clientes
            DataUtils.registrarClientes("src/main/resources/CSVFiles/Facturas.csv", "src/main/resources/CSVFiles/Clientes.csv");

            // Eliminar las facturas ya procesadas
            DataUtils.eliminarDatosArchivo("src/main/resources/CSVFiles/Facturas.csv");

        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensajeError("Error al procesar las facturas: " + e.getMessage());
        }
    }

    @FXML
    void controlHilo(ActionEvent event) {
        // Implementar lógica para controlar el hilo
        GeneradorFacturas.iniciarHiloGeneradorFacturas();
    }

    private void inicializarReloj() {
        // Crear un objeto Timeline para actualizar el reloj cada segundo
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String horaActual = sdf.format(new Date());
            lblReloj.setText(horaActual);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void Regresar(ActionEvent event) throws IOException {
        SceneUtils.cargarVentana("/Controller/login.fxml");
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColumnas(tblFacturas, colIdFactura, colIdCliente, colCliente, colEdad, colGenero, colPais, colCiudad,
                colProductos, colTipoProducto, colValorTotal, colDia, colMes, colAnio);
        CoreMethod.girarImagen2(viwLogo);
        CoreMethod.animarComponente(btnCargarfactura);
        CoreMethod.animarComponente(btnHilo);
        CoreMethod.animarComponente(btnRegresar);
        CoreMethod.animarComponente(btnProcesarFactura);
        CoreMethod.girarImagen(img1);
        CoreMethod.girarImagen(img2);

        // Cargar las facturas desde el archivo CSV al inicializar la ventana
        tblFacturas.setItems(listaFacturas);
        // Ajustar la política de redimensionamiento de columnas de la tabla
        tblFacturas.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        // Configurar el ancho preferido de la tabla
        tblFacturas.setPrefWidth(Region.USE_COMPUTED_SIZE);
        inicializarReloj();
    }

    private void configurarColumnas(TableView<Factura> tabla, TableColumn<Factura, String> colIdFactura, TableColumn<Factura, String> colIdCliente, TableColumn<Factura, String> colCliente,
                                    TableColumn<Factura, String> colEdad, TableColumn<Factura, String> colGenero, TableColumn<Factura, String> colPais, TableColumn<Factura, String> colCiudad,
                                    TableColumn<Producto, String> colProductos, TableColumn<Producto, TipoProducto> colTipoProducto, TableColumn<Factura, Double> colValorTotal,
                                    TableColumn<Factura, String> colDia, TableColumn<Factura, String> colMes, TableColumn<Factura, String> colAnio) {
        tabla.setItems(listaFacturas);
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
    }

    @FXML
    void generarFactura(ActionEvent event) {
        GeneradorFacturas.iniciarHiloGeneradorFacturas();
    }

    private void mostrarMensajeError(String mensaje) {
        lblTexto.setText(mensaje);
        CoreMethod.mostrarErrorTemporalmente(lblTexto);
    }
}