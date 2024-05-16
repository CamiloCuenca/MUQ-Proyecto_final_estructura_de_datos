package Model.objetos;

import Model.enums.Genero;
import Model.enums.Paises;
import Model.enums.TipoProducto;
import Model.utils.GeneradorFacturas; // Importación de la clase GeneradorFacturas

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

import static Controller.VentanaGestorPremio.ActualizarDatosTabla; // Importación de método ActualizarDatosTabla de Controller

// Clase AdminPremio que extiende de Usuario
public class AdminPremio extends Usuario{

    // Lista estática para almacenar las facturas
    public static ArrayList<PersonaPremio>facturaArrayList=new ArrayList<>();

    // Método principal (main)
    public static void main(String[] args) {

        ArrayList<Factura>facturaArrayList3=new ArrayList<>(); // Creación de lista de facturas
        AdminPremio adminPremio=new AdminPremio("Santiago","123","1234",facturaArrayList3); // Creación de instancia AdminPremio

        // Llamada a métodos para procesar facturas y mostrar resultados
        facturaArrayList=adminPremio.FacturaToPersonaPremio(); // Convierte facturas a personas premio
        System.out.println(adminPremio.convertirFactura(facturaArrayList)); // Convierte facturas a premios y los muestra
    }

    private ArrayList<Factura>facturas; // Lista de facturas

    // Constructor
    public AdminPremio(String nombre, String contrasena, String ID,ArrayList<Factura>facturas) {
        super(nombre, contrasena, ID); // Llama al constructor de la superclase Usuario
        this.facturas = facturas; // Inicializa la lista de facturas
    }

    // Método para separar las facturas de ganadores
    public ArrayList<Factura> separarGanadores (ArrayList<Factura>facturas){

        ArrayList<Factura>facturaArrayList=new ArrayList<>();

        for (int i=0;i<facturas.size();i++){
            if(facturas.get(i).getValorTotal()>1000000){ // Verifica si el valor total es mayor a 1000000
                facturaArrayList.add(facturas.get(i)); // Agrega la factura a la lista de facturas de ganadores
            }
        }

        return facturaArrayList; // Retorna la lista de facturas de ganadores
    }

    // Método para generar una PersonaPremio a partir de una factura
    public PersonaPremio generarPersonPremio (Factura factura,int secuencia) {

        int prioridad = 0;
        int pais = 1;

        // Lógica para determinar la prioridad de la PersonaPremio
        if (factura.getCliente().getEdad() > 60) {
            prioridad = 3;
        } else if (factura.getCliente().getSexo() == Genero.HOMBRE) {
            prioridad = 1;
        } else if (factura.getCliente().getSexo() == Genero.MUJER) {
            prioridad = 2;
        }else if (factura.getCliente().getSexo() == Genero.OTRO) {
            prioridad = -1;
        } else if (factura.getCliente().getPais() == Paises.INDIA) {
            prioridad = 3;
        } else if (factura.getCliente().getPais() == Paises.COLOMBIA) {
            prioridad = 2;
        } else if (factura.getCliente().getPais() == Paises.ARGENTINA) {
            prioridad = 1;
        }

        // Creación de una nueva PersonaPremio con los datos calculados
        PersonaPremio premio=new PersonaPremio(prioridad,pais,secuencia,factura.getCliente());

        return premio; // Retorna la PersonaPremio generada
    }

    // Método para convertir las facturas en premios
    public static ArrayList<Factura> convertirFactura (ArrayList<PersonaPremio>personaPremios){

        ArrayList<Factura>facturaArrayList=new ArrayList<>();
        AdminPremio adminPremio=new AdminPremio("Santiago","123","1234",facturaArrayList); // Creación de instancia de AdminPremio

        ArrayList<Factura>facturaArrayList1;
        facturaArrayList1=adminPremio.lectorTXT(); // Lectura de facturas desde archivo

        for (int i=0;i<personaPremios.size();i++){

            Cliente aux=personaPremios.get(i).getCliente();

            // Busca la factura correspondiente a cada PersonaPremio y la agrega a la lista de facturas
            for (int j=0;j<facturaArrayList1.size();j++){
                if(facturaArrayList1.get(j).getCliente().getIdCliente().equals(aux.getIdCliente())){
                    facturaArrayList.add(facturaArrayList1.get(j));
                }
            }

        }

        return facturaArrayList; // Retorna la lista de facturas convertidas en premios
    }

    // Método para convertir las facturas en personas premio
    public static ArrayList<PersonaPremio> FacturaToPersonaPremio() {
        ArrayList<Factura>facturaArrayList=new ArrayList<>();
        AdminPremio adminPremio=new AdminPremio("Santiago","123","1234",facturaArrayList);
        ArrayList<Factura>facturaArrayList2;
        facturaArrayList2= lectorTXT(); // Lee las facturas desde un archivo
        ArrayList<Factura>facturaArrayList1=adminPremio.separarGanadores(facturaArrayList2); // Separa las facturas de ganadores

        PriorityQueue<PersonaPremio>personaPremios=new PriorityQueue<>();

        // Genera una lista de personas premio y la agrega a una cola de prioridad
        for (int i = 0; i < facturaArrayList1.size(); i++) {
            PersonaPremio premio=adminPremio.generarPersonPremio(facturaArrayList1.get(i),i);
            personaPremios.add(premio);
        }

        ArrayList<PersonaPremio>personaPremios1=new ArrayList<>();

        // Extrae las personas premio de la cola y las agrega a una lista
        while (!personaPremios.isEmpty()) {
            PersonaPremio aux=personaPremios.poll();
            personaPremios1.add(aux);
        }

        return personaPremios1; // Retorna la lista de personas premio
    }

    // Método para leer las facturas desde un archivo de texto
    public static ArrayList<Factura> lectorTXT (){

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
                    Genero sexo=Genero.HOMBRE;
                    Paises pais = Paises.COLOMBIA;
                    String ciudad = "";

                    // Imprimir cada parte
                    for (int i = 0; i < parts.length; i++) {

                        if (i == 0) {
                            IDFactura = parts[0];
                        } else if (i == 1) {
                            ID = parts[1];
                        } else if (i == 2) {
                            nombre = parts[2];
                        } else if (i == 3) {
                            edad = Integer.parseInt(parts[3]);

                        } else if (i == 4) {
                            if (parts[4].equals("HOMBRE")) {
                                sexo = Genero.HOMBRE;
                            } else if (parts[4].equals("MUJER")) {
                                sexo = Genero.MUJER;
                            } else if (parts[4].equals("OTRO")) {
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
                    // Creación de un objeto Cliente y un objeto Factura
                    Cliente cliente1 = new Cliente(ID, nombre, edad, sexo, pais, ciudad);
                    Factura factura = new Factura(IDFactura, productos, tipoProducto.toString(), valorTotal, DIA, MES, ANO, cliente1);
                    facturaArrayList.add(factura);
                }
            }
        }catch(IOException e){
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return facturaArrayList; // Retorna la lista de facturas leídas desde el archivo
    }

    // Método para sortear un premio para una persona según el tipo de producto comprado
    public String sortearPremioPersona (TipoProducto producto){
        return GeneradorFacturas.generarNombreAleatorio(producto);
    }

    // Método para devolver una lista de ganadores con premios
    public ArrayList<GanadorPremio> devolverGanadorConPremio (ArrayList<Factura>facturas){

        ArrayList <GanadorPremio>ganadorPremios=new ArrayList<>();

        // Itera sobre las facturas para asignar un premio a cada una
        for (int i = 0; i < facturas.size(); i++) {
            String premio=sortearPremioPersona(convertirTipoEnum(facturas.get(i).getTipoProducto()));
            GanadorPremio ganadorPremio=new GanadorPremio(facturas.get(i).getIdFactura(),
                    facturas.get(i).getCliente().getIdCliente(),facturas.get(i).getCliente().getNombre(),
                    facturas.get(i).getCliente().getEdad(),facturas.get(i).getCliente().getSexo(),
                    facturas.get(i).getCliente().getPais(),facturas.get(i).getCliente().getCiudad(),premio,convertirTipoEnum(facturas.get(i).getTipoProducto()));
            ganadorPremios.add(ganadorPremio); // Agrega el ganador a la lista de ganadores con premios
        }

        // Actualiza la tabla de premios
        ActualizarDatosTabla(ganadorPremios);

        return ganadorPremios; // Retorna la lista de ganadores con premios
    }

    // Método para convertir un tipo de producto de String a TipoProducto enum
    public static TipoProducto convertirTipoEnum(String tipoProducto){
        TipoProducto aux = TipoProducto.ALIMENTO;
        if (tipoProducto.equals("ALIMENTO")){
        } else if (tipoProducto.equals("ELECTRODOMESTICO")) {
            aux = TipoProducto.ELECTRODOMESTICO;
        } else if (tipoProducto.equals("COSMETICO")) {
            aux = TipoProducto.COSMETICO;
        } else if (tipoProducto.equals("TECNOLOGIA")) {
            aux = TipoProducto.TECNOLOGIA;
        }
        return aux;
    }

    // Método para generar atributos de premio
    public void generarAtributosPremio (){}

    // Métodos para generar carga y CSV
    public void generarCarga(ArrayList<Factura> facturas){}
    public void generarCSVCarga(){}

    // Getters y setters
    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }
}