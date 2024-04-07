package Model.objetos;

public class Aeropuerto {
    //Atributos
    private String nombre;
    private int distancia;

    //Constructor

    public Aeropuerto(String nombre, int distancia) {
        this.nombre = nombre;
        this.distancia = distancia;
    }

    //Getters and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
}
