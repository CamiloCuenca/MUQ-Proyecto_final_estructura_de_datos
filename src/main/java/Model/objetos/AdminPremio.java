package Model.objetos;

import Model.enums.Genero;

import java.util.ArrayList;

public class AdminPremio extends Usuario{

    private ArrayList<Factura>facturas;

    //Constructor
    public AdminPremio(String nombre, String contrasena, String ID,ArrayList<Factura>facturas) {
        super(nombre, contrasena, ID);
        this.facturas = facturas;
    }
    //Fase beta

    public PersonaPremio generarPersonPremio (Factura factura,int secuencia){

        int prioridad = 0;
        int pais=1;


        if(factura.getCliente().getEdad()>60){
            prioridad=3;

        }else if(factura.getCliente().getSexo()== Genero.HOMBRE){
            prioridad=1;
        }else if(factura.getCliente().getSexo()==Genero.MUJER) {
            prioridad = 2;
        }
        PersonaPremio premio=new PersonaPremio(prioridad,pais,secuencia,factura.getCliente());

        return premio;
    }

    public void generarAtributosPremio (){

    }

    //Métodos
    public void generarCarga(ArrayList<Factura> facturas){}

    public  void generarCSVCarga(){}


    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }
}
