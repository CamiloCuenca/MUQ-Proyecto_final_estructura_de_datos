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

    /** Esta función es el evento del boton Ingresar de la ventana de adminsitrador de factura.
     *
     * @param event
     * @throws IOException
     * @throws ExceptionVerificar
     */
    @FXML
    void ingresar(ActionEvent event) throws IOException, ExceptionVerificar {

        try {
            String rutaAdministradores = "src/main/resources/CSVFiles/Administradores.txt";

            //Verific asi el administrador esta registrado
            String nombre = txtUsuario.getText();
            String contrasena = pswContrasena.getText();

            boolean datos = DataUtils.verificarDatosUsuarios(nombre,contrasena,rutaAdministradores);

            if (nombre.isEmpty() && contrasena.isEmpty()) {
                throw new ExceptionVerificar("Llene los campos");
            } else if (nombre.isEmpty() || contrasena.isEmpty()) {
                throw new ExceptionVerificar("Campo vacio llenar porfavor");
            } else {
                if (datos) {
                    System.out.println(DataUtils.verificarTipoadmin());

                    /*Condicion que verifica que tipo de administrador
                    * 1 = Administracion de facturas
                    * 2 = Administracion de premios
                    * 3 = Administrador de Rutas
                    * */

                    if(DataUtils.verificarTipoadmin()==1){
                        //Cargar ventana de admistrador Facturas
                        SceneUtils.cargarVentana("/Controller/ventanaAdministradorFactura.fxml");
                        // en esta linea, esconde la ventana actual
                        ((Node) (event.getSource())).getScene().getWindow().hide();
                    } else if (DataUtils.verificarTipoadmin()==2) {
                        //Cargar ventana de Administrador Premios

                    } else if (DataUtils.verificarTipoadmin()==3) {
                        //Cargar ventana de Administrador de rutas

                    }

                } else {
                    throw new ExceptionVerificar("Usuario no encontrado");
                }

            }
        } catch (ExceptionVerificar e) {
            lblMensaje.setText(e.getMessage());
            CoreMethod.mostrarErrorTemporalmente(lblMensaje);

        }
    }

    /** Función de inicialización de la ventana
     *
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CoreMethod.animarComponente(btnIngresar);
        CoreMethod.animarComponente(txtUsuario);
        CoreMethod.animarComponente(pswContrasena);
        CoreMethod.inicializarEnterKey(txtUsuario, pswContrasena, btnIngresar);
        CoreMethod.girarImagen(imgLogo);


    }
}
