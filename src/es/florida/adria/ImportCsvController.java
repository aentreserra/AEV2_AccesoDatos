package es.florida.adria;

import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.Statement;

public class ImportCsvController {
    private AdminView view;

    public ImportCsvController(AdminView view) {
        this.view = view;
        this.view.getImportCsvButton().addActionListener(e -> handleImportCsv());
    }

    public void handleImportCsv() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(view);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                processCsv(selectedFile);
                JOptionPane.showMessageDialog(view, "Archivo CSV importado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error al importar el archivo: " + ex.getMessage());
            }
        }
    }

    private void processCsv(File file) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(file));
             Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            // Leer encabezado
            String headerLine = br.readLine();
            if (headerLine == null) {
                throw new IOException("El archivo CSV está vacío.");
            }

            // Crear tabla "population"
            statement.executeUpdate("DROP TABLE IF EXISTS population");
            String[] columns = headerLine.split(";");
            StringBuilder createTableQuery = new StringBuilder("CREATE TABLE population (");
            for (String column : columns) {
                createTableQuery.append(column).append(" VARCHAR(30), ");
            }
            createTableQuery.setLength(createTableQuery.length() - 2);
            createTableQuery.append(")");
            statement.executeUpdate(createTableQuery.toString());

            // Leer datos y generar XML
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                StringBuilder insertQuery = new StringBuilder("INSERT INTO population VALUES (");
                for (String value : values) {
                    insertQuery.append("'").append(value.replace("'", "''")).append("', ");
                }
                insertQuery.setLength(insertQuery.length() - 2);
                insertQuery.append(")");
                statement.executeUpdate(insertQuery.toString());

                // Crear archivo XML
                createXmlFile(columns, values);
            }
        }
    }

    private void createXmlFile(String[] columns, String[] values) throws IOException {
        String countryName = values[0];
        File xmlDir = new File("xml");
        if (!xmlDir.exists()) {
            xmlDir.mkdir();
        }

        File xmlFile = new File(xmlDir, countryName + ".xml");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(xmlFile))) {
            bw.write("<record>\n");
            for (int i = 0; i < columns.length; i++) {
                bw.write("  <" + columns[i] + ">" + values[i] + "</" + columns[i] + ">\n");
            }
            bw.write("</record>");
        }
    }
}