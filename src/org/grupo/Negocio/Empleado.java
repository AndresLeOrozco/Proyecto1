package org.grupo.Negocio;

public class Empleado {
    private String cedula;
    private String nombre;
    private String telefono;
    private double salario_base;
    private Sucursal sucursal;

    public String getCedula() {
        return this.cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSalario_base() {
        return salario_base;
    }

    public void setSalario_base(double salario_base) {
        this.salario_base = salario_base;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Empleado() {
        this.cedula = " ";
        this.nombre = " ";
        this.telefono = " ";
        this.salario_base = 0.0;
        this.sucursal = new Sucursal();
    }
    public Empleado(String cedula, String nombre, String telefono, double salario_base, Sucursal sucursal) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.salario_base = salario_base;
        this.sucursal = sucursal;
    }

    public double Salario_Total() {
        double salario_Total;
        return (salario_Total = salario_base + (salario_base * sucursal.getPorcentajeDeZonaje()));
    }

}