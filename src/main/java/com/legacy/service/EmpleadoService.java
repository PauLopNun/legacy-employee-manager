package com.legacy.service;

import com.legacy.dao.EmpleadoDAO;
import com.legacy.model.Empleado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EmpleadoService {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoService.class);
    private final EmpleadoDAO empleadoDAO;

    public EmpleadoService(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    public void aplicarSubidaSalarial(int empleadoId, int porcentaje) {
        logger.info("Iniciando proceso de subida salarial. ID: {}, Porcentaje: {}%", empleadoId, porcentaje);

        if (porcentaje <= 0) {
            throw new IllegalArgumentException("El porcentaje de subida debe ser mayor a 0");
        }

        int nuevoSalario = empleadoDAO.subirSueldo(empleadoId, porcentaje);
        logger.info("Subida aplicada con éxito. Nuevo salario base: {}", nuevoSalario);
    }

    public void mostrarTodosLosEmpleados() {
        logger.info("Recuperando lista de empleados...");
        List<Empleado> empleados = empleadoDAO.listarEmpleados();

        if (empleados.isEmpty()) {
            logger.warn("No se encontraron empleados en la base de datos.");
            return;
        }

        System.out.println("--- LISTA DE EMPLEADOS ---");
        empleados.forEach(emp -> System.out.println(emp.toString()));
        System.out.println("--------------------------");

        logger.info("Total de empleados recuperados: {}", empleados.size());
    }
}