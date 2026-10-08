package com.etitc.aeropuerto.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tiquete {
    private int id;
    private String numero;
    private int vueloId;
    private String pasajero;
    private String asiento;
    private double precio;
    private String estado;
    private LocalDateTime fechaEmision;

    public Tiquete() {}

    public Tiquete(int id, String numero, int vueloId, String pasajero,
                   String asiento, double precio, String estado, LocalDateTime fechaEmision) {
        this.id = id;
        this.numero = numero;
        this.vueloId = vueloId;
        this.pasajero = pasajero;
        this.asiento = asiento;
        this.precio = precio;
        this.estado = estado;
        this.fechaEmision = fechaEmision;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getVueloId() {
        return vueloId;
    }

    public void setVueloId(int vueloId) {
        this.vueloId = vueloId;
    }

    public String getPasajero() {
        return pasajero;
    }

    public void setPasajero(String pasajero) {
        this.pasajero = pasajero;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    // Métodos útiles
    public String getFechaEmisionFormato() {
        if (fechaEmision == null) return "";
        return fechaEmision.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public boolean estaVigente() {
        return "Activo".equals(estado);
    }

    public String getPrecioFormato() {
        return String.format("$%.2f", precio);
    }

    @Override
    public String toString() {
        return "Tiquete{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", pasajero='" + pasajero + '\'' +
                ", asiento='" + asiento + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
