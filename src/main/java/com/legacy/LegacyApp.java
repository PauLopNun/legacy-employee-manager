package com.legacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.Types;

public class LegacyApp {

    public static void main(String[] args) {
        try (Connection conn = crearConexion()) {
            System.out.println("Conexión exitosa a la base de datos.");

            int nuevoSalario = subirSueldo(conn, 101, 5);
            System.out.println("Nuevo salario: " + nuevoSalario);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection crearConexion() throws Exception {
        String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
        String usuario = "admin";
        String password = "TuPasswordSegura123";

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
}