package org.example.dao;

import org.example.model.Autor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDaoImpl implements AutorDao {
    private final Connection connection;

    public AutorDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void agregarAutor(Autor autor) {
        String sql = "INSERT INTO autores (autor_id, nombre, apellido, nacionalidad) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, autor.getAutorId());
            statement.setString(2, autor.getNombre());
            statement.setString(3, autor.getApellido());
            statement.setString(4, autor.getNacionalidad());

            statement.executeUpdate();
            System.out.println("Autor agregado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al agregar autor: " + e.getMessage());
        }
    }

    @Override
    public void actualizarAutor(Autor autor) {
        String sql = "UPDATE autores SET nombre = ?, apellido = ?, nacionalidad = ? WHERE autor_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, autor.getNombre());
            statement.setString(2, autor.getApellido());
            statement.setString(3, autor.getNacionalidad());
            statement.setInt(4, autor.getAutorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarAutor(int autorId) {
        String sql = "DELETE FROM autores WHERE autor_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, autorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Autor obtenerAutor(int autorId) {
        String sql = "SELECT * FROM autores WHERE autor_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, autorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Autor(
                        resultSet.getInt("autor_id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("nacionalidad")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Autor> listarAutores() {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM autores";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                autores.add(new Autor(
                        resultSet.getInt("autor_id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("nacionalidad")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }
}