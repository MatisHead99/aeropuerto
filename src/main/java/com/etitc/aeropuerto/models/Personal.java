package com.etitc.aeropuerto.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Personal {
    private int id;
    private String nombre;
    private String cedula;
    private String puesto;
    private String telefono;
    private String email;
    private String usuario;
    private String contrasena;
    private String estado;
    private LocalDateTime fechaIngreso;

    public Personal() {}

    public Personal(int id, String nombre, String cedula, String puesto,
                    String telefono, String email, String usuario, String contrasena,
                    String estado, LocalDateTime fechaIngreso) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.puesto = puesto;
        this.telefono = telefono;
        this.email = email;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estado = estado;
        this.fechaIngreso = fechaIngreso;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    // Métodos útiles
    public String getFechaIngresoFormato() {
        if (fechaIngreso == null) return "";
        return fechaIngreso.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public boolean estaActivo() {
        return "Activo".equals(estado);
    }

    public boolean esAdmin() {
        return "Administrador".equals(puesto);
    }

    @Override
    public String toString() {
        return "Personal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", puesto='" + puesto + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
