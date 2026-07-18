CREATE OR REPLACE PROCEDURE subir_sueldo (
    p_empleado_id IN NUMBER,
    p_porcentaje_subida IN NUMBER,
    p_nuevo_salario OUT NUMBER
) AS
BEGIN
UPDATE empleados
SET salario = salario + (salario * p_porcentaje_subida / 100)
WHERE id = p_empleado_id
    RETURNING salario INTO p_nuevo_salario;

IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Empleado no encontrado: ' || p_empleado_id);
END IF;

COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END;
/