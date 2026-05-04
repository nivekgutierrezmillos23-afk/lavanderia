package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3307/lavadero";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "123456";

    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(URL, USUARIO, CONTRASENA);

            System.out.println("Conexion exitosa");
            return con;

        } catch (Exception e) {
            System.out.println("Error de conexion:");
            e.printStackTrace();
            return null;
        }
    }
}
