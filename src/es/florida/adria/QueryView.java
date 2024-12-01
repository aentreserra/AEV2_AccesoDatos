package es.florida.adria;

import javax.swing.*;
import java.awt.*;

public class QueryView extends JFrame {
    private JTextArea queryArea;
    private JButton executeButton;
    private JButton downloadButton;
    private JTable resultTable;

    public QueryView() {
        setTitle("Consulta SQL");
        setSize(954, 557);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("CONSULTA SQL:");
        lblNewLabel.setBounds(10, 11, 96, 14);
        getContentPane().add(lblNewLabel);
        
        executeButton = new JButton("EJECUTAR");
        executeButton.setBounds(784, 39, 144, 23);
        getContentPane().add(executeButton);
        
        downloadButton = new JButton("DESCARGAR");
        downloadButton.setBounds(629, 39, 144, 23);
        getContentPane().add(downloadButton);
        
        queryArea = new JTextArea();
        queryArea.setBounds(112, 6, 816, 22);
        getContentPane().add(queryArea);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 75, 918, 432);
        getContentPane().add(scrollPane);
        
        resultTable = new JTable();
        scrollPane.setViewportView(resultTable);
    }

    public JTextArea getQueryArea() {
        return queryArea;
    }

    public JButton getExecuteButton() {
        return executeButton;
    }
    
    public JButton getDownloadCsvButton() {
        return downloadButton;
    }

    public JTable getResultTable() {
        return resultTable;
    }

    public void setTableData(Object[][] data, String[] columns) {
        resultTable.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }
}