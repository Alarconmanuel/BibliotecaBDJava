package org.example.dao;

import org.example.model.Prestamo;

import java.util.List;

public interface PrestamoDao {
    void agregarPrestamo(Prestamo prestamo);
    void actualizarPrestamo(Prestamo prestamo);
    void eliminarPrestamo(int prestamoId);
    Prestamo obtenerPrestamo(int prestamoId);
    List<Prestamo> listarPrestamos();
}
