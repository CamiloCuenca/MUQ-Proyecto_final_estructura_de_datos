package Controller;

import Model.utils.DataUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    void ingresar(ActionEvent event) {
        String rutaAdminFactura = "src/main/resources/CSVFiles/Administradores.txt";
        boolean datos =  DataUtils.verificarDatosUsuarios(txtNombre.getText(),txtContrasena.getText(),rutaAdminFactura);
        if(datos){
            System.out.println("Funciono");
            System.out.println(DataUtils.verificarTipoadmin());
        }
        else {
            System.out.println("no es correto");
        }
    }

    @FXML
    void registrarse(ActionEvent event) {

    }

}
