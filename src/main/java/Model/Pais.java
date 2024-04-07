package Model;

import java.util.ArrayList;

public class Pais {
    //Atributos
    private  String  nombre;
    private ArrayList<Ciudad> ciudades =  new ArrayList<>();

    //Constructor
    public Pais(String nombre, ArrayList<Ciudad> ciudades) {
        this.nombre = nombre;
        this.ciudades = ciudades;
    }
    //Getters and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(ArrayList<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
}
