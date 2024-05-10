package com.conversor.modelos;

import java.time.LocalDate;

public class Historial {
    private String monedaBase;
    private String monedaCambio;
    private double montoBase;
    private double montoCambio;
    private LocalDate fecha;

    public Historial(String monedaCambio, String monedaBase, double montoBase, double montoCambio) {
        this.monedaCambio = monedaCambio;
        this.monedaBase = monedaBase;
        this.montoBase = montoBase;
        this.montoCambio = montoCambio;
        this.fecha = LocalDate.now();
    }

    public double getMontoCambio() {
        return montoCambio;
    }

    public void setMontoCambio(double montoCambio) {
        this.montoCambio = montoCambio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getMontoBase() {
        return montoBase;
    }

    public void setMontoBase(double monto) {
        this.montoBase = monto;
    }

    public String getMonedaCambio() {
        return monedaCambio;
    }

    public void setMonedaCambio(String monedaCambio) {
        this.monedaCambio = monedaCambio;
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public void setMonedaBase(String monedaBase) {
        this.monedaBase = monedaBase;
    }
}
