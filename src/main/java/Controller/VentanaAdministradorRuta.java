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

    private Grafo grafo;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /* grafo = new Grafo(8, 20);
        try {
            grafo.insertaArista(String.valueOf(Paises.COLOMBIA), String.valueOf(Paises.INDIA), 1);
            grafo.insertaArista(String.valueOf(Paises.INDIA), String.valueOf(Paises.ARGENTINA), 2);
            grafo.insertaArista(String.valueOf(Paises.CHILE), String.valueOf(Paises.AUSTRALIA), 3);
            grafo.insertaArista(String.valueOf(Paises.AUSTRALIA), String.valueOf(Paises.ARGENTINA), 4);
            grafo.insertaArista(String.valueOf(Paises.CHILE), String.valueOf(Paises.PERU), 5);
            grafo.insertaArista(String.valueOf(Paises.CHILE), String.valueOf(Paises.USA), 6);
            grafo.insertaArista(String.valueOf(Paises.USA), String.valueOf(Paises.CANADA), 7);
            grafo.insertaArista(String.valueOf(Paises.CANADA), String.valueOf(Paises.PERU), 8);
        } catch (ArrayIndexOutOfBoundsException | UnsupportedOperationException e) {
            System.err.println("Error al insertar arista: " + e.getMessage());
        }

        grafo.displayGraph(ApGrafo, grafo);
        grafo.impMatrix();*/

        Paises[] nodos = Paises.values();
        Grafo grafo = new Grafo(nodos);


        System.out.println("Matriz de adyacencia del grafo:");
        grafo.imprimirMatriz();

        int nodoOrigen = 1; // El nodo de origen es CHILE
        String nodoTex = "CHILE";
        Set<String> nodosPasados = DataUtils.leerDestinos("src/main/resources/CSVFiles/CargaAvion.csv");
       nodosPasados.add(nodoTex);
        grafo.dijkstraConRuta(nodoOrigen, nodosPasados);

        ApGrafo.getChildren().add(grafo.dibujarGrafo());
    }


    public void generaRuta(ActionEvent actionEvent) {
    }
}
