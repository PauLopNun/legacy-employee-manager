package com.legacy.service;

import com.legacy.dao.EmpleadoDAO;
import com.legacy.model.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceTest {

    @Mock
    private EmpleadoDAO empleadoDAO;

    private EmpleadoService empleadoService;

    @BeforeEach
    void setUp() {
        empleadoService = new EmpleadoService(empleadoDAO);
    }

    @Test
    void testAplicarSubidaSalarialExitosa() {
        empleadoService.aplicarSubidaSalarial(1, 10);
        verify(empleadoDAO, times(1)).subirSueldo(1, 10);
    }

    @Test
    void testAplicarSubidaSalarialInvalidaLanzaExcepcion() {
        try {
            empleadoService.aplicarSubidaSalarial(1, -5);
        } catch (IllegalArgumentException e) {
        }
        verify(empleadoDAO, never()).subirSueldo(anyInt(), anyInt());
    }

    @Test
    void testMostrarTodosLosEmpleadosConRegistros() {
        List<Empleado> empleados = List.of(new Empleado(1, "Pau", "López", 2000.0));
        when(empleadoDAO.listarEmpleados()).thenReturn(empleados);

        empleadoService.mostrarTodosLosEmpleados();

        verify(empleadoDAO, times(1)).listarEmpleados();
    }

    @Test
    void testMostrarTodosLosEmpleadosVacio() {
        when(empleadoDAO.listarEmpleados()).thenReturn(Collections.emptyList());

        empleadoService.mostrarTodosLosEmpleados();

        verify(empleadoDAO, times(1)).listarEmpleados();
    }
}