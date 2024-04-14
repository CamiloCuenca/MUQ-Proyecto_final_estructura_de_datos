package Model.objetos;

import Model.enums.Genero;
import Model.enums.Paises;
import Model.enums.TipoProducto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public void lectorTXT (){

        String filePath = "MUQ-Proyecto_final_estructura_de_datos\\src\\main\\resources\\CSVFiles\\Facturas"; // Ruta al archivo de texto

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;


            while ((line = bufferedReader.readLine()) != null) {
                // Dividir la línea en partes usando el delimitador ";"
                String[] parts = line.split(";");

                // Imprimir cada parte
                for (int i=0; i<parts.length; i++){
                    String ID="";
                    String IDFactura="";
                    String productos="";
                    TipoProducto tipoProducto=TipoProducto.ALIMENTO;
                    double valorTotal=0;
                    String DIA="0";
                    String MES="0";
                    String ANO="0";
                    Cliente cliente;

                    //Datos cliente
                    String nombre="";
                    int edad=0;
                    Genero sexo=Genero.OTRO;
                    Paises pais=Paises.COLOMBIA;
                    String ciudad="";
                    if(i==0){
                        IDFactura=parts[0];
                        //51055;718674528;Patricia;78;MUJER;COLOMBIA;Pereira;Refrigerador;ELECTRODOMESTICO;1100000;17;06;2023
                    }else if(i==1){
                        ID=parts[1];
                    }else if(i==2){
                        nombre=parts[2];
                    }else if(i==3){
                        edad=Integer.parseInt(parts[3]);
                    }else if(i==4){
                        if(parts[4].equals("COLOMBIA")){
                            pais=Paises.COLOMBIA;
                        }else if(parts[4].equals("INDIA")){
                            pais=Paises.INDIA;

                        }else if(parts[4].equals("ARGENTINA")) {
                            pais = Paises.ARGENTINA;
                        }
                    }else if(i==5){
                        ciudad=parts[5];
                    }else if(i==7){


                    }else if(i==8){
                        if(parts[7].equals("ALIMENTO")){
                            tipoProducto=TipoProducto.ALIMENTO;
                        }else if(parts[7].equals("COSMETICO")){
                            tipoProducto=TipoProducto.COSMETICO;
                        }else if(parts[7].equals("ELECTRODOMESTICO")){
                            tipoProducto=TipoProducto.ELECTRODOMESTICO;
                        }else if(parts[7].equals("TECNOLOGIA")){
                            tipoProducto=TipoProducto.TECNOLOGIA;
                        }


                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }



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
