CREATE OR REPLACE PROCEDURE listar_empleados (
    p_cursor OUT SYS_REFCURSOR
) AS
BEGIN
OPEN p_cursor FOR
SELECT id, nombre, apellido, salario FROM empleados;
END;
/