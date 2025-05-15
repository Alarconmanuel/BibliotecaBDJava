package org.example.model;

import java.util.Date;

public class Prestamo {
    private int prestamoId;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private int libroId;
    private int miembroId;
    private Libro libro;
    private Miembro miembro;

    public Prestamo() {
    }

    public Prestamo(int prestamoId, java.sql.Date fechaPrestamo, java.sql.Date fechaDevolucion, int libroId, int miembroId) {
    }

    public Prestamo(int prestamoId, Date fechaPrestamo, Date fechaDevolucion, int libroId, int miembroId, Libro libro, Miembro miembro) {
        this.prestamoId = prestamoId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.libroId = libroId;
        this.miembroId = miembroId;
        this.libro = libro;
        this.miembro = miembro;
    }

    public int getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(int prestamoId) {
        this.prestamoId = prestamoId;
    }

    public java.sql.Date getFechaPrestamo() {
        return (java.sql.Date) fechaPrestamo;
    }

    public void setFechaprestamo(java.sql.Date date) {
    }

    public java.sql.Date getFechaDevolucion() {
        return (java.sql.Date) fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public int getMiembroId() {
        return miembroId;
    }

    public void setMiembroId(int miembroId) {
        this.miembroId = miembroId;
    }


    @Override
    public String toString() {
        return "Prestamo{" +
                "prestamoId=" + prestamoId +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", libroId=" + libroId +
                ", miembroId=" + miembroId +
                ", libro=" + libro +
                ", miembro=" + miembro +
                '}';
    }

}


