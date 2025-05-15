package org.example.dao;

import org.example.model.Prestamo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDaoImpl implements PrestamoDao {
    private final Connection connection;

    public PrestamoDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void agregarPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO prestamos (prestamo_id, fecha_prestamo, fecha_devolucion, libro_id, miembro_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prestamo.getPrestamoId());
            statement.setDate(2, prestamo.getFechaPrestamo());
            statement.setDate(3, prestamo.getFechaDevolucion());
            statement.setInt(4, prestamo.getLibroId());
            statement.setInt(5, prestamo.getMiembroId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarPrestamo(Prestamo prestamo) {
        String sql = "UPDATE prestamos SET fecha_prestamo = ?, fecha_devolucion = ?, libro_id = ?, miembro_id = ? WHERE prestamo_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, prestamo.getFechaPrestamo());
            statement.setDate(2, prestamo.getFechaDevolucion());
            statement.setInt(3, prestamo.getLibroId());
            statement.setInt(4, prestamo.getMiembroId());
            statement.setInt(5, prestamo.getPrestamoId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarPrestamo(int prestamoId) {
        String sql = "DELETE FROM prestamos WHERE prestamo_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prestamoId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Prestamo obtenerPrestamo(int prestamoId) {
        String sql = "SELECT * FROM prestamos WHERE prestamo_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prestamoId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Prestamo(
                        resultSet.getInt("prestamo_id"),
                        resultSet.getDate("fecha_prestamo"),
                        resultSet.getDate("fecha_devolucion"),
                        resultSet.getInt("libro_id"),
                        resultSet.getInt("miembro_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Prestamo> listarPrestamos() {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                prestamos.add(new Prestamo(
                        resultSet.getInt("prestamo_id"),
                        resultSet.getDate("fecha_prestamo"),
                        resultSet.getDate("fecha_devolucion"),
                        resultSet.getInt("libro_id"),
                        resultSet.getInt("miembro_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }
}