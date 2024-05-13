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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaGestorPremio implements Initializable {



    private ArrayList<PersonaPremio> personaPremios = AdminPremio.FacturaToPersonaPremio();
    private ArrayList<Factura>facturas = AdminPremio.convertirFactura(personaPremios);


    public static boolean  aux  = false;



    @FXML
    private Button ButtonBuscar;

    @FXML
    private Button btnSalir;


    //Columna tabla 1- IDCliente
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
    private TableColumn<Producto, String> columProducto;

    @FXML
    private TableColumn<Producto, String> columTipo;

    @FXML
    private TableView<Producto> tblProductos;

    @FXML
    private TextField txtBuscarCliente;

    public ObservableList<Factura> listaFacturas = FXCollections.observableArrayList();

    //Convertir a observable list

    public static ObservableList<Factura> convertirToObservable(ArrayList<Factura>facturas){
        ObservableList<Factura>aux= FXCollections.observableArrayList();

        for(int i=0; i<facturas.size(); i++){
            aux.add(facturas.get(i));
        }

        return aux;
    }


    @FXML
    void OnBuscar(ActionEvent event) {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(aux){
            tblFacturas.setItems(convertirToObservable(facturas));
        }else{
            System.out.println("no se cargaron las facturas");
        }
        //tblFacturas.setItems(convertirToObservable(facturas));
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

        System.out.println(facturas);

        DataUtils.escribirFacturaCSV(facturas,"src/main/resources/CSVFiles/FacturasProcesadas.txt");



    }

    @FXML
    void OnSalir(ActionEvent event) throws IOException {
        SceneUtils.cargarVentana("/Controller/login.fxml");
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void OnGenerar(ActionEvent event) throws IOException {

        Stage stage=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("ventanaOptionPremio.fxml"));
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();

    }

}