package Model.objetos;

import Model.enums.Genero;

public class Cliente {

    private String nombre;

    private int edad;

    private Genero sexo;

    private int id;

    private Direccion direccion;

    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                        ", edad=" + edad +
                        ", sexo=" + sexo +
                        ", id=" + id +
                        ", direccion=" + direccion;
    }

    public Cliente(String nombre, int edad, Genero sexo, int id, Direccion direccion) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.id = id;
        this.direccion = direccion;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
