package org.example.dao;

import org.example.model.Autor;
import java.util.List;

public interface AutorDao {
    void agregarAutor(Autor autor);
    void actualizarAutor(Autor autor);
    void eliminarAutor(int autorId);
    Autor obtenerAutor(int autorId);
    List<Autor> listarAutores();
}
