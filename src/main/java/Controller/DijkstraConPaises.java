package Controller;

import Model.enums.Paises;
import Model.objetos.Arista;
import Model.objetos.Vertice;
import Model.utils.DataUtils;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.*;

public class DijkstraConPaises {
    private final DirectedMultigraph<Paises, DefaultWeightedEdge> graph; // Grafo
    private final Map<Paises, Double> distancias; // Distancias mínimas desde el origen
    private final Map<Paises, Paises> predecesores; // Predecesores en el camino más corto

    // Constructor
    public DijkstraConPaises(List<Arista<Paises>> aristas) {
        if (aristas == null || aristas.isEmpty()) {
            throw new IllegalArgumentException("La lista de aristas no puede ser nula o vacía.");
        }
        this.graph = crearGrafo(aristas); // Crea el grafo a partir de las aristas
        this.distancias = new HashMap<>(); // Inicializa el mapa de distancias
        this.predecesores = new HashMap<>(); // Inicializa el mapa de predecesores
    }

    // Método estático para crear un grafo a partir de una lista de aristas
    static DirectedMultigraph<Paises, DefaultWeightedEdge> crearGrafo(List<Arista<Paises>> aristas) {
        DirectedMultigraph<Paises, DefaultWeightedEdge> grafo = new DirectedMultigraph<>(DefaultWeightedEdge.class); // Crea un grafo ponderado
        for (Arista<Paises> arista : aristas) {
            if (arista == null || arista.getOrigen() == null || arista.getDestino() == null) {
                throw new IllegalArgumentException("Las aristas y sus vértices no pueden ser nulos.");
            }
            Paises origen = arista.getOrigen().getValor(); // Obtiene el vértice origen de la arista
            Paises destino = arista.getDestino().getValor(); // Obtiene el vértice destino de la arista
            double peso = arista.getPeso(); // Obtiene el peso de la arista

            grafo.addVertex(origen); // Agrega el vértice origen al grafo
            grafo.addVertex(destino); // Agrega el vértice destino al grafo

            // Agrega la arista al grafo
            grafo.setEdgeWeight(grafo.addEdge(origen, destino), peso);
        }
        return grafo; // Devuelve el grafo creado
    }

    // Método para ejecutar el algoritmo de Dijkstra
    public void ejecutarDijkstra(Paises origen) {
        if (!graph.containsVertex(origen)) {
            throw new IllegalArgumentException("El vértice de origen no existe en el grafo.");
        }
        PriorityQueue<Paises> cola = new PriorityQueue<>(Comparator.comparingDouble(distancias::get)); // Cola de prioridad para almacenar los vértices a explorar
        Set<Paises> verticesVisitados = new HashSet<>(); // Conjunto de vértices visitados

        distancias.put(origen, 0.0); // Establece la distancia mínima al origen como 0
        cola.add(origen); // Agrega el origen a la cola

        while (!cola.isEmpty()) { // Mientras haya vértices por explorar en la cola
            Paises actual = cola.poll(); // Obtiene el vértice con la distancia mínima
            if (verticesVisitados.contains(actual)) continue; // Si el vértice ya ha sido visitado, pasa al siguiente

            verticesVisitados.add(actual); // Marca el vértice como visitado

            // Itera sobre los vecinos del vértice actual
            for (DefaultWeightedEdge edge : graph.edgesOf(actual)) {
                Paises vecino = graph.getEdgeTarget(edge); // Obtiene el vecino de la arista
                if (vecino.equals(actual)) {
                    vecino = graph.getEdgeSource(edge); // Si el vecino es igual al actual, obtiene el origen de la arista
                }

                if (verticesVisitados.contains(vecino)) continue; // Si el vecino ya ha sido visitado, pasa al siguiente

                double nuevaDistancia = distancias.get(actual) + graph.getEdgeWeight(edge); // Calcula la nueva distancia
                if (nuevaDistancia < distancias.getOrDefault(vecino, Double.POSITIVE_INFINITY)) {
                    distancias.put(vecino, nuevaDistancia); // Actualiza la distancia mínima al vecino
                    predecesores.put(vecino, actual); // Registra el predecesor del vecino en el camino más corto
                    cola.add(vecino); // Agrega el vecino a la cola para explorar sus vecinos
                }
            }
        }
    }

    // Método para obtener la distancia mínima hasta un nodo destino
    public double obtenerDistanciaMinima(Paises destino) {
        return distancias.getOrDefault(destino, Double.POSITIVE_INFINITY); // Devuelve la distancia mínima o infinito si no hay camino
    }

    // Método para obtener el camino más corto hasta un nodo destino
    public List<Paises> obtenerCaminoMasCorto(Paises destino) {
        List<Paises> camino = new LinkedList<>(); // Lista para almacenar el camino más corto
        Paises paso = destino;

        // Si el nodo destino no tiene predecesor, devuelve una lista vacía
        if (predecesores.get(paso) == null) {
            return camino;
        }

        // Reconstruye el camino desde el nodo destino hasta el origen
        camino.add(paso);
        while (predecesores.get(paso) != null) {
            paso = predecesores.get(paso);
            camino.add(0, paso); // Añade el predecesor al inicio de la lista para mantener el orden del camino
        }
        return camino; // Devuelve el camino más corto
    }

    // Método para visualizar los resultados (camino más corto y distancia mínima)
    public void visualizarResultados(Set<Paises> destino) {
        if (destino == null || destino.isEmpty()) {
            throw new IllegalArgumentException("El conjunto de destinos no puede ser nulo o vacío.");
        }
        List<Paises> camino = null;
        List<Paises> visitados = new ArrayList<>();
        double distancia = 0;

        for (Paises pais : destino) {
            camino = obtenerCaminoMasCorto(pais); // Obtiene el camino más corto hasta el destino
            visitados.add(pais);

            System.out.println("Camino más corto a " + pais + ": " + camino); // Imprime el camino más corto
            distancia += obtenerDistanciaMinima(pais);
            System.out.println("Distancia mínima a " + pais + ": " + obtenerDistanciaMinima(pais)); // Imprime la distancia mínima
        }

        VisualizadorGrafo<Paises> visualizador = new VisualizadorGrafo<>(graph, camino); // Crea un visualizador de grafo
        visualizador.visualize(); // Visualiza el grafo resaltando el camino más corto
    }

    public static void main(String[] args) {
        List<Vertice<Paises>> vertices = new ArrayList<>();
        for (Paises pais : Paises.values()) {
            vertices.add(new Vertice<>(pais));
        }

        List<Arista<Paises>> aristas = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                int peso = numeroAleatorio();
                aristas.add(new Arista<>(vertices.get(i), vertices.get(j), peso));
            }
        }

        DijkstraConPaises dijkstra = new DijkstraConPaises(aristas);

        dijkstra.ejecutarDijkstra(Paises.USA);

        Set<String> nombresPaises = null;
        try {
            nombresPaises = DataUtils.leerDestinos("src/main/resources/CSVFiles/CargaAvion.csv");
        } catch (Exception e) {
            System.err.println("Error leyendo destinos: " + e.getMessage());
            return;
        }

        Set<Paises> paisesDestino = new HashSet<>();
        for (String nombrePais : nombresPaises) {
            try {
                paisesDestino.add(convertirAPaises(nombrePais));
            } catch (IllegalArgumentException e) {
                System.err.println("País desconocido: " + nombrePais);
            }
        }

        dijkstra.visualizarResultados(paisesDestino);
    }

    public static int numeroAleatorio() {
        Random random = new Random();
        return random.nextInt(91) + 10; // 91 porque nextInt(91) genera un número entre 0 y 90
    }

    public static Paises convertirAPaises(String nombrePais) {
        try {
            return Paises.valueOf(nombrePais.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El nombre del país " + nombrePais + " no es válido.");
        }
    }
}