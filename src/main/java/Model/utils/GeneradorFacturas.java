package Model.utils;

import Model.enums.Genero;
import Model.enums.TipoProducto;
import Model.objetos.Cliente;
import Model.objetos.Factura;
import Model.objetos.Producto;

import java.util.*;

public class GeneradorFacturas {

    private static final Random random = new Random();
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


    /**
     * Crear metodo para generar nombres a los clientes segun el tipo de sexo.
     *
     */

    /**
     * Generar metodo para crar cliente aletoreo por sus atributos.
     */


    /**
     * Metodo principal para generar facturas aletoreas.
     * @return
     */
    private static List<Factura> generarFacturas() {
        List<Factura> listaFacturas = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Cliente cliente = new Cliente("12","camilo",20, Genero.HOMBRE,"peruano","lima");
            List<Producto> productos = generarProductosAleatorios();
            Factura factura = new Factura(generarNumeroFacturaAleatorio(),productos.getFirst().getNombre(), productos.getLast().getTipo(),1,"10","07","2024",cliente);
            listaFacturas.add(factura);
        }
        return listaFacturas;
    }


    /**
     * Metodo que me genera producto segun el tipoDe producto con su nombre
     * @return
     */
    private static List<Producto> generarProductosAleatorios() {
        ArrayList<Producto> productos = new ArrayList<>();
        int numProductos = random.nextInt(1) + 1; // Entre 1 y 5 productos
        for (int i = 0; i < numProductos; i++) {
            TipoProducto tipo = TipoProducto.values()[random.nextInt(TipoProducto.values().length)];
            String nombre = generarNombreAleatorio(tipo); // Genera el nombre basado en el tipo de producto
            productos.add(new Producto(nombre, tipo));
        }
        return productos;
    }

    /**
     * Genera el ID factura.
     * @return
     */
    private static String generarNumeroFacturaAleatorio() {
        return String.valueOf(random.nextInt(9000) + 1000);
    }

    /**
     * Genera el nombre del producto segun el tipo de producto.
     * @param tipo
     * @return
     */
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

    private static void imprimirFacturas(List<Factura> listaFacturas) {
        for (int i = 0; i < listaFacturas.size(); i++) {
            System.out.println("Factura " + (i + 1) + ": " + listaFacturas.get(i));
        }
    }

    public static void main(String[] args) {
        List<Factura> listaFacturas = generarFacturas();
        imprimirFacturas(listaFacturas);
    }

}
