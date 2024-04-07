package Model;

public class Cliente {
    //Atributos
    private String nombre;
    private String ID;
    private int puntos;

    //Constructor

    public Cliente(String nombre, String ID, int puntos) {
        this.nombre = nombre;
        this.ID = ID;
        this.puntos = puntos;
    }
    //Getters and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
