package com.legacy.model;

public class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private double salario;

    public Empleado(int id, String nombre, String apellido, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public double getSalario() { return salario; }

    @Override
    public String toString() {
        return id + " - " + nombre + " " + apellido + " - " + salario;
    }
}