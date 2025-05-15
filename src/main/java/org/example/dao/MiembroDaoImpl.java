package org.example.dao;

import org.example.model.Miembro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MiembroDaoImpl implements MiembroDao {
    private final Connection connection;

    public MiembroDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void agregarMiembro(Miembro miembro) {
        String sql = "INSERT INTO miembros (miembro_id, nombre, apellido, fecha_inscripcion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, miembro.getMiembroId());
            statement.setString(2, miembro.getNombre());
            statement.setString(3, miembro.getApellido());
            statement.setDate(4, Date.valueOf(miembro.getFechaInscripcion()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarMiembro(Miembro miembro) {
        String sql = "UPDATE miembros SET nombre = ?, apellido = ?, fecha_inscripcion = ? WHERE miembro_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, miembro.getNombre());
            statement.setString(2, miembro.getApellido());
            statement.setDate(3, Date.valueOf(miembro.getFechaInscripcion()));
            statement.setInt(4, miembro.getMiembroId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarMiembro(int miembroId) {
        String sql = "DELETE FROM miembros WHERE miembro_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, miembroId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Miembro obtenerMiembro(int miembroId) {
        String sql = "SELECT * FROM miembros WHERE miembro_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, miembroId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Miembro(
                        resultSet.getInt("miembro_id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getDate("fecha_inscripcion").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Miembro> listarMiembros() {
        List<Miembro> miembros = new ArrayList<>();
        String sql = "SELECT * FROM miembros";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                miembros.add(new Miembro(
                        resultSet.getInt("miembro_id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getDate("fecha_inscripcion").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return miembros;
    }
}