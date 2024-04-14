package Controller;

import Model.utils.DataUtils;
import Model.utils.GeneradorFacturas;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {
        CoreMethod.mostrarLogin(primaryStage);
        GeneradorFacturas.iniciarHiloGeneradorFacturas();
        DataUtils.registrarClientes("src/main/resources/CSVFiles/Facturas.txt", "src/main/resources/CSVFiles/Clientes.txt");
    }
    public static void main(String[] args) {
        launch(args);
        //pruebaPrioridad();


    }



}