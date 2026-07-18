CREATE OR REPLACE PACKAGE PKG_EMPLEADOS AS
    TYPE t_cursor_empleados IS REF CURSOR;

    PROCEDURE SUBIR_SUELDO(
        p_id IN NUMBER,
        p_porcentaje IN NUMBER,
        p_nuevo_salario OUT NUMBER
    );

    PROCEDURE LISTAR_EMPLEADOS(
        p_cursor OUT t_cursor_empleados
    );
END PKG_EMPLEADOS;
/

CREATE OR REPLACE PACKAGE BODY PKG_EMPLEADOS AS

    PROCEDURE SUBIR_SUELDO(
        p_id IN NUMBER,
        p_porcentaje IN NUMBER,
        p_nuevo_salario OUT NUMBER
    ) AS
BEGIN
UPDATE EMPLEADOS
SET salario = salario + (salario * p_porcentaje / 100)
WHERE id = p_id
    RETURNING salario INTO p_nuevo_salario;

IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20001, 'Empleado no encontrado con ID: ' || p_id);
END IF;

COMMIT;
END SUBIR_SUELDO;

    PROCEDURE LISTAR_EMPLEADOS(
        p_cursor OUT t_cursor_empleados
    ) AS
BEGIN
OPEN p_cursor FOR
SELECT id, nombre, apellido, salario
FROM EMPLEADOS
ORDER BY id;
END LISTAR_EMPLEADOS;

END PKG_EMPLEADOS;
/