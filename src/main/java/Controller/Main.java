package Controller;

import Model.utils.GeneradorFacturas;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {
        CoreMethod.mostrarLogin(primaryStage);
        GeneradorFacturas.iniciarHiloGeneradorFacturas();
    }

    public static void main(String[] args) {
        launch(args);
        //pruebaPrioridad();

    }



}