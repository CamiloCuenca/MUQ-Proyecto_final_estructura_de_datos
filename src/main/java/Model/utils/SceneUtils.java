package Model.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneUtils {

    /** Esta función carga una nueva ventana
     *
     * @param rutaVentana es la ruta de la ventana que se desa abrir
     * @throws IOException
     */
    public static void cargarVentana(String rutaVentana)throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(SceneUtils.class.getResource(rutaVentana)));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
    }
}
