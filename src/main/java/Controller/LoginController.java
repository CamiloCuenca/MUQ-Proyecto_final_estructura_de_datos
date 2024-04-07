package Controller;

import Model.utils.DataUtils;
import Model.utils.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginController {

    @FXML
    private AnchorPane anchonPaneLogin;

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private TextField txtContrasena;

    @FXML
    private TextField txtNombre;

    @FXML
    void ingresar(ActionEvent event) throws IOException {
        String rutaAdministradores = "src/main/resources/CSVFiles/Administradores.txt";
        boolean datos =  DataUtils.verificarDatosUsuarios(txtNombre.getText(),txtContrasena.getText(),rutaAdministradores);
        if(datos){
            System.out.println("Funciono");
            System.out.println(DataUtils.verificarTipoadmin());
            //Cargar ventana
            SceneUtils.cargarVentana("/Controller/ventanaAdministradorFactura.fxml");
            // en esta linea, esconde la ventana actual
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
        else {
            System.out.println("no es correto");
        }
    }

    @FXML
    void registrarse(ActionEvent event) {

    }

}
