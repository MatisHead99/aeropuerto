package com.etitc.aeropuerto.dao;

import com.etitc.aeropuerto.models.Tiquete;
import com.etitc.aeropuerto.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TiqueteDAO {

    public boolean crear(Tiquete tiquete) {
        String sql = "INSERT INTO tiquetes (numero, vuelo_id, pasajero, asiento, precio, estado) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tiquete.getNumero());
            pstmt.setInt(2, tiquete.getVueloId());
            pstmt.setString(3, tiquete.getPasajero());
            pstmt.setString(4, tiquete.getAsiento());
            pstmt.setDouble(5, tiquete.getPrecio());
            pstmt.setString(6, tiquete.getEstado());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error creando tiquete: " + e.getMessage());
            return false;
        }
    }

    public Tiquete obtenerPorId(int id) {
        String sql = "SELECT * FROM tiquetes WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapearTiquete(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo tiquete: " + e.getMessage());
        }
        return null;
    }

    public Tiquete obtenerPorNumero(String numero) {
        String sql = "SELECT * FROM tiquetes WHERE numero = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, numero);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapearTiquete(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo tiquete por número: " + e.getMessage());
        }
        return null;
    }

    public List<Tiquete> obtenerPorVuelo(int vueloId) {
        List<Tiquete> tiquetes = new ArrayList<>();
        String sql = "SELECT * FROM tiquetes WHERE vuelo_id = ? ORDER BY asiento ASC";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vueloId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                tiquetes.add(mapearTiquete(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo tiquetes del vuelo: " + e.getMessage());
        }
        return tiquetes;
    }

    public List<Tiquete> obtenerTodos() {
        List<Tiquete> tiquetes = new ArrayList<>();
        String sql = "SELECT * FROM tiquetes ORDER BY fecha_emision DESC";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tiquetes.add(mapearTiquete(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo tiquetes: " + e.getMessage());
        }
        return tiquetes;
    }

    public List<Tiquete> obtenerActivos() {
        List<Tiquete> tiquetes = new ArrayList<>();
        String sql = "SELECT * FROM tiquetes WHERE estado = 'Activo' ORDER BY fecha_emision DESC";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tiquetes.add(mapearTiquete(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo tiquetes activos: " + e.getMessage());
        }
        return tiquetes;
    }

    public boolean actualizar(Tiquete tiquete) {
        String sql = "UPDATE tiquetes SET numero=?, vuelo_id=?, pasajero=?, asiento=?, precio=?, estado=? WHERE id=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tiquete.getNumero());
            pstmt.setInt(2, tiquete.getVueloId());
            pstmt.setString(3, tiquete.getPasajero());
            pstmt.setString(4, tiquete.getAsiento());
            pstmt.setDouble(5, tiquete.getPrecio());
            pstmt.setString(6, tiquete.getEstado());
            pstmt.setInt(7, tiquete.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error actualizando tiquete: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM tiquetes WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error eliminando tiquete: " + e.getMessage());
            return false;
        }
    }

    public int contarTiquetesPorVuelo(int vueloId) {
        String sql = "SELECT COUNT(*) as total FROM tiquetes WHERE vuelo_id = ? AND estado = 'Activo'";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vueloId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Error contando tiquetes: " + e.getMessage());
        }
        return 0;
    }

    public double obtenerIngresosPorVuelo(int vueloId) {
        String sql = "SELECT SUM(precio) as total FROM tiquetes WHERE vuelo_id = ? AND estado = 'Activo'";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vueloId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.err.println("Error calculando ingresos: " + e.getMessage());
        }
        return 0.0;
    }

    public boolean asientoOcupado(int vueloId, String asiento) {
        String sql = "SELECT COUNT(*) as total FROM tiquetes WHERE vuelo_id = ? AND asiento = ? AND estado = 'Activo'";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vueloId);
            pstmt.setString(2, asiento);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error verificando asiento: " + e.getMessage());
        }
        return false;
    }

    private Tiquete mapearTiquete(ResultSet rs) throws SQLException {
        Tiquete tiquete = new Tiquete();
        tiquete.setId(rs.getInt("id"));
        tiquete.setNumero(rs.getString("numero"));
        tiquete.setVueloId(rs.getInt("vuelo_id"));
        tiquete.setPasajero(rs.getString("pasajero"));
        tiquete.setAsiento(rs.getString("asiento"));
        tiquete.setPrecio(rs.getDouble("precio"));
        tiquete.setEstado(rs.getString("estado"));
        tiquete.setFechaEmision(rs.getTimestamp("fecha_emision").toLocalDateTime());
        return tiquete;
    }
}
