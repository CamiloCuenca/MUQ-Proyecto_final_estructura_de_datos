package Model.utils;

import Model.enums.Genero;
import Model.enums.Paises;
import Model.enums.TipoProducto;
import Model.objetos.Cliente;
import Model.objetos.Factura;
import Model.objetos.GanadorPremio;
import Model.objetos.RutaGandor;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataUtils {
    public static String tipoAdmin;


    /**
     * Esta función verifica si un usuario con el nombre y la contraseña proporcionados existe en un archivo CSV.
     *
     * @param nombre el nombre de usuario a verificar.
     * @param contraseña la contraseña del usuario a verificar.
     * @return true si se encuentra un usuario con el nombre y la contraseña especificados en el archivo CSV; false en caso contrario.
     */
    public static boolean verificarDatosUsuarios(String nombre, String contraseña,String archivoCSV) {
        String separadorCSV = ";"; // Separador utilizado en el archivo CSV

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            // Leer cada línea del archivo CSV
            while ((linea = br.readLine()) != null) {
                String[] datosUsuario = linea.split(separadorCSV);
                // Verificar si hay suficientes campos en la línea
                if (datosUsuario.length >= 3) {
                    String nombreUsuario = datosUsuario[0].trim(); // El método trim() elimina espacios en blanco al inicio y al final
                    String contraseñaUsuario = datosUsuario[1].trim();
                     tipoAdmin = datosUsuario[3].trim();


                    // Comparar nombre de usuario y contraseña
                    if (nombreUsuario.equalsIgnoreCase(nombre) && contraseñaUsuario.equals(contraseña)) {
                        return true; // Usuario encontrado
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Usuario no encontrado en el archivo CSV o contraseña incorrecta
        return false;
    }

    /** Esta función verifica que tipo de administrador es el usuario
     *
     * @return 1 en caso de que sea un administrador de facturas, 2 si es de premios y 3 si es de ruta, en caso contrario 0
     */
    public static int verificarTipoadmin(){
        if(tipoAdmin.equalsIgnoreCase("factura")){
            return 1;
        } else if (tipoAdmin.equalsIgnoreCase("premio")) {
            return 2;
        } else if (tipoAdmin.equalsIgnoreCase("ruta")) {
            return 3;
        }else {
            return 0;
        }
    }

    /** Esta función escribe las facturas que están en un ArrayList de facturas en un archivo csv
     *
     * @param facturas
     * @param rutaArchivo
     */
    public static void escribirFacturaCSV(ArrayList<Factura> facturas, String rutaArchivo) {
        try (FileWriter fw = new FileWriter(rutaArchivo, true);
             PrintWriter pw = new PrintWriter(fw)) {

            for (Factura factura : facturas) {
                // Construir la línea CSV a partir de la factura
                StringBuilder lineaCSV = new StringBuilder();
                lineaCSV.append(factura.getIdFactura()).append(";")
                        .append(factura.getCliente().getIdCliente()).append(";")
                        .append(factura.getCliente().getNombre()).append(";")
                        .append(factura.getCliente().getEdad()).append(";")
                        .append(factura.getCliente().getSexo()).append(";")
                        .append(factura.getCliente().getPais()).append(";")
                        .append(factura.getCliente().getCiudad()).append(";")
                        .append(factura.getProductos()).append(";")
                        .append(factura.getTipoProducto()).append(";")
                        .append(factura.getValorTotal()).append(";")
                        .append(factura.getDIA()).append(";")
                        .append(factura.getMES()).append(";")
                        .append(factura.getANIO());

                // Escribir la línea en el archivo CSV
                pw.println(lineaCSV.toString());
            }

            System.out.println("Facturas escritas en el archivo CSV correctamente.");

        } catch (IOException e) {
            System.err.println("Error al escribir las facturas en el archivo CSV: " + e.getMessage());
        }
    }


    /** Función para escribir el encabezado del archivo CSV
     *
     * @param pw
     */
    private static void escribirEncabezado(PrintWriter pw) {
        pw.println("ID Factura;ID Cliente;Nombre;Edad;Genero;Pais;Ciudad;Productos;ALIMENTOS||TECNOLOGÍA||ELECTRODOMÉSTICOS||COSMÉTICOS;Valor total;DIA;MES;AÑO");
    }

    /** Función para leer un archivo CSV y generar una lista de facturas
     *
     * @param rutaArchivo
     * @return lista de las facturas
     */
    public static ArrayList<Factura> leerFacturasDesdeCSV(String rutaArchivo) {
        ArrayList<Factura> facturas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int numeroFactura = 1; // Contador para el número de factura

            // Leer las líneas del archivo
            while ((linea = br.readLine()) != null) {
                // Imprimir el número de factura actual
                System.out.print("Factura " + numeroFactura + ": ");

                // Dividir la línea en campos utilizando el separador ";"
                String[] campos = linea.split(";");
                // Imprimir cada campo en una línea separada
                for (String campo : campos) {
                    System.out.print(campo + ", ");
                }
                //Crear objeto factura;
                Factura  f = crearFacturaDesdeCampos(campos);
                facturas.add(f);

                // Aumentar el contador de factura para la siguiente factura
                numeroFactura++;
            }

            System.out.println("Facturas leídas del archivo CSV correctamente.");

        } catch (IOException e) {
            // **Se debe poner una alerta de error**
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        return facturas;
    }

    /** Función para crear un objeto Factura a partir de los campos de una línea CSV
     *
     * @param campos
     * @return el objeto factura
     */
    private static Factura crearFacturaDesdeCampos(String[] campos) {
        // Verificar si hay suficientes campos para crear una factura
        if (campos.length < 13) {
            System.err.println("Error: La línea CSV no tiene suficientes campos para crear una factura.");
            return null;
        }
        //Crear un objeto Factura a partir de los campos
        Factura factura = new Factura();
        factura.setIdFactura(campos[0]);
        //Cliente
        Cliente cliente = new Cliente();
        cliente.setIdCliente(campos[1]);
        cliente.setNombre(campos[2]);
        cliente.setEdad(Integer.parseInt(campos[3]));
        cliente.setSexo(Genero.valueOf(campos[4]));
        cliente.setPais(Paises.valueOf(campos[5]));
        cliente.setCiudad(campos[6]);
        //
        factura.setProductos(campos[7]);
        factura.setTipoProducto((campos[8]));
        factura.setValorTotal(Double.parseDouble(campos[9]));
        factura.setDIA(campos[10]);
        factura.setMES(campos[11]);
        factura.setANIO(campos[12]);
        factura.setCliente(cliente);

        return factura;
    }

    public static void main(String[] args) {
        registrarClientes("src/main/resources/CSVFiles/Facturas.csv", "src/main/resources/CSVFiles/Clientes.csv");
    }

    /**
     * Esta función registra los clientes en el archivo clientes.txt
     * @param Facturas
     * @param Clientes
     */
    public static void registrarClientes(String Facturas, String Clientes) {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            // Abrir el archivo de facturas en modo lectura
            br = new BufferedReader(new FileReader(Facturas));
            bw = new BufferedWriter(new FileWriter(Clientes, true)); // Abrir el archivo de clientes en modo escritura, con la opción de añadir al final

            String lineaFactura;
            while ((lineaFactura = br.readLine()) != null) { // Leer cada línea de facturas
                String[] datosFactura = lineaFactura.split(";");
                String idCliente = datosFactura[1];
                String nombreCliente = datosFactura[2];

                if (!clienteRegistrado(idCliente, Clientes)) { // Verificar si el cliente ya está registrado
                    bw.write(idCliente + ";" + nombreCliente); // Escribir el idCliente y el nombreCliente en el archivo de clientes
                    bw.newLine();
                }
            }
            System.out.println("Clientes registrados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al leer/escribir en los archivos: " + e.getMessage());
        } finally {
            // Cerrar los buffers
            try {
                if (br != null) {
                    br.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar los archivos: " + e.getMessage());
            }
        }
    }

    public static boolean clienteRegistrado(String idCliente, String nombreArchivoClientes) {
        BufferedReader br = null;
        try {
            // Abrir el archivo de clientes en modo lectura
            br = new BufferedReader(new FileReader(nombreArchivoClientes));
            String linea;
            while ((linea = br.readLine()) != null) { // Leer el archivo línea por línea para verificar si el cliente ya está registrado
                String[] datosCliente = linea.split(";");
                String id = datosCliente[0];
                if (id.equals(idCliente)) {
                    return true; // El cliente ya está registrado
                }
            }
            return false; // El cliente no está registrado
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return false;
        } finally {
            // Cerrar el buffer de lectura
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }

    public static void eliminarDatosArchivo(String ruta){
        try(FileWriter fileWriter = new FileWriter(ruta, false)){
        // Escribe una cadena vacía para borrar el contenido del archivo
            fileWriter.write("");
        }catch (IOException e){
            System.err.println("Error al borrar el contenido del archivo: " + e.getMessage());
        }
    }


    public static void eliminarGanadores(ObservableList<GanadorPremio> ganadores, String ruta) {
        List<String> lineasRestantes = new ArrayList<>();

        // Leer el archivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String line;

            while ((line = br.readLine()) != null) {
                boolean esGanador = false;
                String[] fields = line.split(";");
                String idFactura = fields[0];

                // Verificar si la línea corresponde a un ganador
                for (GanadorPremio ganador : ganadores) {
                    if (ganador.getIdFactura().equals(idFactura)) {
                        esGanador = true;
                        break;
                    }
                }

                // Si la línea no es de un ganador, añadirla a las líneas restantes
                if (!esGanador) {
                    lineasRestantes.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        // Escribir las líneas restantes de vuelta en el archivo CSV
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (String remainingLine : lineasRestantes) {
                bw.write(remainingLine);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void escribirCargaAvion(ObservableList<GanadorPremio> ganadores,String ruta){
        try (FileWriter fw = new FileWriter(ruta, true);
             PrintWriter pw = new PrintWriter(fw)) {

            for (GanadorPremio ganador : ganadores) {
                // Construir la línea CSV a partir de la factura
                StringBuilder lineaCSV = new StringBuilder();
                lineaCSV.append(ganador.getIdFactura()).append(";")
                        .append(ganador.getIdCliente()).append(";")
                        .append(ganador.getPais()).append(";")
                        .append(ganador.getCiudad()).append(";")
                        .append(ganador.getTipoProducto()).append(";")
                        .append(ganador.getPremio()).append(";");


                // Escribir la línea en el archivo CSV
                pw.println(lineaCSV.toString());
            }

            System.out.println("Ganadores escritos en el archivo CSV correctamente.");

        } catch (IOException e) {
            System.err.println("Error al escribir los ganadores  en el archivo CSV: " + e.getMessage());
        }
    }

    public static Set<String> leerDestinos(String ruta)  {
        Set<String> destinos = new HashSet<>();
        // Leer el archivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String line;

            while ((line = br.readLine()) != null) {
                boolean esGanador = false;
                String[] fields = line.split(";");
                String pais = fields[2];
                destinos.add(pais);
            }


        }catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return destinos;

    }

    /** Función para leer un archivo CSV y generar una lista de rutas
     *
     * @param rutaArchivo
     * @return lista de las rutas
     */
    public static ArrayList<RutaGandor> leerRutasDesdeCSV(String rutaArchivo) {
        ArrayList<RutaGandor> rutas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int numeroRuta = 1; // Contador para el número de ruta

            // Leer las líneas del archivo
            while ((linea = br.readLine()) != null) {
                // Imprimir el número de ruta actual
                System.out.print("Ruta " + numeroRuta + ": ");

                // Dividir la línea en campos utilizando el separador ";"
                String[] campos = linea.split(";");
                // Imprimir cada campo en una línea separada
                for (String campo : campos) {
                    System.out.print(campo + ", ");
                }

                // Crear objeto RutaGandor
                RutaGandor ruta = crearRutaDesdeCampos(campos);
                rutas.add(ruta);

                // Aumentar el contador de ruta para la siguiente ruta
                numeroRuta++;
            }

            System.out.println("Rutas leídas del archivo CSV correctamente.");

        } catch (IOException e) {
            // **Se debe poner una alerta de error**
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        return rutas;
    }

    // Función para crear un objeto RutaGandor desde un array de campos
    private static RutaGandor crearRutaDesdeCampos(String[] campos) {
        String idFactura = campos[0];
        String idCliente = campos[1];
        Paises pais = Paises.valueOf(campos[2].toUpperCase());
        String ciudad = campos[3];
        TipoProducto tipoProducto = TipoProducto.valueOf(campos[4].toUpperCase());
        String premio = campos[5];

        return new RutaGandor(idFactura, idCliente, pais, ciudad, tipoProducto, premio);
    }



}