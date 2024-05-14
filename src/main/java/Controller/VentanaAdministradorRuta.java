package Controller;

import Model.enums.Paises;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class VentanaAdministradorRuta implements Initializable {
    @FXML
    public Label lblRuta;

    public TextField txtIncio;
    @FXML
    private AnchorPane ApGrafo;

    private Grafo grafo;

    @FXML
    void generaRuta() {


        String origen = txtIncio.getText().toUpperCase(Locale.ROOT);
        System.out.println(origen);
        ResultadosDijkstra resultados = grafo.dijkstra(origen);
        int[] distancias = resultados.getDistancias();
        String[] rutas = resultados.getRutas();

        // Imprimir las distancias más cortas y las rutas
        System.out.println("Distancias más cortas desde " + origen + ":");
        for (int i = 0; i < distancias.length; i++) {
            System.out.println(grafo.getCountryFromIndex(i) + ": " + distancias[i] + " km");
            lblRuta.setText(grafo.getCountryFromIndex(i) + ": " + distancias[i] + " km\n");
        }

        System.out.println("\nRutas desde " + origen + ":");
        for (int i = 0; i < rutas.length; i++) {
            System.out.println(grafo.getCountryFromIndex(i) + ": " + origen + rutas[i]);
            lblRuta.setText(grafo.getCountryFromIndex(i) + ": " + origen + rutas[i]+"\n");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grafo = new Grafo(5, 10);

        // Insertar aristas
        try {
            grafo.insertaArista(String.valueOf(Paises.COLOMBIA), String.valueOf(Paises.INDIA), 7);
            grafo.insertaArista(String.valueOf(Paises.INDIA), String.valueOf(Paises.ARGENTINA), 3);
            grafo.insertaArista(String.valueOf(Paises.CHILE), String.valueOf(Paises.AUSTRALIA), 7);
            grafo.insertaArista(String.valueOf(Paises.AUSTRALIA), String.valueOf(Paises.ARGENTINA), 2);
            grafo.insertaArista(String.valueOf(Paises.CHILE), String.valueOf(Paises.COLOMBIA), 7); // Modificado el vértice de destino para probar
        } catch (ArrayIndexOutOfBoundsException | UnsupportedOperationException e) {
            System.err.println("Error al insertar arista: " + e.getMessage());
        }



        // Visualizar el grafo utilizando JavaFX
        grafo.displayGraph(ApGrafo,grafo);
    }
}
