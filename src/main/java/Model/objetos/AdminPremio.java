package Model.objetos;

import java.util.ArrayList;

public class AdminPremio extends Usuario{

    //Constructor
    public AdminPremio(String nombre, String contrasena, String ID) {
        super(nombre, contrasena, ID);
    }

    //Métodos
    public void generarCarga(ArrayList<Factura> facturas){}

    public  void generarCSVCarga(){}


}
