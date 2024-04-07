package Model.utils;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class DataUtils {
    /**
     * Esta función abre un cuadro de diálogo para que el usuario seleccione un archivo CSV.
     * Una vez seleccionado el archivo, lo copia al directorio "src/main/resources/CSVFiles" dentro del proyecto.
     * Si el directorio de destino no existe, se crea automáticamente.
     */
    public static void UploadCSVfile() {
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

}
