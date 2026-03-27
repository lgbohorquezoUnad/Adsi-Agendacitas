package com.agendecitas.model;

public class Cita {
    private final int id;
    private final String nombre;
    private final String correo;
    private final String telefono;
    private final String servicio;
    private final String fecha;
    private final String mensaje;

    public Cita(int id, String nombre, String correo, String telefono, String servicio, String fecha, String mensaje) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.servicio = servicio;
        this.fecha = fecha;
        this.mensaje = mensaje;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getServicio() {
        return servicio;
    }

    public String getFecha() {
        return fecha;
    }

    public String getMensaje() {
        return mensaje;
    }
}
