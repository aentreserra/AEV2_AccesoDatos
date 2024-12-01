package es.florida.adria;

import javax.swing.*;
import java.awt.*;

public class RegisterUserView extends JFrame {
    private JTextField userField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;

    public RegisterUserView() {
        setTitle("Registrar Usuario");
        setSize(744, 237);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel de entradas
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.setBounds(0, 0, 728, 99);
        inputPanel.add(new JLabel("Usuario:"));
        userField = new JTextField();
        inputPanel.add(userField);

        inputPanel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        inputPanel.add(new JLabel("Confirmar Contraseña:"));
        confirmPasswordField = new JPasswordField();
        inputPanel.add(confirmPasswordField);

        // Botón de registro
        registerButton = new JButton("Registrar");
        registerButton.setBounds(319, 132, 140, 23);
        getContentPane().setLayout(null);

        // Añadir componentes
        getContentPane().add(inputPanel);
        getContentPane().add(registerButton);

        setLocationRelativeTo(null);
    }

    public JTextField getUserField() {
        return userField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JPasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}
