package Controller;

import Model.enums.Paises;
import Model.utils.DataUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class VentanaAdministradorRuta implements Initializable {
    @FXML
    public Label lblRuta;

    public TextField txtIncio;
    public AnchorPane ApGra;
    @FXML
    private AnchorPane ApGrafo;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void generaRuta(ActionEvent actionEvent) {
    }
}
