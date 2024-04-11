package Model.objetos;

import Model.enums.Genero;

public class Cliente {

    private String ID;
    private String nombre;
    private int edad;
    private Genero sexo;
    private String pais;
    private String ciudad;


    public Cliente(String ID, String nombre, int edad, Genero sexo, String pais, String ciudad) {
        this.ID = ID;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.pais = pais;
        this.ciudad = ciudad;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Genero getSexo() {
        return sexo;
    }

    public void setSexo(Genero sexo) {
        this.sexo = sexo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "ID='" + ID + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", sexo=" + sexo +
                ", pais='" + pais + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
