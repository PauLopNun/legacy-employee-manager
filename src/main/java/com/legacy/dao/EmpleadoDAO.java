package com.legacy.dao;

import com.legacy.config.DatabaseConfig;
import com.legacy.exception.EmpleadoException;
import com.legacy.model.Empleado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoDAO.class);

    public int subirSueldo(int empleadoId, int porcentaje) {
        String sql = "{call PKG_EMPLEADOS.SUBIR_SUELDO(?, ?, ?)}";

        try (Connection conn = DatabaseConfig.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, empleadoId);
            stmt.setInt(2, porcentaje);
            stmt.registerOutParameter(3, Types.NUMERIC);

            stmt.execute();
            return stmt.getInt(3);

        } catch (SQLException e) {
            logger.error("Error en BD al subir sueldo del empleado {}", empleadoId, e);
            // Capturamos el RAISE_APPLICATION_ERROR(-20001) de Oracle
            if (e.getErrorCode() == 20001) {
                throw new EmpleadoException("El empleado no existe en la base de datos.");
            }
            throw new EmpleadoException("Error interno de base de datos.", e);
        }
    }

    public List<Empleado> listarEmpleados() {
        String sql = "{call PKG_EMPLEADOS.LISTAR_EMPLEADOS(?)}";
        List<Empleado> empleados = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, Types.REF_CURSOR);
            stmt.execute();

            try (ResultSet rs = (ResultSet) stmt.getObject(1)) {
                while (rs.next()) {
                    empleados.add(new Empleado(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getDouble("salario")
                    ));
                }
            }
            return empleados;
        } catch (SQLException e) {
            logger.error("Error al listar empleados", e);
            throw new EmpleadoException("No se pudo obtener la lista de empleados.", e);
        }
    }
}