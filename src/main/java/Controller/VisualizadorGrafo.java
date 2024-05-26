package Controller;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedMultigraph;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualizadorGrafo<T> extends JFrame {

    // Grafo que se visualizará
    private DirectedMultigraph<T, DefaultWeightedEdge> graph;
    // Mapa que almacena las posiciones de los vértices en el plano
    private Map<T, Point> vertexPositions;
    // Lista del camino más corto a resaltar
    private List<T> shortestPath;

    /**
     * Constructor de la clase VisualizadorGrafo.
     *
     * @param graph Grafo a visualizar.
     * @param shortestPath Lista del camino más corto a resaltar.
     */
    public VisualizadorGrafo(DirectedMultigraph<T, DefaultWeightedEdge> graph, List<T> shortestPath) {
        this.graph = graph; // Inicializa el grafo
        this.shortestPath = shortestPath; // Inicializa el camino más corto
        this.vertexPositions = new HashMap<>(); // Inicializa el mapa de posiciones de vértices
        initialize(); // Inicializa la interfaz gráfica
        calculateVertexPositions(); // Calcula las posiciones de los vértices
    }

    // Método para inicializar la interfaz gráfica
    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura la operación de cierre
        setSize(800, 600); // Establece el tamaño de la ventana

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Llama al método paintComponent de la superclase
                drawGraph((Graphics2D) g); // Dibuja el grafo
            }
        };

        add(panel); // Añade el panel a la ventana
    }

    // Método para calcular las posiciones de los vértices en el plano
    private void calculateVertexPositions() {
        int vertexCount = graph.vertexSet().size(); // Número de vértices
        int centerX = getWidth() / 2; // Centro X de la ventana
        int centerY = getHeight() / 2; // Centro Y de la ventana
        int radius = Math.min(centerX, centerY) - 50; // Radio del círculo donde se posicionarán los vértices
        double angleIncrement = 2 * Math.PI / vertexCount; // Incremento de ángulo entre vértices

        int i = 0;
        for (T vertex : graph.vertexSet()) {
            int x = (int) (centerX + radius * Math.cos(i * angleIncrement)); // Calcula la posición X
            int y = (int) (centerY + radius * Math.sin(i * angleIncrement)); // Calcula la posición Y
            vertexPositions.put(vertex, new Point(x, y)); // Guarda la posición en el mapa
            i++;
        }
    }

    // Método para dibujar el grafo
    private void drawGraph(Graphics2D g) {
        // Dibuja las aristas y vértices del grafo
        for (DefaultWeightedEdge edge : graph.edgeSet()) {
            T source = graph.getEdgeSource(edge); // Obtiene el vértice de origen
            T target = graph.getEdgeTarget(edge); // Obtiene el vértice de destino

            Point sourcePoint = vertexPositions.get(source); // Obtiene la posición del origen
            Point targetPoint = vertexPositions.get(target); // Obtiene la posición del destino

            if (sourcePoint != null && targetPoint != null) {
                g.setColor(Color.RED); // Establece el color de la arista
                g.drawLine(sourcePoint.x, sourcePoint.y, targetPoint.x, targetPoint.y); // Dibuja la arista

                double weight = graph.getEdgeWeight(edge); // Obtiene el peso de la arista
                int weightX = (sourcePoint.x + targetPoint.x) / 2; // Calcula la posición X del peso
                int weightY = (sourcePoint.y + targetPoint.y) / 2; // Calcula la posición Y del peso
                g.setColor(Color.BLACK); // Establece el color del peso
                g.drawString(String.format("%.1f", weight), weightX, weightY); // Dibuja el peso
            }
        }

        // Dibuja los vértices del grafo
        for (T vertex : graph.vertexSet()) {
            Point vertexPoint = vertexPositions.get(vertex); // Obtiene la posición del vértice
            if (vertexPoint != null) {
                g.setColor(Color.BLACK); // Establece el color del vértice
                g.fillOval(vertexPoint.x - 15, vertexPoint.y - 15, 30, 30); // Dibuja el vértice
                g.setColor(Color.WHITE); // Establece el color del texto
                g.drawString(vertex.toString(), vertexPoint.x - 5, vertexPoint.y + 5); // Dibuja el nombre del vértice
            }
        }

        // Dibuja el camino más corto
        if (shortestPath != null && !shortestPath.isEmpty()) {
            g.setColor(Color.GREEN); // Establece el color del camino más corto
            for (int i = 0; i < shortestPath.size() - 1; i++) {
                T from = shortestPath.get(i); // Obtiene el vértice de origen
                T to = shortestPath.get(i + 1); // Obtiene el vértice de destino
                Point fromPoint = vertexPositions.get(from); // Obtiene la posición del origen
                Point toPoint = vertexPositions.get(to); // Obtiene la posición del destino

                if (fromPoint != null && toPoint != null) {
                    g.drawLine(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y); // Dibuja la línea del camino más corto
                }
            }
        }
    }

    // Método para visualizar el grafo
    public void visualize() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true); // Hace visible la ventana
        });
    }
}
