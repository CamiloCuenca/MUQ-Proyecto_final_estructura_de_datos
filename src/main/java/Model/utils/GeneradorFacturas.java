package Model.utils;

import Model.enums.TipoProducto;
import Model.objetos.Cliente;
import Model.objetos.Factura;
import Model.objetos.Producto;

import java.util.*;

public class GeneradorFacturas {
    private static final String[] nombres = {"Juan", "María", "Carlos", "Ana", "Luis", "Laura", "Pedro", "Sofía", "Diego", "Elena", "Branon"};
    private static final String[] alimentos = {
            "Leche", "Pan", "Huevos", "Arroz", "Frijoles", "Manzanas", "Pasta", "Carne", "Pollo", "Cereal"
    };

    private static final String[] electrodomesticos = {
            "Lavadora", "Refrigerador", "Televisor", "Licuadora", "Microondas", "Horno", "Aspiradora", "Batidora", "Secadora", "Cafetera"
    };

    private static final String[] cosmeticos = {
            "Crema facial", "Shampoo", "Acondicionador", "Maquillaje", "Perfume", "Desodorante", "Gel de ducha", "Cepillo de dientes", "Cremas corporales", "Protector solar"
    };

    private static final String[] tecnologia = {
            "Smartphone", "Laptop", "Tablet", "Cámara digital", "Altavoces inteligentes", "Auriculares inalámbricos", "Consola de videojuegos", "Impresora", "Router", "Smartwatch"
    };


    private static final Random random = new Random();

    public static void main(String[] args) {
        List<Factura> listaFacturas = generarFacturas();
        imprimirFacturas(listaFacturas);
    }

    private static List<Factura> generarFacturas() {
        List<Factura> listaFacturas = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Date fecha = generarFechaAleatoria();
            Cliente cliente = generarClienteAleatorio();
            List<Producto> productos = generarProductosAleatorios();
            Factura factura = new Factura(productos, fecha, generarNumeroFacturaAleatorio(), cliente);
            listaFacturas.add(factura);
        }
        return listaFacturas;
    }

    private static Date generarFechaAleatoria() {
        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        // Restar un número aleatorio de días entre 1 y 30
        int diasAtras = random.nextInt(30) + 1;
        calendar.add(Calendar.DAY_OF_MONTH, -diasAtras);

        // Obtener el año, mes y día
        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH) + 1; // Los meses se indexan desde 0
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        // Generar la fecha aleatoria
        int anioAleatorio = anio;
        int mesAleatorio = random.nextInt(mes) + 1; // Mes aleatorio entre 1 y el mes actual
        int diaAleatorio = random.nextInt(dia) + 1; // Día aleatorio entre 1 y el día actual

        // Construir la fecha aleatoria
        calendar.set(anioAleatorio, mesAleatorio - 1, diaAleatorio); // Restar 1 al mes para indexar correctamente
        return calendar.getTime();
    }

    private static Cliente generarClienteAleatorio() {
        String nombre = nombres[random.nextInt(nombres.length)];
        String ID = "ID" + (random.nextInt(100) + 1);
        int puntos = random.nextInt(101);
        return new Cliente(nombre, ID, puntos);
    }

    private static List<Producto> generarProductosAleatorios() {
        ArrayList<Producto> productos = new ArrayList<>();
        int numProductos = random.nextInt(1) + 1; // Entre 1 y 5 productos
        for (int i = 0; i < numProductos; i++) {
            TipoProducto tipo = TipoProducto.values()[random.nextInt(TipoProducto.values().length)];
            String nombre = generarNombreAleatorio(tipo); // Genera el nombre basado en el tipo de producto
            double precio = random.nextDouble() * 100 + 1;
            productos.add(new Producto(nombre, precio, tipo));
        }
        return productos;
    }
    private static String generarNumeroFacturaAleatorio() {
        return String.valueOf(random.nextInt(9000) + 1000);
    }

    private static void imprimirFacturas(List<Factura> listaFacturas) {
        for (int i = 0; i < listaFacturas.size(); i++) {
            System.out.println("Factura " + (i + 1) + ": " + listaFacturas.get(i));
        }
    }

    private static String generarNombreAleatorio(TipoProducto tipo) {
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

}
