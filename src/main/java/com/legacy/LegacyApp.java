package com.legacy;

import com.legacy.dao.EmpleadoDAO;
import com.legacy.service.EmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LegacyApp {
    private static final Logger logger = LoggerFactory.getLogger(LegacyApp.class);

    public static void main(String[] args) {
        logger.info("Arrancando la aplicación Legacy Employee Manager...");

        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        EmpleadoService empleadoService = new EmpleadoService(empleadoDAO);

        try {
            empleadoService.mostrarTodosLosEmpleados();
            empleadoService.aplicarSubidaSalarial(101, 5);
            empleadoService.mostrarTodosLosEmpleados();

        } catch (Exception e) {
            logger.error("La aplicación terminó con un error crítico", e);
        }

        logger.info("Aplicación finalizada correctamente.");
    }
}