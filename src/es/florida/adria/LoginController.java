package es.florida.adria;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
        this.view.getLoginButton().addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = view.getLogin();
        String password = new String(view.getPassword());
        String hashedPassword = MD5Hash(password);

        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE login = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String userType = rs.getString("type");
                JOptionPane.showMessageDialog(view, "Login exitoso");

                if ("admin".equalsIgnoreCase(userType)) {
                	AdminView adminView = new AdminView();
                    new AdminController(adminView);
                    adminView.setVisible(true);
                } else {
                	QueryView queryView = new QueryView();
                	new QueryController(queryView);
                	queryView.setVisible(true);
                    //JOptionPane.showMessageDialog(view, "No tienes permisos para registrar usuarios");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Usuario o contraseña incorrectos");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error de conexión: " + ex.getMessage());
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