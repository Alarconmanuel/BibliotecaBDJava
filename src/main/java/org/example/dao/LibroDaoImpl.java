package org.example.dao;

import org.example.model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDaoImpl implements LibroDao{
    private final Connection connection;

    public LibroDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void agregarLibro(Libro libro) {
        String sql = "INSERT INTO libros (libro_id, titulo, genero, autor_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, libro.getLibroId());
            statement.setString(2, libro.getTitulo());
            statement.setString(3, libro.getGenero());
            statement.setInt(4, libro.getAutorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Libro leer(int libroId) {
        String sql = "SELECT * FROM libros WHERE libro_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, libroId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Libro(
                        rs.getInt("libro_id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getInt("autor_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizarLibro(Libro libro) {
        String sql = "UPDATE libros SET titulo = ?, genero = ?, autor_id = ? WHERE libro_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getGenero());
            statement.setInt(3, libro.getAutorId());
            statement.setInt(4, libro.getLibroId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarLibro(int libroId) {
        String sql = "DELETE FROM libros WHERE libro_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, libroId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Libro> listarLibro() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                libros.add(new Libro(
                        rs.getInt("libro_id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getInt("autor_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }
}
