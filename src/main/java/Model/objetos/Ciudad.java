package Model.objetos;

import java.util.ArrayList;

public class Ciudad {
    //Atributos
    private String nombre;
    private ArrayList<Aeropuerto> aeropuertos = new ArrayList<>();
    //Constructor

    public Ciudad(String nombre, ArrayList<Aeropuerto> aeropuertos) {
        this.nombre = nombre;
        this.aeropuertos = aeropuertos;
    }
    //Getters and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }

    public void setAeropuertos(ArrayList<Aeropuerto> aeropuertos) {
        this.aeropuertos = aeropuertos;
    }
}
