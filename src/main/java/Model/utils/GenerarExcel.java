package Model.utils;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerarExcel {
    public static void main(String[] args) {
        String csvFilePath = "src/main/resources/CSVFiles/FacturasProcesadas.csv";
        String excelFilePath = "planilla_carga.xlsx";

        try {
            List<String[]> data = readCsv(csvFilePath);
            writeExcel(data, excelFilePath);
            System.out.println("Archivo Excel generado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Leer el archivo CSV
    public static List<String[]> readCsv(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(";");
            data.add(values);
        }
        br.close();
        return data;
    }

    // Escribir los datos en un archivo Excel
    public static void writeExcel(List<String[]> data, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Planilla de Carga");

        // Crear encabezados
        String[] encabezado = {"ID", "Número de Documento", "Nombre", "Edad", "Género", "País", "Ciudad",
                "Productos", "Categorías", "Costo", "Día de la Semana", "Mes", "Año"};
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < encabezado.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(encabezado[i]);
        }

        // Escribir los datos del CSV
        int rowNum = 1;
        for (String[] rowData : data) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < rowData.length; i++) {
                row.createCell(i).setCellValue(rowData[i]);
            }
        }

        // Ajustar el tamaño de las columnas
        for (int i = 0; i < encabezado.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Escribir el archivo Excel a disco
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            workbook.close();
        }
    }
}
