package com.etitc.aeropuerto.dao;

import com.etitc.aeropuerto.models.Personal;
import com.etitc.aeropuerto.util.ConexionBD;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PersonalDAO {

    public boolean crear(Personal personal) {
        String sql = "INSERT INTO personal (nombre, cedula, puesto, telefono, email, usuario, contrasena, estado, fecha_ingreso) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, personal.getNombre());
            pstmt.setString(2, personal.getCedula());
            pstmt.setString(3, personal.getPuesto());
            pstmt.setString(4, personal.getTelefono());
            pstmt.setString(5, personal.getEmail());
            pstmt.setString(6, personal.getUsuario());
            pstmt.setString(7, personal.getContrasena());
            pstmt.setString(8, personal.getEstado());
            pstmt.setTimestamp(9, Timestamp.valueOf(personal.getFechaIngreso()));

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error creando personal: " + e.getMessage());
            return false;
        }
    }

    public Personal obtenerPorId(int id) {
        String sql = "SELECT * FROM personal WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapearPersonal(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo personal: " + e.getMessage());
        }
        return null;
    }

    public Personal autenticar(String usuario, String contrasena) {
        String sql = "SELECT * FROM personal WHERE usuario = ? AND contrasena = ? AND estado = 'Activo'";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario);
            pstmt.setString(2, contrasena);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapearPersonal(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error autenticando usuario: " + e.getMessage());
        }
        return null;
    }

    public List<Personal> obtenerTodos() {
        List<Personal> personal = new ArrayList<>();
        String sql = "SELECT * FROM personal ORDER BY nombre ASC";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                personal.add(mapearPersonal(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo personal: " + e.getMessage());
        }
        return personal;
    }

    public List<Personal> obtenerActivos() {
        List<Personal> personal = new ArrayList<>();
        String sql = "SELECT * FROM personal WHERE estado = 'Activo' ORDER BY nombre ASC";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                personal.add(mapearPersonal(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo personal activo: " + e.getMessage());
        }
        return personal;
    }

    public boolean actualizar(Personal personal) {
        String sql = "UPDATE personal SET nombre=?, cedula=?, puesto=?, telefono=?, email=?, usuario=?, contrasena=?, estado=? WHERE id=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, personal.getNombre());
            pstmt.setString(2, personal.getCedula());
            pstmt.setString(3, personal.getPuesto());
            pstmt.setString(4, personal.getTelefono());
            pstmt.setString(5, personal.getEmail());
            pstmt.setString(6, personal.getUsuario());
            pstmt.setString(7, personal.getContrasena());
            pstmt.setString(8, personal.getEstado());
            pstmt.setInt(9, personal.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error actualizando personal: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM personal WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error eliminando personal: " + e.getMessage());
            return false;
        }
    }

    private Personal mapearPersonal(ResultSet rs) throws SQLException {
        Personal p = new Personal();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setCedula(rs.getString("cedula"));
        p.setPuesto(rs.getString("puesto"));
        p.setTelefono(rs.getString("telefono"));
        p.setEmail(rs.getString("email"));
        p.setUsuario(rs.getString("usuario"));
        p.setContrasena(rs.getString("contrasena"));
        p.setEstado(rs.getString("estado"));
        p.setFechaIngreso(rs.getTimestamp("fecha_ingreso").toLocalDateTime());
        return p;
    }
}
