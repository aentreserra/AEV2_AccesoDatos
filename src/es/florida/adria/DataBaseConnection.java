package es.florida.adria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/population";
    private static final String USER = "admin";
    private static final String PASSWORD = "21232f297a57a5a743894a0e4a801fc3";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC no encontrado.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
