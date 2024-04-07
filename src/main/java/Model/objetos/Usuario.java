package Model.objetos;

public class Usuario {
    //Atributos
    private  String nombre;
    private  String contrasena;
    private String ID;
    //Constructor
    public Usuario(String nombre, String contrasena, String ID) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.ID = ID;
    }
    //Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


}
