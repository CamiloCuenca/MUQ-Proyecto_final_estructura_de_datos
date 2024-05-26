package Controller;

import Model.enums.Paises;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.*;



public class Grafo {
    private int cantidadVertices;
    private Paises[] nodos;
    private int[][] matrizAdyacencia;

    private final double radioCirculo = 150;
    private final double centerX = 250;
    private final double centerY = 250;

    public Grafo(Paises[] nodos) {
        this.nodos = nodos;
        this.cantidadVertices = nodos.length;
        this.matrizAdyacencia = new int[cantidadVertices][cantidadVertices];
        inicializarMatriz();
        generarPesosAleatorios();
    }

    private void inicializarMatriz() {
        for (int i = 0; i < cantidadVertices; i++) {
            for (int j = 0; j < cantidadVertices; j++) {
                if (i == j) {
                    matrizAdyacencia[i][j] = 0;
                } else {
                    Random random = new Random();
                    matrizAdyacencia[i][j] = random.nextInt(10) + 1; ;
                }
            }
        }
    }

    public void imprimirMatriz() {
        System.out.print("    ");
        for (int i = 0; i < cantidadVertices; i++) {
            System.out.print(nodos[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < cantidadVertices; i++) {
            System.out.print(nodos[i] + " ");
            for (int j = 0; j < cantidadVertices; j++) {
                System.out.print(matrizAdyacencia[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void dijkstraConRuta(int nodoOrigen, Set<String> nodosPasados) {
        int[] distancia = new int[cantidadVertices];
        boolean[] visitado = new boolean[cantidadVertices];
        int[] padre = new int[cantidadVertices];

        // Inicializar distancias como infinito e inicializar el nodo de origen como 0
        for (int i = 0; i < cantidadVertices; i++) {
            distancia[i] = Integer.MAX_VALUE;
            visitado[i] = false;
        }
        distancia[nodoOrigen] = 0;
        padre[nodoOrigen] = -1;

        // Lista para almacenar los nodos visitados en el orden correcto
        List<Paises> rutaOrdenada = new ArrayList<>();

        // Encontrar el camino más corto para todos los vértices
        for (int count = 0; count < cantidadVertices - 1; count++) {
            int u = minimaDistancia(distancia, visitado);
            visitado[u] = true;

            for (int v = 0; v < cantidadVertices; v++) {
                if (!visitado[v] && matrizAdyacencia[u][v] != 0 && distancia[u] != Integer.MAX_VALUE &&
                        distancia[u] + matrizAdyacencia[u][v] < distancia[v]) {
                    distancia[v] = distancia[u] + matrizAdyacencia[u][v];
                    padre[v] = u;
                }
            }
        }

        imprimirRuta(nodoOrigen,nodosPasados,padre,distancia);
    }

    private int minimaDistancia(int[] distancia, boolean[] visitado) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < cantidadVertices; v++) {
            if (!visitado[v] && distancia[v] <= min) {
                min = distancia[v];
                minIndex = v;
            }
        }
        return minIndex;
    }


    private int getIndex(Paises nodo) {
        for (int i = 0; i < cantidadVertices; i++) {
            if (nodos[i] == nodo) {
                return i;
            }
        }
        return -1;
    }
    private void generarPesosAleatorios() {
        Random random = new Random();
        for (int i = 0; i < cantidadVertices; i++) {
            for (int j = 0; j < cantidadVertices; j++) {
                if (i == j) {
                    matrizAdyacencia[i][j] = 0;
                } else if (matrizAdyacencia[i][j] == 0) {
                    matrizAdyacencia[i][j] = random.nextInt(10) + 1; // Genera un peso aleatorio entre 1 y 10
                    matrizAdyacencia[j][i] = matrizAdyacencia[i][j]; // El grafo es no dirigido
                }
            }
        }
    }

    public AnchorPane dibujarGrafo() {
        AnchorPane grafoPane = new AnchorPane();

        // Calcular las posiciones de los nodos en el círculo
        double angleIncrement = 360.0 / cantidadVertices;
        double angle = 0;

        Map<Paises, double[]> posicionesNodos = new HashMap<>();

        for (Paises nodo : nodos) {
            double x = centerX + radioCirculo * Math.cos(Math.toRadians(angle));
            double y = centerY + radioCirculo * Math.sin(Math.toRadians(angle));

            posicionesNodos.put(nodo, new double[]{x, y});

            Circle circle = new Circle(x, y, 15, Color.BLUE);
            Text text = new Text(x - 5, y + 5, nodo.toString());

            grafoPane.getChildren().addAll(circle, text);

            angle += angleIncrement;
        }

        // Dibujar las aristas
        for (int i = 0; i < cantidadVertices; i++) {
            for (int j = i + 1; j < cantidadVertices; j++) {
                if (matrizAdyacencia[i][j] != 0) {
                    double[] posA = posicionesNodos.get(nodos[i]);
                    double[] posB = posicionesNodos.get(nodos[j]);

                    Line line = new Line(posA[0], posA[1], posB[0], posB[1]);

                    grafoPane.getChildren().add(line);
                }
            }
        }


       // ApGrafo.getChildren().add(grafoPane);
        return grafoPane;
    }

    private void imprimirRuta(int nodoOrigen, Set<String> nodosPasados, int[] padre, int[] distancia) {
        List<Paises> rutaCompleta = new ArrayList<>();
        Paises nodoInicio = nodos[nodoOrigen];

        // Conjunto para mantener los nodos visitados en la ruta completa
        Set<Paises> nodosVisitados = new HashSet<>();

        for (String destino : nodosPasados) {
            if (!nodosPasados.contains(nodos[nodoOrigen].toString())) {
                System.out.println("No se puede llegar al nodo de destino desde el nodo de origen.");
                return;
            }
            List<Paises> ruta = new ArrayList<>();
            int nodoActual = getIndex(Paises.valueOf(destino));

            while (nodoActual != -1) {
                ruta.add(nodos[nodoActual]);
                nodoActual = padre[nodoActual];
            }

            Collections.reverse(ruta);

            // Agregar la ruta de la iteración a la ruta completa
            rutaCompleta.addAll(ruta);

            // Imprimir la ruta de la iteración
            StringBuilder rutaString = new StringBuilder("[");
            for (int i = 0; i < ruta.size(); i++) {
                rutaString.append(ruta.get(i));
                if (i < ruta.size() - 1) {
                    rutaString.append(", ");
                }
            }
            rutaString.append("]");
            System.out.println("Ruta más corta desde " + nodoInicio + " hasta " + destino + ":");
            System.out.println(rutaString.toString() + " ---- TOTAL distancia = " + distancia[getIndex(Paises.valueOf(destino))]);

            // Agregar la ruta de la iteración al conjunto de nodos visitados en la ruta completa
            nodosVisitados.addAll(ruta);

            // Establecer el nodo de origen para la próxima iteración como el último nodo visitado
            nodoOrigen = getIndex(Paises.valueOf(destino));
        }

        // Agregar los nodos visitados en la ruta completa a la lista completa
        rutaCompleta.addAll(nodosVisitados);

        // Imprimir la ruta completa
        StringBuilder rutaCompletaString = new StringBuilder("[");
        for (int i = 0; i < rutaCompleta.size(); i++) {
            rutaCompletaString.append(rutaCompleta.get(i));
            if (i < rutaCompleta.size() - 1) {
                rutaCompletaString.append(", ");
            }
        }
        rutaCompletaString.append("]");
        System.out.println("Ruta completa: " + rutaCompletaString.toString());
    }


}
