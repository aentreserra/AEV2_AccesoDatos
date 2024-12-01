package es.florida.adria;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterUserController {
    private RegisterUserView view;

    public RegisterUserController(RegisterUserView view) {
        this.view = view;
        this.view.getRegisterButton().addActionListener(e -> handleRegister());
    }

    private void handleRegister() {
        String username = view.getUserField().getText();
        String password = new String(view.getPasswordField().getPassword());
        String confirmPassword = new String(view.getConfirmPasswordField().getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, completa todos los campos.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(view, "Las contraseñas no coinciden.");
            return;
        }

        String hashedPassword = MD5Hash(password);

        try (Connection connection = DataBaseConnection.getConnection()) {
        	String insertUserQuery = "INSERT INTO USERS (login, password, type) VALUES (?, ?, ?)";
            PreparedStatement psInsertUser = connection.prepareStatement(insertUserQuery);
            psInsertUser.setString(1, username);
            psInsertUser.setString(2, hashedPassword);
            psInsertUser.setString(3, "client");
            psInsertUser.executeUpdate();
        	
            // Crear el usuario en la base de datos
            String createUserQuery = "CREATE USER ? IDENTIFIED BY ?";
            PreparedStatement psCreateUser = connection.prepareStatement(createUserQuery);
            psCreateUser.setString(1, username);
            psCreateUser.setString(2, hashedPassword);
            psCreateUser.executeUpdate();

            // Otorgar permisos de solo lectura
            String grantQuery = "GRANT SELECT ON population.population TO ?";
            PreparedStatement psGrant = connection.prepareStatement(grantQuery);
            psGrant.setString(1, username);
            psGrant.executeUpdate();

            JOptionPane.showMessageDialog(view, "Usuario registrado exitosamente.");
            view.dispose(); // Cerrar la ventana
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al registrar usuario: " + ex.getMessage());
        }
    }

    private String MD5Hash(String input) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}