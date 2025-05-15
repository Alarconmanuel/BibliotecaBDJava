package org.example.model;

import java.sql.Date;
import java.time.LocalDate;

public class Miembro {
    private int miembroId;
    private String nombre;
    private String apellido;
    private LocalDate fechaInscripcion;

    public Miembro(int miembroId, String nombre, String apellido, LocalDate fechaInscripcion) {
        this.miembroId = miembroId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getMiembroId() {
        return miembroId;
    }

    public void setMiembroId(int miembroId) {
        this.miembroId = miembroId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    @Override
    public String toString() {
        return "Miembro{" +
                "miembroId=" + miembroId +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaInscripcion=" + fechaInscripcion +
                '}';
    }
}
