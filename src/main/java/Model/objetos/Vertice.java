package Model.objetos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vertice<T> {

    // Este atributo se utiliza como genérico y final. Es final porque es importante que el valor de la arista no cambie,
    // y es genérico porque puede tener cualquier tipo de valor (String, Integer, Int, etc.).
    private final T valor;
    // Este atributo se usa con un Set porque no es conveniente que nuestros vértices se repitan.
    // Además, este Set puede contener cualquier tipo de dato.
    private final Set<Vertice<T>> vecinos;
    // Es una bandera para señalar que el  vertice esta siendo visitado.
    private boolean visitado;
    // Es una bandera para señalar que el vertice esta siendo explorado.
    private boolean explorado;

    /**
     * Constructor necesario para crear un vértice.
     *
     * @param valor El valor del vértice.
     */
    public Vertice(T valor) {
        this.valor = Objects.requireNonNull(valor, "El valor no puede ser nulo");
        this.vecinos = new HashSet<>();
        this.visitado = false;
        this.explorado = false;
    }

    /**
     * Método para agregar un vértice vecino.
     *
     * @param vecino El vértice vecino a agregar.
     */
    public void agregarVecino(Vertice<T> vecino) {
        if (vecino != null) {
            vecinos.add(vecino);
        }
    }

    // Getters and Setters
    public T getValor() {
        return valor;
    }

    public Set<Vertice<T>> getVecinos() {
        return vecinos;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public boolean isExplorado() {
        return explorado;
    }

    public void setExplorado(boolean explorado) {
        this.explorado = explorado;
    }

    // Métodos hashCode, equals y toString
    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vertice<?> other = (Vertice<?>) obj;
        return Objects.equals(valor, other.valor);
    }

    @Override
    public String toString() {
        return "Punto1.Vertice{" +
                "valor=" + valor +
                ", visitado=" + visitado +
                ", explorado=" + explorado +
                '}';
    }
}
