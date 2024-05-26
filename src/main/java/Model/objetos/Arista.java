package Model.objetos;

import java.util.Objects;

public class Arista<T> implements Comparable<Arista<T>> {
    // Atributo encargado de almacenar el vértice origen.
    private final Vertice<T> origen;
    // Atributo encargado de almacenar el vértice destino.
    private final Vertice<T> destino;
    // Atributo encargado de almacenar el peso de la arista.
    private final double peso;

    /**
     * Constructor.
     *
     * @param origen  Vértice origen de la arista.
     * @param destino Vértice destino de la arista.
     * @param peso    Peso de la arista.
     */
    public Arista(Vertice<T> origen, Vertice<T> destino, double peso) {
        // Se utiliza Objects.requireNonNull para verificar que los vértices no sean nulos.
        this.origen = Objects.requireNonNull(origen, "el vértice origen no puede ser nulo");
        this.destino = Objects.requireNonNull(destino, "el vértice destino no puede ser nulo");
        this.peso = peso;
    }

    /**
     * Método para obtener el vértice origen de la arista.
     *
     * @return Vértice origen.
     */
    public Vertice<T> getOrigen() {
        return origen;
    }

    /**
     * Método para obtener el vértice destino de la arista.
     *
     * @return Vértice destino.
     */
    public Vertice<T> getDestino() {
        return destino;
    }

    /**
     * Método para obtener el peso de la arista.
     *
     * @return Peso de la arista.
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Método de comparación para ordenar las aristas por peso.
     *
     * @param otra La otra arista con la que se compara.
     * @return Un número negativo si esta arista es menor, cero si son iguales, y un número positivo si es mayor.
     */
    @Override
    public int compareTo(Arista<T> otra) {
        return Double.compare(this.peso, otra.peso);
    }

    /**
     * Método para verificar si dos aristas son iguales.
     *
     * @param obj El objeto a comparar.
     * @return true si son iguales, false de lo contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Arista<?> other = (Arista<?>) obj;
        return Double.compare(peso, other.peso) == 0 &&
                origen.equals(other.origen) &&
                destino.equals(other.destino);
    }

    /**
     * Método para calcular el código hash de la arista.
     *
     * @return Código hash de la arista.
     */
    @Override
    public int hashCode() {
        return Objects.hash(origen, destino, peso);
    }

    /**
     * Método para representar la arista como una cadena de caracteres.
     *
     * @return Representación de la arista como cadena de caracteres.
     */
    @Override
    public String toString() {
        return "Punto1.Arista{" +
                "origen=" + origen +
                ", destino=" + destino +
                ", peso=" + peso +
                '}';
    }


}
