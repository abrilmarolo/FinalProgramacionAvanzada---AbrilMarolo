package org.example.Model;

public class Poder {
    private String nombre;
    private double costoPoder;
    private double daño;

    public Poder(String nombre, double costoPoder, double daño) {
        this.nombre = nombre;
        this.costoPoder = costoPoder;
        this.daño = daño;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCostoPoder() {
        return costoPoder;
    }

    public void setCostoPoder(double costoPoder) {
        this.costoPoder = costoPoder;
    }

    public double getDaño() {
        return daño;
    }

    public void setDaño(double daño) {
        this.daño = daño;
    }
}
