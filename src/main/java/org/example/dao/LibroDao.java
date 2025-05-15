package org.example.dao;

import org.example.model.Libro;

import java.util.List;

public interface LibroDao {
    void agregarLibro(Libro libro);
    void actualizarLibro(Libro libro);
    void eliminarLibro(int libroId);
    Libro leer(int libroId);
    List<Libro> listarLibro();
}
