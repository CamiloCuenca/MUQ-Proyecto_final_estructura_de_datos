package Controller;

import Model.Exception.ExceptionVerificar;
import Model.utils.DataUtils;
import Model.utils.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    private AnchorPane anchorPaneLogin;

    @FXML
    private AnchorPane anchorPaneD;

    @FXML
    private Button btnIngresar;

    @FXML
    private ImageView imgLogo;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField pswContrasena;

    @FXML
    private Label lblMensaje;


    @FXML
    void ingresar(ActionEvent event) throws IOException, ExceptionVerificar {

        try {

            String rutaAdministradores = "src/main/resources/CSVFiles/Administradores.txt";
            boolean datos = DataUtils.verificarDatosUsuarios(txtUsuario.getText(), pswContrasena.getText(), rutaAdministradores);
            String nombre = txtUsuario.getText();
            String contrasena = pswContrasena.getText();
            if (nombre.isEmpty() && contrasena.isEmpty()) {
                throw new ExceptionVerificar("Llene los campos");
            } else if (nombre.isEmpty() || contrasena.isEmpty()) {
                throw new ExceptionVerificar("Campo vacio llenar porfavor");
            } else {
                if (datos) {
                    System.out.println(DataUtils.verificarTipoadmin());
                    //Cargar ventana
                    SceneUtils.cargarVentana("/Controller/ventanaAdministradorFactura.fxml");
                    // en esta linea, esconde la ventana actual
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else {
                    throw new ExceptionVerificar("Usuario no encontrado");
                }

            }
        } catch (ExceptionVerificar e) {
            lblMensaje.setText(e.getMessage());
            CoreMethod.mostrarErrorTemporalmente(lblMensaje);

        }
    }

    @FXML
    void registrarse(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CoreMethod.animarComponente(btnIngresar);
        CoreMethod.animarComponente(txtUsuario);
        CoreMethod.animarComponente(pswContrasena);
        CoreMethod.inicializarEnterKey(txtUsuario, pswContrasena, btnIngresar);
        CoreMethod.girarImagen(imgLogo);


    }
}
