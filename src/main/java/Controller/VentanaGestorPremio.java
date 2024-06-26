package Controller;

import Model.enums.TipoProducto;
import Model.objetos.*;
import Model.utils.DataUtils;
import Model.utils.GenerarExcel;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class VentanaGestorPremio implements Initializable {

    // Lista de personaPremios y facturas obtenidas al inicio
    private ArrayList<PersonaPremio> personaPremios = AdminPremio.FacturaToPersonaPremio();
    private ArrayList<Factura> facturas = AdminPremio.convertirFactura(personaPremios);
    public static ObservableList<GanadorPremio> ganadorPremioObservableList2;

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

    @FXML
    private Button btnGenerarExcel;

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


    @FXML
    private javafx.scene.image.ImageView imgRegalo1;

    @FXML
    private ImageView imgRegalo2;


    @FXML
    private Label lblReloj;


    //Tabla Premios:

    @FXML
    private TableView<GanadorPremio> tblPremios;

    @FXML
    private TableColumn<GanadorPremio, String> colPremioFacturaid;

    @FXML
    private TableColumn<GanadorPremio, String> colPremio;

    @FXML
    private TableColumn<GanadorPremio, String> colPremioCiudad;

    @FXML
    private TableColumn<GanadorPremio, String> colPremioCliente;

    @FXML
    private TableColumn<GanadorPremio, String> colPremioClienteid;

    @FXML
    private TableColumn<GanadorPremio, String> colPremioEdad;

    @FXML
    private TableColumn<GanadorPremio, String> colPremioGenero;

    @FXML
    private TableColumn<GanadorPremio, String> colPremioPais;

    @FXML
    private TableColumn<GanadorPremio, String> colPremioTipoPremio;

    @FXML
    private Label lblTexto;



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
        CoreMethod.animarComponente(btnGenerarExcel);
        CoreMethod.girarImagen(imgRegalo1);
        CoreMethod.girarImagen(imgRegalo2);
        inicializarReloj();

        // Se escribe la lista de facturas en un archivo CSV
        //DataUtils.escribirFacturaCSV(facturas, "src/main/resources/CSVFiles/CargaAvion.csv");

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
        // Se inicializan dos ArrayLists, uno para las facturas y otro para los ganadores
        ArrayList<PersonaPremio> facturaArrayList = new ArrayList<>();
        ArrayList<Factura> facturaArrayList3 = new ArrayList<>();

        // Se crea una instancia de AdminPremio
        AdminPremio adminPremio = new AdminPremio("Santiago", "123", "1234", facturaArrayList3);

        try {
            // Se obtiene una lista de PersonaPremio a partir de las facturas
            facturaArrayList = adminPremio.FacturaToPersonaPremio();

            // Se obtiene una lista de Factura
            ArrayList<Factura> facturaArrayList1 = adminPremio.convertirFactura(facturaArrayList);

            // Verifica si hay facturas disponibles
            if (facturaArrayList1.isEmpty()) {
                lblTexto.setText("No hay facturas disponibles para generar los premios.");
                return;
            }

            // Se imprime la lista de facturas
            System.out.println("GANADORES");
            System.out.println(facturaArrayList1);
            System.out.println("Con Premio");

            // Se obtiene una lista de GanadorPremio
            ArrayList<GanadorPremio> ganadorPremios = adminPremio.devolverGanadorConPremio(facturaArrayList1);

            // Se establece la variable auxiliar como verdadera
            aux = true;

            // Se imprime la lista de ganadores
            System.out.println(ganadorPremios);

            // Se actualiza la tabla de premios
            ActualizarDatosTabla(ganadorPremios);

            // Se muestra la lista de ganadores en la tabla de premios
            ObservableList<GanadorPremio> listaGanadores = FXCollections.observableArrayList(ganadorPremios);
            tblPremios.setItems(listaGanadores);
            DataUtils.escribirCargaAvion(listaGanadores, "src/main/resources/CSVFiles/CargaAvion.csv");
            DataUtils.eliminarGanadores(listaGanadores, "src/main/resources/CSVFiles/FacturasProcesadas.csv");

            // Actualiza el label con un mensaje de éxito
            lblTexto.setText("Premios generados exitosamente.");

        } catch (Exception e) {
            // Manejo de cualquier excepción no esperada
            lblTexto.setText("Ocurrió un error al generar los premios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Actualiza los datos de la tabla de premios con una nueva lista de ganadores de premios.
     * @param ganadorPremioArrayList La lista de ganadores de premios que se utilizará para actualizar la tabla.
     */
    public static void ActualizarDatosTabla(ArrayList<GanadorPremio> ganadorPremioArrayList) {
        // Se convierte la lista de ganadores de premios en un ObservableList
        ganadorPremioObservableList2 = FXCollections.observableArrayList(ganadorPremioArrayList);
    }

    @FXML
    void OnGenerarExcel(ActionEvent event) {
        String csvFilePath = "src/main/resources/CSVFiles/FacturasProcesadas.csv";
        String excelFilePath = "planilla_carga.xlsx";

        try {
            // Leer datos del CSV y escribir en Excel
            GenerarExcel.writeExcel(GenerarExcel.readCsv(csvFilePath), excelFilePath);
            System.out.println("Archivo Excel generado exitosamente.");

            // Abrir el archivo Excel con el programa predeterminado
            abrirArchivoConProgramaPredeterminado(excelFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Abre el archivo especificado con el programa predeterminado del sistema.
     * @param filePath La ruta del archivo a abrir.
     */
    private void abrirArchivoConProgramaPredeterminado(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("El archivo no existe: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}