package es.florida.adria;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField textField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginView() {
        initialize();
    }

    private void initialize() {
        setTitle("Login");
        setBounds(100, 100, 622, 414);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Usuario:");
        lblNewLabel.setBounds(176, 143, 54, 14);
        getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(240, 140, 188, 20);
        getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Contraseña:");
        lblNewLabel_1.setBounds(164, 179, 74, 14);
        getContentPane().add(lblNewLabel_1);

        passwordField = new JPasswordField();
        passwordField.setBounds(240, 176, 188, 20);
        getContentPane().add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(260, 226, 89, 23);
        getContentPane().add(loginButton);

        JLabel lblNewLabel_2 = new JLabel("INICIAR SESIÓN");
        lblNewLabel_2.setBounds(270, 91, 120, 14);
        getContentPane().add(lblNewLabel_2);
    }

    public String getLogin() {
        return textField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
    
    public JButton getLoginButton() {
        return loginButton;
    } 

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
}
