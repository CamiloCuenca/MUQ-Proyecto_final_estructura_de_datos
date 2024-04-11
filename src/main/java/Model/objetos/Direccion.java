package Model.objetos;

import Model.enums.Ciudades;
import Model.enums.Paises;

public class Direccion {

    private Paises pais;

    private Ciudades ciudad;

    private int latitud;

    private int longitud;

    public Direccion(Paises pais, Ciudades ciudad, int latitud, int longitud) {
        this.pais = pais;
        this.ciudad = ciudad;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
}