package Model.utils;

import Model.enums.Genero;
import Model.enums.Paises;
import Model.enums.TipoProducto;
import Model.objetos.Factura;
import Model.objetos.PersonaPremio;
import Model.objetos.Producto;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;

public class DataUtils {
    public static String tipoAdmin;

    /**
     * Esta función abre un cuadro de diálogo para que el usuario seleccione un archivo CSV.
     * Una vez seleccionado el archivo, lo copia al directorio "src/main/resources/CSVFiles" dentro del proyecto.
     * Si el directorio de destino no existe, se crea automáticamente.
     */
    public static void cargarArchivoCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt", "*.csv")); // Filtro para archivos de texto
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            try {
                System.out.println("Ruta del archivo seleccionado: " + selectedFile.getAbsolutePath());

                // Verificar si el directorio de destino existe, si no, crearlo
                Path destinationDirectory = Path.of("src/main/resources/CSVFiles");
                if (!Files.exists(destinationDirectory)) {
                    Files.createDirectories(destinationDirectory);
                }

                // Copiar el archivo seleccionado al directorio de destino
                Path destinationPath = destinationDirectory.resolve(selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Archivo guardado en: " + destinationPath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }
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
     * @return 1 en caso de que sea un administrador de facturas , 2 si es de premios y 3 si es de ruta , en caso contrario 0
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


    public static void CSVWriter(String ruta) throws IOException {
        String[] encabezado = {"Nombre","Edad","Email"};
        //Caso de prueba:
        String[][] data = {{"Juan", "30", "juan@example.com"}, // Datos para escribir en el CSV
                {"María", "25", "maria@example.com"},
                {"Pedro", "35", "pedro@example.com"}};
        try{
            FileWriter fw = new FileWriter(ruta);
            BufferedWriter bw = new BufferedWriter(fw);

            // Escribir encabezados
            for (int i = 0; i < encabezado.length; i++) {
                bw.write(encabezado[i]);
                if (i < encabezado.length - 1) {
                    bw.write(";");
                }
            }
            bw.newLine();

            // Escribir datos
            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    bw.write(row[i]);
                    if (i < row.length - 1) {
                        bw.write(";");
                    }
                }
                bw.newLine();
            }

            bw.close();
            System.out.println("¡Archivo CSV creado con éxito!");


        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Factura> leerCSVFactura(String ruta) {
        ArrayList<Factura> listaFacturas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            // Leer la primera línea que generalmente contiene encabezados
            String encabezados = br.readLine();
            // Leer cada línea del archivo CSV
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en partes usando coma como delimitador
                String[] partes = linea.split(";");
                // Crear un objeto Factura con los datos de la línea
                Factura factura = crearFacturaDesdeLinea(partes);
                // Agregar la factura a la lista
                listaFacturas.add(factura);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaFacturas;
    }

    private static Factura crearFacturaDesdeLinea(String[] partes) {
        String nombre = partes[0];
        String productos = partes[1];
        double valor = Double.parseDouble(partes[2]);
        TipoProducto tipo = TipoProducto.valueOf(partes[3]);

        String fecha = partes[4];
        String numeroFactura = partes[5];
        String cliente = partes[6];
        String id = partes[7];
        int puntos = Integer.parseInt(partes[8]);

        // Crear un objeto Factura con los datos de la línea
       // return new Factura();
        ArrayList<Producto> productoslist = new ArrayList<>();
        productoslist.add(new Producto(productos,valor,tipo));
        //return  new Factura(productoslist,fecha,numeroFactura,cliente);
        return null;
    }

}
