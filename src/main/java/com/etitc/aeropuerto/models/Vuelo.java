package com.etitc.aeropuerto.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vuelo {
    private int id;
    private String codigo;
    private String destino;
    private String origen;
    private LocalDateTime horaSalida;
    private LocalDateTime horaLlegada;
    private String aerolinea;
    private String puertaSalida;
    private int capacidad;
    private int pasajeros;
    private String estado;

    public Vuelo() {}

    public Vuelo(int id, String codigo, String destino, String origen,
                 LocalDateTime horaSalida, LocalDateTime horaLlegada,
                 String aerolinea, String puertaSalida, int capacidad, int pasajeros, String estado) {
        this.id = id;
        this.codigo = codigo;
        this.destino = destino;
        this.origen = origen;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.aerolinea = aerolinea;
        this.puertaSalida = puertaSalida;
        this.capacidad = capacidad;
        this.pasajeros = pasajeros;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public LocalDateTime getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(LocalDateTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getPuertaSalida() {
        return puertaSalida;
    }

    public void setPuertaSalida(String puertaSalida) {
        this.puertaSalida = puertaSalida;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Métodos útiles
    public int getAsientosDisponibles() {
        return capacidad - pasajeros;
    }

    public boolean tieneAsientosDisponibles() {
        return getAsientosDisponibles() > 0;
    }

    public String getHoraSalidaFormato() {
        if (horaSalida == null) return "";
        return horaSalida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String getHoraLlegadaFormato() {
        if (horaLlegada == null) return "";
        return horaLlegada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", destino='" + destino + '\'' +
                ", origen='" + origen + '\'' +
                ", horaSalida=" + horaSalida +
                ", estado='" + estado + '\'' +
                '}';
    }
}
