package es.florida.adria;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class ExportCsvController {
    public static void exportPopulationToCsv(JFrame parentFrame) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(parentFrame);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (Connection connection = DataBaseConnection.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet rs = statement.executeQuery("SELECT * FROM population");
                 BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile))) {

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Escribir encabezados
                for (int i = 1; i <= columnCount; i++) {
                    bw.write(metaData.getColumnName(i));
                    if (i < columnCount) {
                        bw.write(";");
                    }
                }
                bw.newLine();

                // Escribir datos
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        bw.write(rs.getString(i));
                        if (i < columnCount) {
                            bw.write(";");
                        }
                    }
                    bw.newLine();
                }

                JOptionPane.showMessageDialog(parentFrame, "Archivo exportado exitosamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parentFrame, "Error al exportar: " + ex.getMessage());
            }
        }
    }
}