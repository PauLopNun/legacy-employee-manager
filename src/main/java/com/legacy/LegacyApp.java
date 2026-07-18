package com.legacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.io.InputStream;
import java.util.Properties;

public class LegacyApp {

    public static void main(String[] args) {
        try (Connection conn = crearConexion()) {
            System.out.println("Conexión exitosa a la base de datos.");

            int nuevoSalario = subirSueldo(conn, 101, 5);
            System.out.println("Nuevo salario: " + nuevoSalario);

            listarEmpleados(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection crearConexion() throws Exception {
        Properties props = new Properties();

        try (InputStream input = LegacyApp.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("No se encontró config.properties. Copia config.properties.example y rellénalo.");
            }
            props.load(input);
        }

        String url = props.getProperty("db.url");
        String usuario = props.getProperty("db.usuario");
        String password = props.getProperty("db.password");

        return DriverManager.getConnection(url, usuario, password);
    }

    private static int subirSueldo(Connection conn, int empleadoId, int porcentaje) throws Exception {
        String sql = "{call subir_sueldo(?, ?, ?)}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, empleadoId);
            stmt.setInt(2, porcentaje);
            stmt.registerOutParameter(3, Types.NUMERIC);

            stmt.execute();

            return stmt.getInt(3);
        }
    }

    private static void listarEmpleados(Connection conn) throws Exception {
        String sql = "{call listar_empleados(?)}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.registerOutParameter(1, Types.REF_CURSOR);
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    double salario = rs.getDouble("salario");

                    System.out.println(id + " - " + nombre + " " + apellido + " - " + salario);
                }
            }
        }
    }
}