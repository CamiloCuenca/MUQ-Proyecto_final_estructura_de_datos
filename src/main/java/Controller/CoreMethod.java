package Controller;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.DepthTest;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CoreMethod {// Esta clase se va encargar de almacenar todos los metodos del proyecto los cuales se van a reutilizar.


    /**
     * Metodo que se encarga de abrir la ventana login en el main
     * @param stage
     */
    public static void mostrarLogin (Stage stage){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 509 );
            stage.setTitle("MUQ");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Funcion para dar animacion a botón
     * @param nodo
     */

    public static void animarComponente(Node nodo) {
        ScaleTransition escalaEntrada = new ScaleTransition(Duration.millis(200), nodo);
        escalaEntrada.setToX(1.1);
        escalaEntrada.setToY(1.1);

        ScaleTransition escalaSalida = new ScaleTransition(Duration.millis(200), nodo);
        escalaSalida.setToX(1);
        escalaSalida.setToY(1);
        nodo.setOnMouseEntered(event -> escalaEntrada.play());
        nodo.setOnMouseExited(event -> escalaSalida.play());
    }

    /**
     * Esta funcion se encarga de que cuando el usuario precione Enter pueda ingresar directamente.
     * @param node1
     * @param node2
     * @param button
     */

    public static void inicializarEnterKey(Node node1, Node node2, Button button) {
        TextField[] camposTexto = {(TextField) node1, (TextField) node2};

        for (TextField campo : camposTexto) {
            campo.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    button.fire();
                }
            });
        }
    }

    /**
     * Metodo que le da animacion a las imagenes de giro.
     * @param imageView
     */

    public static void girarImagen(ImageView imageView) {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(imageView);
        rotate.setDuration(Duration.millis(2200));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.play();
    }

    /**
     * Metodo que establece mostrar mensaje en lbl durante un tiempo (2sg).
     * @param lblMensaje
     */
    public static void mostrarErrorTemporalmente(Label lblMensaje) {
        lblMensaje.setVisible(true);
        Duration delay = Duration.seconds(2);
        KeyFrame keyFrame = new KeyFrame(delay, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lblMensaje.setVisible(false);
            }
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }





}
