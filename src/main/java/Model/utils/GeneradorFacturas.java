package Model.utils;

import Controller.VentanaAdministradores;
import Model.enums.Genero;
import Model.enums.Paises;
import Model.enums.TipoProducto;
import Model.objetos.Cliente;
import Model.objetos.Factura;
import Model.objetos.Producto;
import javafx.application.Platform;
import javafx.scene.control.Label;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static Controller.VentanaAdministradores.listaFacturas;

public class GeneradorFacturas {

    private static final Random random = new Random();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // Arreglos de nombres para hombres y mujeres
    private static final String[] hombres = {"Juan", "Carlos", "Luis", "Pedro", "Diego", "Manuel", "Javier", "Miguel", "José", "Antonio"};
    private static final String[] mujeres = {"María", "Ana", "Laura", "Sofía", "Elena", "Isabel", "Carmen", "Paula", "Lucía", "Marta"};

    // Arreglos de nombres de productos por tipo
    private static final String[] alimentos = {"Leche", "Pan", "Huevos", "Arroz", "Frijoles", "Manzanas", "Pasta", "Carne", "Pollo", "Cereal"};
    private static final String[] electrodomesticos = {"Lavadora", "Refrigerador", "Televisor", "Licuadora", "Microondas", "Horno", "Aspiradora", "Batidora", "Secadora", "Cafetera"};
    private static final String[] cosmeticos = {"Crema facial", "Shampoo", "Acondicionador", "Maquillaje", "Perfume", "Desodorante", "Gel de ducha", "Cepillo de dientes", "Cremas corporales", "Protector solar"};
    private static final String[] tecnologia = {"Smartphone", "Laptop", "Tablet", "Cámara digital", "Altavoces inteligentes", "Auriculares inalámbricos", "Consola de videojuegos", "Impresora", "Router", "Smartwatch"};

   /* // Arreglos de ciudades por país
    private static final String[] ciudadIndia = {"Nueva Delhi", "Bombay", "Bangalore", "Calcuta", "Chennai", "Pune", "Hyderabad"};
    private static final String[] ciudadChile = {"Santiago", "Valparaíso", "Concepción", "La Serena", "Antofagasta", "Viña del Mar", "Temuco"};
    private static final String[] ciudadColombia = {"Bogotá", "Medellín", "Cali", "Barranquilla", "Cartagena", "Bucaramanga", "Pereira"};
    private static final String[] ciudadAustralia = {"Sídney", "Melbourne", "Brisbane", "Perth", "Adelaida", "Gold Coast", "Canberra"};
    private static final String[] ciudadArgentina = {"Buenos Aires", "Córdoba", "Rosario", "Mendoza", "San Miguel de Tucumán", "La Plata", "Mar del Plata"};
*/
   // Mapa que asocia cada país con sus respectivas ciudades
   private static final Map<Paises, String[]> ciudadesPorPais = new HashMap<>();
    static {
        ciudadesPorPais.put(Paises.INDIA, new String[]{"Nueva Delhi", "Bombay", "Bangalore", "Calcuta", "Chennai", "Pune", "Hyderabad"});
        ciudadesPorPais.put(Paises.CHILE, new String[]{"Santiago", "Valparaíso", "Concepción", "La Serena", "Antofagasta", "Viña del Mar", "Temuco"});
        ciudadesPorPais.put(Paises.COLOMBIA, new String[]{"Bogotá", "Medellín", "Cali", "Barranquilla", "Cartagena", "Bucaramanga", "Pereira"});
        ciudadesPorPais.put(Paises.AUSTRALIA, new String[]{"Sídney", "Melbourne", "Brisbane", "Perth", "Adelaida", "Gold Coast", "Canberra"});
        ciudadesPorPais.put(Paises.ARGENTINA, new String[]{"Buenos Aires", "Córdoba", "Rosario", "Mendoza", "San Miguel de Tucumán", "La Plata", "Mar del Plata"});
        //ciudadesPorPais.put(Paises.VENEZUELA, new String[]{"Caracas", "Maracaibo", "Valencia", "Barquisimeto", "Maracay", "San Cristóbal", "Ciudad Guayana", "Maturín", "Barinas", "Cumana"});
        ciudadesPorPais.put(Paises.PERU, new String[]{"Lima", "Arequipa", "Trujillo", "Chiclayo", "Iquitos", "Piura", "Cusco", "Tacna", "Chimbote", "Huancayo"});
        ciudadesPorPais.put(Paises.USA, new String[]{"Nueva York", "Los Ángeles", "Chicago", "Houston", "Filadelfia", "Phoenix", "San Antonio", "San Diego", "Dallas", "San José"});
        ciudadesPorPais.put(Paises.CANADA, new String[]{"Toronto", "Montreal", "Vancouver", "Calgary", "Edmonton", "Ottawa", "Quebec", "Winnipeg", "Hamilton", "Kitchener"});
        //ciudadesPorPais.put(Paises.URUGUAY, new String[]{"Montevideo", "Salto", "Paysandú", "Las Piedras", "Rivera", "Maldonado", "Tacuarembó", "Melilla", "Mercedes", "Artigas"});
        //ciudadesPorPais.put(Paises.ECUADOR, new String[]{"Quito", "Guayaquil", "Cuenca", "Santo Domingo", "Machala", "Portoviejo", "Manta", "Durán", "Esmeraldas", "Ambato"});
        //ciudadesPorPais.put(Paises.BOLIVIA, new String[]{"Santa Cruz de la Sierra", "La Paz", "Cochabamba", "Sucre", "Oruro", "Tarija", "Potosí", "Sacaba", "Montero", "Quillacollo"});
        //ciudadesPorPais.put(Paises.PARAGUAY, new String[]{"Asunción", "Ciudad del Este", "San Lorenzo", "Luque", "Capiatá", "Lambaré", "Fernando de la Mora", "Limpio", "Ñemby", "Encarnación"});
        //ciudadesPorPais.put(Paises.JAPON, new String[]{"Tokio", "Yokohama", "Osaka", "Nagoya", "Sapporo", "Kioto", "Fukuoka", "Kawasaki", "Saitama", "Hiroshima"});
        //ciudadesPorPais.put(Paises.CHINA, new String[]{"Shanghái", "Pekín", "Cantón", "Shenzhen", "Tianjin", "Wuhan", "Chongqing", "Chengdú", "Nanjing", "Hong Kong"});
        //ciudadesPorPais.put(Paises.SURCOREA, new String[]{"Seúl", "Busan", "Incheon", "Daegu", "Daejeon", "Gwangju", "Suwon", "Ulsan", "Changwon", "Seongnam"});
        //ciudadesPorPais.put(Paises.RUSIA, new String[]{"Moscú", "San Petersburgo", "Novosibirsk", "Ekaterimburgo", "Nizhni Nóvgorod", "Kazán", "Cheliábinsk", "Omsk", "Samara", "Rostov del Don"});
        //ciudadesPorPais.put(Paises.ESPAÑA, new String[]{"Madrid", "Barcelona", "Valencia", "Sevilla", "Zaragoza", "Málaga", "Murcia", "Palma de Mallorca", "Bilbao", "Alicante"});
    }

    /**
     * Genera las facturas con atributos aleatorios.
     * @return lista de facturas generadas.
     */
    public static List<Factura> generarFacturas() {
        List<Factura> listaFacturas = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Cliente cliente = generarClientesAletoreos().getFirst(); // Obtener un cliente aleatorio
            List<Producto> productos = generarProductosAleatorios(); // Generar productos aleatorios

            Factura factura = new Factura(generarIdFactura(), convertirProductosAString(productos),convertirTipoProductoAString(productos), generarValorTotal(), generarDias(), generarMesAleatorioString(), generarAnioAleatorioString(), cliente);
            listaFacturas.add(factura);
        }
        return listaFacturas;
    }



    public static String convertirTipoProductoAString(List<Producto> productos){
        StringBuilder productosString = new StringBuilder();
        for (Producto p:productos){
            productosString.append(p.getTipo());
            productosString.append("||");
        }
        // Eliminar el último "||" si es necesario
        if (!productosString.isEmpty()) {
            productosString.delete(productosString.length() - 2, productosString.length());
        }
        return productosString.toString();
    }

    /**
     * Genera una lista de productos aleatorios.
     * @return lista de productos generados.
     */
    private static List<Producto> generarProductosAleatorios() {
        ArrayList<Producto> productos = new ArrayList<>();
        int numProductos = random.nextInt(3) + 1; // Entre 1 y 2 productos
        for (int i = 0; i < numProductos; i++) {
            TipoProducto tipo = TipoProducto.values()[random.nextInt(TipoProducto.values().length)];
            String nombre = generarNombreAleatorio(tipo); // Genera el nombre basado en el tipo de producto
            productos.add(new Producto(nombre, tipo));
        }
        return productos;
    }

    /** Esta función convierte una lista de productos en un String separado por ||
     *
     * @param productos
     * @return
     */
    private static String convertirProductosAString(List<Producto> productos) {
        StringBuilder productosString = new StringBuilder();
        for (Producto producto : productos) {
            productosString.append(producto.getNombre());
            productosString.append("||");
        }
        // Eliminar el último "||" si es necesario
        if (!productosString.isEmpty()) {
            productosString.delete(productosString.length() - 2, productosString.length());
        }
        return productosString.toString();
    }

    /**
     * Genera un cliente aleatorio con sus atributos.
     * @return lista de clientes generados.
     */
    private static List<Cliente> generarClientesAletoreos() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            String ID = generarIdCliente();
            int edad = generarEdadAleatoria();
            Genero genero = Genero.values()[random.nextInt(Genero.values().length)];
            String nombre = generarNombresGenero(genero);
            Paises pais = Paises.values()[random.nextInt(Paises.values().length)];
            String ciudad = generarCiudadesPaises(pais);
            clientes.add(new Cliente(ID, nombre, edad, genero, pais, ciudad));
        }
        return clientes;
    }

    /**
     * Genera una edad aleatoria dentro de un rango.
     * @return edad aleatoria generada.
     */
    private static int generarEdadAleatoria() {
        return random.nextInt(73) + 18; // Rango de 18 a 90 (90 - 18 + 1)
    }

    /**
     * Genera un ID de factura aleatorio.
     * @return ID de factura aleatorio generado.
     */
    private static String generarIdFactura() {
        int numeroFactura = random.nextInt(90000) + 10000; // Genera un número aleatorio de 5 dígitos
        return String.valueOf(numeroFactura);
    }

    /**
     * Genera un ID de cliente aleatorio.
     * @return ID de cliente aleatorio generado.
     */
    private static String generarIdCliente() {
        int numeroCliente = random.nextInt(900000000) + 100000000; // Genera un número aleatorio de 10 dígitos
        return String.valueOf(numeroCliente);
    }

    /**
     * Metodo para generar valores a las facturas.
     */

    private static double generarValorTotal() {
        double valorMinimo = 100000.0;
        double valorMaximo = 2000000.0;
        double valorAleatorio = valorMinimo + (valorMaximo - valorMinimo) * random.nextDouble();
        return valorAleatorio;
    }




    /**
     * Genera un día aleatorio como cadena de texto.
     * @return día aleatorio generado como cadena de texto.
     */

    private static String generarDias() {
        String dia = "";
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
        int indice = random.nextInt(dias.length);
        dia = dias[indice];
        return dia;
    }


    /**
     * Genera un mes aleatorio como cadena de texto.
     * @return mes aleatorio generado como cadena de texto.
     */

    private static String generarMesAleatorioString() {
        int mes = random.nextInt(12) + 1; // Genera un número aleatorio entre 1 y 12
        return String.format("%02d", mes); // Formatea el mes como una cadena de dos dígitos
    }

    /**
     * Genera un año aleatorio como cadena de texto.
     * @return año aleatorio generado como cadena de texto.
     */

    private static String generarAnioAleatorioString() {
        int anio = random.nextInt(5) + 2020; // Genera un número aleatorio entre 2020 y 2024
        return String.valueOf(anio); // Convierte el año a una cadena de texto
    }

    /**
     * Genera un nombre aleatorio según el género especificado.
     * @param genero género para el cual se genera el nombre.
     * @return nombre aleatorio generado.
     */
    public static String generarNombresGenero(Genero genero) {
        String[] nombres;
        switch (genero) {
            case MUJER:
                nombres = mujeres;
                break;
            case HOMBRE:
                nombres = hombres;
                break;
            case OTRO:
                nombres = Arrays.copyOf(hombres, hombres.length + mujeres.length);
                System.arraycopy(mujeres, 0, nombres, hombres.length, mujeres.length);
                break;
            default:
                nombres = new String[]{};
        }
        return nombres[random.nextInt(nombres.length)];
    }

    /**
     * Genera un nombre aleatorio para un tipo de producto específico.
     * @param tipo tipo de producto para el cual se genera el nombre.
     * @return nombre aleatorio generado.
     */
    public static String generarNombreAleatorio(TipoProducto tipo) {
        String[] nombres;
        switch (tipo) {
            case ALIMENTO:
                nombres = alimentos;
                break;
            case ELECTRODOMESTICO:
                nombres = electrodomesticos;
                break;
            case COSMETICO:
                nombres = cosmeticos;
                break;
            case TECNOLOGIA:
                nombres = tecnologia;
                break;
            default:
                // En caso de que se agreguen más tipos de productos, aquí podrías manejarlos
                nombres = new String[]{};
        }
        return nombres[random.nextInt(nombres.length)];
    }



    /**
     * Genera una ciudad aleatoria según el país especificado.
     * @param pais país para el cual se genera la ciudad.
     * @return ciudad aleatoria generada.
     */
    public static String generarCiudadesPaises(Paises pais) {
        String[] ciudades = ciudadesPorPais.get(pais);
        if (ciudades != null && ciudades.length > 0) {
            Random random = new Random();
            return ciudades[random.nextInt(ciudades.length)];
        }
        return "Desconocido";
    }


    /**
     * Hilo que se encarga de generarme las facturas,Escribirlas,y Actualizarlas.
     */
    public static void iniciarHiloGeneradorFacturas() {
        // Programa la generación de facturas cada 5 minutos
        scheduler.scheduleAtFixedRate(() -> {
            List<Factura> nuevasFacturas = generarFacturas();
            DataUtils.escribirFacturaCSV((ArrayList<Factura>) nuevasFacturas, "src/main/resources/CSVFiles/Facturas.txt");
            //VentanaAdministradores.actualizarTabla(nuevasFacturas);
            Platform.runLater(() -> {
                VentanaAdministradores.listaFacturas.addAll(nuevasFacturas);
            });
        }, 0, 5, TimeUnit.MINUTES);
    }






}