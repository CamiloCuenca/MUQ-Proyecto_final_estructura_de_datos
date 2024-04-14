package Model.objetos;

import Model.enums.Genero;
import Model.enums.Paises;
import Model.enums.TipoProducto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class AdminPremio extends Usuario{

    public static void main(String[] args) {
        ArrayList<PersonaPremio>facturaArrayList=new ArrayList<>();
        ArrayList<Factura>facturaArrayList3=new ArrayList<>();
        AdminPremio adminPremio=new AdminPremio("Santiago","123","1234",facturaArrayList3);
        /*
        ArrayList<Factura>facturaArrayList1;
        facturaArrayList1=adminPremio.lectorTXT();

        System.out.println(facturaArrayList1);

        PriorityQueue<PersonaPremio>personaPremios=new PriorityQueue<>();


        for (int i = 0; i < facturaArrayList1.size(); i++) {
            PersonaPremio premio=adminPremio.generarPersonPremio(facturaArrayList1.get(i),i);
            personaPremios.add(premio);

        }

        System.out.println(personaPremios);

        System.out.println(personaPremios.size());

        while (!personaPremios.isEmpty()) {
            PersonaPremio aux=personaPremios.poll();
            System.out.println(aux);
            System.out.println();

        }

        System.out.println(adminPremio.convertirFactura(personaPremios));


        System.out.println();

         */

        facturaArrayList=adminPremio.FacturaToPersonaPremio();
        System.out.println(adminPremio.convertirFactura(facturaArrayList));
    }

    private ArrayList<Factura>facturas;

    //Constructor
    public AdminPremio(String nombre, String contrasena, String ID,ArrayList<Factura>facturas) {
        super(nombre, contrasena, ID);
        this.facturas = facturas;
    }
    //Fase beta


    public ArrayList<Factura> separarGanadores (ArrayList<Factura>facturas){

        ArrayList<Factura>facturaArrayList=new ArrayList<>();

        for (int i=0;i<facturas.size();i++){
            if(facturas.get(i).getValorTotal()>1000000){
                facturaArrayList.add(facturas.get(i));
            }

        }

        return facturaArrayList;

    }



    public PersonaPremio generarPersonPremio (Factura factura,int secuencia) {

        int prioridad = 0;
        int pais = 1;

        if (factura.getCliente().getEdad() > 60) {
            prioridad = 3;

        } else if (factura.getCliente().getSexo() == Genero.HOMBRE) {
            prioridad = 1;
        } else if (factura.getCliente().getSexo() == Genero.MUJER) {
            prioridad = 2;
        } else if (factura.getCliente().getPais() == Paises.INDIA) {
            prioridad = 3;
        } else if (factura.getCliente().getPais() == Paises.COLOMBIA) {
            prioridad = 2;
        } else if (factura.getCliente().getPais() == Paises.ARGENTINA) {
            prioridad = 1;
        }

        PersonaPremio premio=new PersonaPremio(prioridad,pais,secuencia,factura.getCliente());

        return premio;


    }

    public ArrayList<Factura> convertirFactura (ArrayList<PersonaPremio>personaPremios){

        ArrayList<Factura>facturaArrayList=new ArrayList<>();
        AdminPremio adminPremio=new AdminPremio("Santiago","123","1234",facturaArrayList);

        ArrayList<Factura>facturaArrayList1;
        facturaArrayList1=adminPremio.lectorTXT();




        for (int i=0;i<personaPremios.size();i++){

            Cliente aux=personaPremios.get(i).getCliente();

            for (int j=0;j<facturaArrayList1.size();j++){
                if(facturaArrayList1.get(j).getCliente().getIdCliente().equals(aux.getIdCliente())){
                    facturaArrayList.add(facturaArrayList1.get(j));

                }
            }

        }

        return facturaArrayList;
    }

    public ArrayList<PersonaPremio> FacturaToPersonaPremio() {
        ArrayList<Factura>facturaArrayList=new ArrayList<>();
        AdminPremio adminPremio=new AdminPremio("Santiago","123","1234",facturaArrayList);
        ArrayList<Factura>facturaArrayList2;
        facturaArrayList2=adminPremio.lectorTXT();
        ArrayList<Factura>facturaArrayList1=adminPremio.separarGanadores(facturaArrayList2);

        System.out.println(facturaArrayList1);

        PriorityQueue<PersonaPremio>personaPremios=new PriorityQueue<>();


        for (int i = 0; i < facturaArrayList1.size(); i++) {
            PersonaPremio premio=adminPremio.generarPersonPremio(facturaArrayList1.get(i),i);
            personaPremios.add(premio);

        }

        System.out.println(personaPremios);

        System.out.println(personaPremios.size());

        ArrayList<PersonaPremio>personaPremios1=new ArrayList<>();

        while (!personaPremios.isEmpty()) {
            PersonaPremio aux=personaPremios.poll();
            personaPremios1.add(aux);
            System.out.println(aux);
            System.out.println();

        }

        System.out.println(personaPremios1);

        return personaPremios1;



    }

    public ArrayList<Factura> lectorTXT (){

        ArrayList<Factura>facturaArrayList=new ArrayList<>();
        String filePath = "src/main/resources/CSVFiles/Facturas.txt"; // Ruta al archivo de texto

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumero = 0;


            while ((line = bufferedReader.readLine()) != null) {
                lineNumero++;

                if (lineNumero > 1) {

                    // Dividir la línea en partes usando el delimitador ";"
                    String[] parts = line.split(";");
                    String ID = "";
                    String IDFactura = "";
                    String productos = "";
                    TipoProducto tipoProducto = TipoProducto.ALIMENTO;
                    double valorTotal = 0;
                    String DIA = "0";
                    String MES = "0";
                    String ANO = "0";
                    Cliente cliente;

                    //Datos cliente
                    String nombre = "";
                    int edad = 1;
                    Genero sexo = Genero.OTRO;
                    Paises pais = Paises.COLOMBIA;
                    String ciudad = "";

                    // Imprimir cada parte
                    for (int i = 0; i < parts.length; i++) {

                        if (i == 0) {
                            IDFactura = parts[0];
                            //51055;718674528;Patricia;78;MUJER;COLOMBIA;Pereira;Refrigerador;ELECTRODOMESTICO;1100000;17;06;2023
                        } else if (i == 1) {
                            ID = parts[1];
                        } else if (i == 2) {
                            nombre = parts[2];
                        } else if (i == 3) {
                            edad = Integer.parseInt(parts[3]);

                        } else if (i == 4) {
                            if (parts[3].equals("HOMBRE")) {
                                sexo = Genero.HOMBRE;
                            } else if (parts[3].equals("MUJER")) {
                                sexo = Genero.MUJER;
                            } else if (parts[3].equals("OTRO")) {
                                sexo = Genero.OTRO;
                            }
                        } else if (i == 5) {
                            if (parts[5].equals("COLOMBIA")) {
                                pais = Paises.COLOMBIA;
                            } else if (parts[5].equals("INDIA")) {
                                pais = Paises.INDIA;

                            } else if (parts[5].equals("ARGENTINA")) {
                                pais = Paises.ARGENTINA;
                            }
                        } else if (i == 6) {
                            ciudad = parts[6];
                        } else if (i == 7) {
                            productos = parts[7];


                        } else if (i == 8) {
                            if (parts[8].equals("ALIMENTO")) {
                                tipoProducto = TipoProducto.ALIMENTO;
                            } else if (parts[8].equals("COSMETICO")) {
                                tipoProducto = TipoProducto.COSMETICO;
                            } else if (parts[8].equals("ELECTRODOMESTICO")) {
                                tipoProducto = TipoProducto.ELECTRODOMESTICO;
                            } else if (parts[8].equals("TECNOLOGIA")) {
                                tipoProducto = TipoProducto.TECNOLOGIA;
                            }
                        } else if (i == 9) {
                            valorTotal = Double.parseDouble(parts[9]);
                        } else if (i == 10) {
                            DIA = parts[10];

                        } else if (i == 11) {
                            MES = parts[11];
                        } else if (i == 12) {
                            ANO = parts[12];
                        }
                    }
                    Cliente cliente1 = new Cliente(ID, nombre, edad, sexo, pais, ciudad);
                    Factura factura = new Factura(IDFactura, productos, tipoProducto, valorTotal, DIA, MES, ANO, cliente1);
                    facturaArrayList.add(factura);
                }
            }
        }catch(IOException e){
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return facturaArrayList;



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
