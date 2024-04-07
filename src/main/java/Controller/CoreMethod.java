package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CoreMethod {// Esta clase se va encargar de almacenar todos los metodos del proyecto los cuales se van a reutilizar.


    /**
     * Metodo que se encarga de abrir la ventana login en el main
     * @param stage
     */
    public static void mostrarLogin (Stage stage){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 719, 512);
            stage.setTitle("MUQ");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
