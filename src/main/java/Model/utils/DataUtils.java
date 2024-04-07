package Model.utils;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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
}
