package Model.objetos;

public class Avion {

    //Atributos
    private String nombres;
    private Integer capacidad;
    // private ? ruta;

    //Construcor
    public Avion(String nombres, Integer capacidad) {
        this.nombres = nombres;
        this.capacidad = capacidad;
    }

    //Getters and Setters
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}
