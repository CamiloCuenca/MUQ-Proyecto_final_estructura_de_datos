package Controller;

import Model.enums.Ciudades;
import Model.enums.Genero;
import Model.enums.Paises;
import Model.objetos.*;
import Model.utils.DataUtils;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

public class Main extends Application {



    public void start(Stage primaryStage) {
        CoreMethod.mostrarLogin(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
        //pruebaPrioridad();


    }



}