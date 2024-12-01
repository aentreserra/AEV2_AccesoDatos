package es.florida.adria;

import javax.swing.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryController {
    private QueryView view;

    public QueryController(QueryView view) {
        this.view = view;
        this.view.getExecuteButton().addActionListener(e -> executeQuery());
        view.getDownloadCsvButton().addActionListener(e -> handleDownloadCsv());
    }

    private void executeQuery() {
        String query = view.getQueryArea().getText();

        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Obtener nombres de las columnas
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            // Obtener datos
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                data.add(row);
            }

            // Mostrar datos en la tabla
            Object[][] dataArray = data.toArray(new Object[0][]);
            view.setTableData(dataArray, columnNames);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error en la consulta: " + ex.getMessage());
        }
    }
    
    private void handleDownloadCsv() {
        String query = view.getQueryArea().getText();

        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Obtener nombres de las columnas
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            // Obtener datos
            ArrayList<Object[]> data = new ArrayList<>();
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                data.add(row);
            }

            // Mostrar datos en la tabla
            Object[][] dataArray = data.toArray(new Object[0][]);
            view.setTableData(dataArray, columnNames);

            // Guardar datos en el archivo CSV
            saveResultsToCsv(columnNames, dataArray);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error en la consulta: " + ex.getMessage());
        }
    }

    private void saveResultsToCsv(String[] columnNames, Object[][] data) {
        File csvFile = new File("newData.csv");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            // Escribir encabezados
            for (int i = 0; i < columnNames.length; i++) {
                bw.write(columnNames[i]);
                if (i < columnNames.length - 1) {
                    bw.write(";"); // Separador
                }
            }
            bw.newLine();

            // Escribir filas
            for (Object[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    bw.write(row[i] != null ? row[i].toString() : ""); // Evitar valores nulos
                    if (i < row.length - 1) {
                        bw.write(";"); // Separador
                    }
                }
                bw.newLine();
            }

            JOptionPane.showMessageDialog(view, "Archivo 'newData.csv' creado exitosamente en la raíz del proyecto.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al crear 'newData.csv': " + ex.getMessage());
        }
    }


    
}