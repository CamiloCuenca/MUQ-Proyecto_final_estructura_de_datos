package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DataUtils;

import java.io.IOException;

public class Main extends Application {



    public void start(Stage primaryStage) {
        CoreMethod.mostrarLogin(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }

}