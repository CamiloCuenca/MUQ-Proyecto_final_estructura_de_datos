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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CoreMethod.animarComponente(btnIngresar);
        CoreMethod.inicializarEnterKey(txtUsuario, pswContrasena, btnIngresar);
        CoreMethod.girarImagen2(imgLogo);
    }

    @FXML
    void ingresar(ActionEvent event) {
        try {
            String rutaAdministradores = "src/main/resources/CSVFiles/Administradores.csv";

            // Verifica si el administrador está registrado
            String nombre = txtUsuario.getText();
            String contrasena = pswContrasena.getText();

            if (nombre.isEmpty() || contrasena.isEmpty()) {
                throw new ExceptionVerificar("Por favor, llene todos los campos");
            }

            boolean datos = DataUtils.verificarDatosUsuarios(nombre, contrasena, rutaAdministradores);

            if (datos) {
                int tipoAdmin = DataUtils.verificarTipoadmin();

                // Condición que verifica el tipo de administrador
                switch (tipoAdmin) {
                    case 1:
                        cargarVentana("/Controller/ventanaAdministradorFactura.fxml", event);
                        break;
                    case 2:
                        cargarVentana("/Controller/ventanaGestorPremio.fxml", event);
                        break;
                    case 3:
                        cargarVentana("/Controller/ventanaAdministradorRuta.fxml", event);
                        break;
                    default:
                        throw new ExceptionVerificar("Tipo de administrador desconocido");
                }
            } else {
                throw new ExceptionVerificar("Usuario no encontrado");
            }
        } catch (ExceptionVerificar e) {
            lblMensaje.setText(e.getMessage());
            CoreMethod.mostrarErrorTemporalmente(lblMensaje);
        } catch (IOException e) {
            lblMensaje.setText("Error al cargar la ventana: " + e.getMessage());
            CoreMethod.mostrarErrorTemporalmente(lblMensaje);
        } catch (Exception e) {
            lblMensaje.setText("Ha ocurrido un error inesperado: " + e.getMessage());
            CoreMethod.mostrarErrorTemporalmente(lblMensaje);
        }
    }

    private void cargarVentana(String fxmlPath, ActionEvent event) throws IOException {
        try {
            SceneUtils.cargarVentana(fxmlPath);
            // Esconde la ventana actual
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException e) {
            throw new IOException("No se pudo cargar la ventana: " + fxmlPath, e);
        }
    }
}