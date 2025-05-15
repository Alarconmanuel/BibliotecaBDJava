package org.example.dao;

import org.example.model.Miembro;

import java.util.List;

public interface MiembroDao {
    void agregarMiembro(Miembro miembro);
    void actualizarMiembro(Miembro miembro);
    void eliminarMiembro(int miembroId);
    Miembro obtenerMiembro(int miembroId);
    List<Miembro> listarMiembros();
}
