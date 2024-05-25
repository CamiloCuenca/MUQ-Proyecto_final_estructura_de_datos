package Controller;

import Model.enums.Paises;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class VentanaAdministradorRuta implements Initializable {
    @FXML
    public Label lblRuta;

    public TextField txtIncio;
    public AnchorPane ApGra;
    @FXML
    private AnchorPane ApGrafo;

    private Grafo grafo;

    @FXML
    void generaRuta() {
        String origen = txtIncio.getText().toUpperCase(Locale.ROOT);
        ResultadosDijkstra resultados = grafo.dijkstra(origen);
        int[] distancias = resultados.getDistancias();
        String[] rutas = resultados.getRutas();

        if (distancias.length == 0) {
            lblRuta.setText("País de origen no encontrado.");
            return;
        }

        StringBuilder result = new StringBuilder();
        result.append("Distancias más cortas desde ").append(origen).append(":\n");
        for (int i = 0; i < distancias.length; i++) {
            result.append(grafo.getCountryFromIndex(i)).append(": ").append(distancias[i]).append(" km\n");
        }
        lblRuta.setText(result.toString());

        result.setLength(0);
        result.append("\nRutas desde ").append(origen).append(":\n");
        for (int i = 0; i < rutas.length; i++) {
            result.append(grafo.getCountryFromIndex(i)).append(": ").append(origen).append(rutas[i]).append("\n");
        }
        lblRuta.setText(result.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grafo = new Grafo(8, 20);
        try {
            grafo.insertaArista(String.valueOf(Paises.COLOMBIA), String.valueOf(Paises.INDIA), generarDistanciaAleatoria());
            grafo.insertaArista(String.valueOf(Paises.INDIA), String.valueOf(Paises.ARGENTINA), generarDistanciaAleatoria());
            grafo.insertaArista(String.valueOf(Paises.CHILE), String.valueOf(Paises.AUSTRALIA), generarDistanciaAleatoria());
            grafo.insertaArista(String.valueOf(Paises.AUSTRALIA), String.valueOf(Paises.ARGENTINA), generarDistanciaAleatoria());
            grafo.insertaArista(String.valueOf(Paises.CHILE), String.valueOf(Paises.PERU), generarDistanciaAleatoria());
            grafo.insertaArista(String.valueOf(Paises.CHILE), String.valueOf(Paises.USA), generarDistanciaAleatoria());
            grafo.insertaArista(String.valueOf(Paises.USA), String.valueOf(Paises.CANADA), generarDistanciaAleatoria());
            grafo.insertaArista(String.valueOf(Paises.CANADA), String.valueOf(Paises.PERU), generarDistanciaAleatoria());
        } catch (ArrayIndexOutOfBoundsException | UnsupportedOperationException e) {
            System.err.println("Error al insertar arista: " + e.getMessage());
        }

        grafo.displayGraph(ApGrafo, grafo);
        grafo.impMatrix();
    }

    private static int generarDistanciaAleatoria() {
        return (int) (Math.random() * 10) + 1;
    }
}
