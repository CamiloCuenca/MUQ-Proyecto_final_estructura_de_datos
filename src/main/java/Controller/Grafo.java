package Controller;

import Model.enums.Paises;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Grafo {

    // Atributos de la clase
    private int MAXIMO_VERTICES; // Número máximo de vértices en el grafo
    private int MAXIMO_ARISTAS; // Número máximo de aristas en el grafo
    private int aristas; // Número actual de aristas en el grafo
    private int matrix[][]; // Matriz de adyacencia que representa las aristas entre vértices

    public Grafo() {

    }

    public static void main(String[] args) {
        launch(args);
    }



    public Grafo(int nroVertices, int nroAristas) {
        MAXIMO_VERTICES = nroVertices;
        MAXIMO_ARISTAS = nroAristas;

        this.aristas = 0;

        // Inicializar la matriz de adyacencia con todas las aristas a cero
        matrix = new int[MAXIMO_VERTICES][MAXIMO_VERTICES];

        for (int i = 0; i < getMAX_VERTICES(); i++) {
            for (int j = 0; j < getMAX_VERTICES(); j++) {
                matrix[i][j] = 0;
            }
        }
    }

    // Método para obtener el número máximo de vértices en el grafo
    public int getMAX_VERTICES() {
        return MAXIMO_VERTICES;
    }

    // Método para obtener el número máximo de aristas en el grafo
    public int getMAX_ARISTAS() {
        return MAXIMO_ARISTAS;
    }

    // Método para insertar una arista entre dos vértices con una distancia dada
    public void insertaArista(String v1, String v2, int dist) throws ArrayIndexOutOfBoundsException, UnsupportedOperationException {
        int indexV1 = getIndexFromVertex(v1);
        int indexV2 = getIndexFromVertex(v2);

        // Verificar si los vértices están dentro del rango permitido
        if (indexV1 < 0 || indexV1 >= MAXIMO_VERTICES || indexV2 < 0 || indexV2 >= MAXIMO_VERTICES) {
            throw new ArrayIndexOutOfBoundsException(
                    "Vertices inválidos, fuera de rango. Rango de vértices: 0 - " + (getMAX_VERTICES() - 1));
        } else if (aristas == MAXIMO_ARISTAS) { // Verificar si se alcanzó el número máximo de aristas
            throw new UnsupportedOperationException("No se puede añadir más aristas");
        } else {
            // Insertar la arista en la matriz de adyacencia
            matrix[indexV1][indexV2] = dist;
        }
    }

    // Método para imprimir la matriz de adyacencia
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

    // Método para obtener el índice de un país en la matriz
    private int getIndexFromVertex(String country) {
        // Puedes agregar más países según sea necesario
        String[] countries = {String.valueOf(Paises.COLOMBIA), String.valueOf(Paises.INDIA), String.valueOf(Paises.ARGENTINA), String.valueOf(Paises.AUSTRALIA), String.valueOf(Paises.CHILE)};
        for (int i = 0; i < countries.length; i++) {
            if (countries[i].equalsIgnoreCase(country)) {
                return i;
            }
        }
        return -1; // Si el país no se encuentra en la lista
    }

    // Método para obtener el nombre de un país a partir de su índice
    String getCountryFromIndex(int index) {

        String[] countries = {String.valueOf(Paises.COLOMBIA), String.valueOf(Paises.INDIA), String.valueOf(Paises.ARGENTINA), String.valueOf(Paises.AUSTRALIA), String.valueOf(Paises.CHILE)};
        if (index >= 0 && index < countries.length) {
            return countries[index];
        } else {
            return "Desconocido";
        }
    }

    // Método para aplicar el algoritmo de Dijkstra desde un vértice de origen
    public ResultadosDijkstra dijkstra(String origen) {
        int[] distancias = new int[MAXIMO_VERTICES];
        boolean[] visitados = new boolean[MAXIMO_VERTICES];
        int origenIndex = getIndexFromVertex(origen);
        String[] rutas = new String[MAXIMO_VERTICES];

        // Inicializar las distancias como infinito y los nodos como no visitados
        for (int i = 0; i < MAXIMO_VERTICES; i++) {
            distancias[i] = Integer.MAX_VALUE;
            rutas[i] = "";
            visitados[i] = false;
        }

        // La distancia desde el origen hacia él mismo es siempre cero
        distancias[origenIndex] = 0;

        // Calcular las distancias más cortas
        for (int count = 0; count < MAXIMO_VERTICES - 1; count++) {
            int u = minDistance(distancias, visitados);
            visitados[u] = true;
            for (int v = 0; v < MAXIMO_VERTICES; v++) {
                if (!visitados[v] && matrix[u][v] != 0 && distancias[u] != Integer.MAX_VALUE && distancias[u] + matrix[u][v] < distancias[v]) {
                    distancias[v] = distancias[u] + matrix[u][v];
                    rutas[v] = rutas[u] + " -> " + getCountryFromIndex(v);
                }
            }
        }
        return new ResultadosDijkstra(distancias,rutas);
    }

    // Método auxiliar para encontrar el vértice con la distancia mínima
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

    // Método para visualizar el grafo utilizando JavaFX
    void displayGraph(AnchorPane pane, Grafo grafo) {


        // Posiciones de los nodos
        double[] posX = {150, 300, 450, 450, 150};
        double[] posY = {100, 50, 100, 250, 250};

        // Crear círculos para representar los nodos
        Circle[] circles = new Circle[grafo.getMAX_VERTICES()];
        for (int i = 0; i < grafo.getMAX_VERTICES(); i++) {
            double x = posX[i];
            double y = posY[i];
            Circle circle = new Circle(x, y, 10);
            circles[i] = circle;
            pane.getChildren().add(circle);

            // Etiqueta con el nombre del país
            Label label = new Label(grafo.getCountryFromIndex(i));
            label.setLayoutX(x - 30); // Ajuste de la posición horizontal
            label.setLayoutY(y + 15); // Ajuste de la posición vertical
            pane.getChildren().add(label);
        }

        // Crear líneas para representar las aristas con pesos
        drawWeightedEdges(pane, circles, grafo);


    }

    // Método para dibujar las aristas con pesos entre los nodos
    private void drawWeightedEdges(Pane pane, Circle[] circles, Grafo grafo) {
        for (int i = 0; i < grafo.getMAX_VERTICES(); i++) {
            for (int j = 0; j < grafo.getMAX_VERTICES(); j++) {
                int weight = grafo.matrix[i][j];
                if (weight != 0) {
                    double x1 = circles[i].getCenterX();
                    double y1 = circles[i].getCenterY();
                    double x2 = circles[j].getCenterX();
                    double y2 = circles[j].getCenterY();

                    // Dibujar la línea
                    Line line = new Line(x1, y1, x2, y2);
                    pane.getChildren().add(line);

                    // Calcular la posición del peso
                    double weightX = (x1 + x2) / 2;
                    double weightY = (y1 + y2) / 2;

                    // Etiqueta con el peso de la arista
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
