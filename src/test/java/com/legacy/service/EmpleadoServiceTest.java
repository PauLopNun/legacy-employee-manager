package com.legacy.service;

import com.legacy.dao.EmpleadoDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmpleadoServiceTest {

    private EmpleadoDAO empleadoDAO;
    private EmpleadoService empleadoService;

    @BeforeEach
    void setUp() {
        empleadoDAO = mock(EmpleadoDAO.class);
        empleadoService = new EmpleadoService(empleadoDAO);
    }

    @Test
    void subidaSalarialExitosa() {
        when(empleadoDAO.subirSueldo(101, 10)).thenReturn(25000);

        assertDoesNotThrow(() -> empleadoService.aplicarSubidaSalarial(101, 10));
        verify(empleadoDAO, times(1)).subirSueldo(101, 10);
    }

    @Test
    void porcentajeInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> empleadoService.aplicarSubidaSalarial(101, -5));
        verify(empleadoDAO, never()).subirSueldo(anyInt(), anyInt());
    }
}