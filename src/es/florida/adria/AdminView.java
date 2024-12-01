package es.florida.adria;

import javax.swing.*;

public class AdminView extends JFrame {
	
	private JButton importCsvButton;
	private JButton newUserButton;
	private JButton queryButton;

    public AdminView() {
        setTitle("Administrador");
        setSize(646, 504);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        newUserButton = new JButton("CREAR USUARIO");
        newUserButton.setBounds(244, 129, 163, 37);
        getContentPane().add(newUserButton);
        
        queryButton = new JButton("EJECUTAR SQL");
        queryButton.setBounds(244, 202, 163, 37);
        getContentPane().add(queryButton);
        
        importCsvButton = new JButton("CARGAR CSV EN DB");
        importCsvButton.setBounds(244, 278, 163, 37);
        getContentPane().add(importCsvButton);
    }

    public JButton getImportCsvButton() {
        return importCsvButton;
    }
    
    public JButton getNewUserButton () {
    	return newUserButton;
    }
    
    public JButton getQueryButton() {
    	return queryButton;
    }
}
