package Controller;

import Model.enums.Paises;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class Grafo {

    private int MAXIMO_VERTICES;
    private int MAXIMO_ARISTAS;
    private int aristas;
    private int[][] matrix;

    public Grafo() {}

    public Grafo(int nroVertices, int nroAristas) {
        MAXIMO_VERTICES = nroVertices;
        MAXIMO_ARISTAS = nroAristas;
        this.aristas = 0;
        matrix = new int[MAXIMO_VERTICES][MAXIMO_VERTICES];
        for (int i = 0; i < getMAX_VERTICES(); i++) {
            for (int j = 0; j < getMAX_VERTICES(); j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public int getMAX_VERTICES() {
        return MAXIMO_VERTICES;
    }

    public int getMAX_ARISTAS() {
        return MAXIMO_ARISTAS;
    }

    public void insertaArista(String v1, String v2, int dist) throws ArrayIndexOutOfBoundsException, UnsupportedOperationException {
        int indexV1 = getIndexFromVertex(v1);
        int indexV2 = getIndexFromVertex(v2);

        if (indexV1 < 0 || indexV1 >= MAXIMO_VERTICES || indexV2 < 0 || indexV2 >= MAXIMO_VERTICES) {
            throw new ArrayIndexOutOfBoundsException("Vertices inválidos, fuera de rango. Rango de vértices: 0 - " + (getMAX_VERTICES() - 1));
        } else if (aristas == MAXIMO_ARISTAS) {
            throw new UnsupportedOperationException("No se puede añadir más aristas");
        } else {
            matrix[indexV1][indexV2] = dist;
            this.aristas++;
        }
    }

    public void impMatrix() {
        System.out.print("  ");
        for (int i = 0; i < MAXIMO_VERTICES; i++) {
            System.out.printf("%-12s", getCountryFromIndex(i));
        }
        System.out.println();
        for (int i = 0; i < MAXIMO_VERTICES; i++) {
            System.out.printf("%-12s", getCountryFromIndex(i));
            for (int j = 0; j < MAXIMO_VERTICES; j++) {
                System.out.printf("%-12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private int getIndexFromVertex(String country) {
        for (Paises pais : Paises.values()) {
            if (pais.name().equalsIgnoreCase(country)) {
                return pais.ordinal();
            }
        }
        return -1;
    }

    String getCountryFromIndex(int index) {
        Paises[] countries = Paises.values();
        if (index >= 0 && index < countries.length) {
            return countries[index].name();
        } else {
            return "Desconocido";
        }
    }

    public ResultadosDijkstra dijkstra(String origen) {
        int[] distancias = new int[MAXIMO_VERTICES];
        boolean[] visitados = new boolean[MAXIMO_VERTICES];
        int origenIndex = getIndexFromVertex(origen);
        String[] rutas = new String[MAXIMO_VERTICES];

        if (origenIndex == -1) {
            return new ResultadosDijkstra(distancias, rutas);
        }

        for (int i = 0; i < MAXIMO_VERTICES; i++) {
            distancias[i] = Integer.MAX_VALUE;
            rutas[i] = "";
            visitados[i] = false;
        }

        distancias[origenIndex] = 0;

        for (int count = 0; count < MAXIMO_VERTICES - 1; count++) {
            int u = minDistance(distancias, visitados);
            visitados[u] = true;
            for (int v = 0; v < MAXIMO_VERTICES; v++) {
                if (!visitados[v] && matrix[u][v] != 0 && distancias[u] != Integer.MAX_VALUE && distancias[u] + matrix[u][v] < distancias[v]) {
                    distancias[v] = distancias[u] + matrix[u][v];
                    rutas[v] = rutas[u].isEmpty() ? getCountryFromIndex(v) : rutas[u] + " -> " + getCountryFromIndex(v);
                }
            }
        }
        return new ResultadosDijkstra(distancias, rutas);
    }

    private int minDistance(int[] distancias, boolean[] visitados) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < MAXIMO_VERTICES; v++) {
            if (!visitados[v] && distancias[v] <= min) {
                min = distancias[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    void displayGraph(AnchorPane pane, Grafo grafo) {
        int numVertices = grafo.getMAX_VERTICES();
        double[] posX = new double[numVertices];
        double[] posY = new double[numVertices];
        double centerX = 300;
        double centerY = 200;
        double radius = 100;
        double angleIncrement = 2 * Math.PI / numVertices;

        for (int i = 0; i < numVertices; i++) {
            double angle = i * angleIncrement;
            posX[i] = centerX + radius * Math.cos(angle);
            posY[i] = centerY + radius * Math.sin(angle);
        }

        Circle[] circles = new Circle[grafo.getMAX_VERTICES()];
        for (int i = 0; i < grafo.getMAX_VERTICES(); i++) {
            double x = posX[i];
            double y = posY[i];
            Circle circle = new Circle(x, y, 10);
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.YELLOW);
            circles[i] = circle;
            pane.getChildren().add(circle);

            Label label = new Label(grafo.getCountryFromIndex(i));
            label.setLayoutX(x - 30);
            label.setLayoutY(y + 15);
            pane.getChildren().add(label);
        }

        drawWeightedEdges(pane, circles, grafo);
    }

    private void drawWeightedEdges(Pane pane, Circle[] circles, Grafo grafo) {
        for (int i = 0; i < grafo.getMAX_VERTICES(); i++) {
            for (int j = 0; j < grafo.getMAX_VERTICES(); j++) {
                int weight = grafo.matrix[i][j];
                if (weight != 0) {
                    double x1 = circles[i].getCenterX();
                    double y1 = circles[i].getCenterY();
                    double x2 = circles[j].getCenterX();
                    double y2 = circles[j].getCenterY();

                    Line line = new Line(x1, y1, x2, y2);
                    pane.getChildren().add(line);

                    double weightX = (x1 + x2) / 2;
                    double weightY = (y1 + y2) / 2;

                    Label weightLabel = new Label(String.valueOf(weight));
                    weightLabel.setLayoutX(weightX);
                    weightLabel.setLayoutY(weightY);
                    weightLabel.setFont(Font.font("Arial", 12));
                    weightLabel.setTextFill(Color.BLUE);
                    pane.getChildren().add(weightLabel);
                }
            }
        }
    }
}
