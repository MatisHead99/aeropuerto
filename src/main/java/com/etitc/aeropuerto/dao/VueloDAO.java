package com.etitc.aeropuerto.dao;

import com.etitc.aeropuerto.models.Vuelo;
import com.etitc.aeropuerto.util.ConexionBD;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VueloDAO {

    public boolean crear(Vuelo vuelo) {
        String sql = "INSERT INTO vuelos (codigo, destino, origen, hora_salida, hora_llegada, aerolinea, puerta_salida, capacidad, pasajeros, estado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vuelo.getCodigo());
            pstmt.setString(2, vuelo.getDestino());
            pstmt.setString(3, vuelo.getOrigen());
            pstmt.setTimestamp(4, Timestamp.valueOf(vuelo.getHoraSalida()));
            pstmt.setTimestamp(5, Timestamp.valueOf(vuelo.getHoraLlegada()));
            pstmt.setString(6, vuelo.getAerolinea());
            pstmt.setString(7, vuelo.getPuertaSalida());
            pstmt.setInt(8, vuelo.getCapacidad());
            pstmt.setInt(9, vuelo.getPasajeros());
            pstmt.setString(10, vuelo.getEstado());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error creando vuelo: " + e.getMessage());
            return false;
        }
    }

    public Vuelo obtenerPorId(int id) {
        String sql = "SELECT * FROM vuelos WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return mapearVuelo(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo vuelo: " + e.getMessage());
        }
        return null;
    }

    public List<Vuelo> obtenerTodos() {
        List<Vuelo> vuelos = new ArrayList<>();
        String sql = "SELECT * FROM vuelos ORDER BY hora_salida DESC";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                vuelos.add(mapearVuelo(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo vuelos: " + e.getMessage());
        }
        return vuelos;
    }

    public List<Vuelo> obtenerActivos() {
        List<Vuelo> vuelos = new ArrayList<>();
        String sql = "SELECT * FROM vuelos WHERE estado = 'Activo' ORDER BY hora_salida ASC";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                vuelos.add(mapearVuelo(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo vuelos activos: " + e.getMessage());
        }
        return vuelos;
    }

    public boolean actualizar(Vuelo vuelo) {
        String sql = "UPDATE vuelos SET codigo=?, destino=?, origen=?, hora_salida=?, hora_llegada=?, " +
                     "aerolinea=?, puerta_salida=?, capacidad=?, pasajeros=?, estado=? WHERE id=?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vuelo.getCodigo());
            pstmt.setString(2, vuelo.getDestino());
            pstmt.setString(3, vuelo.getOrigen());
            pstmt.setTimestamp(4, Timestamp.valueOf(vuelo.getHoraSalida()));
            pstmt.setTimestamp(5, Timestamp.valueOf(vuelo.getHoraLlegada()));
            pstmt.setString(6, vuelo.getAerolinea());
            pstmt.setString(7, vuelo.getPuertaSalida());
            pstmt.setInt(8, vuelo.getCapacidad());
            pstmt.setInt(9, vuelo.getPasajeros());
            pstmt.setString(10, vuelo.getEstado());
            pstmt.setInt(11, vuelo.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error actualizando vuelo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM vuelos WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error eliminando vuelo: " + e.getMessage());
            return false;
        }
    }

    public List<Vuelo> buscar(String criterio) {
        List<Vuelo> vuelos = new ArrayList<>();
        String sql = "SELECT * FROM vuelos WHERE codigo LIKE ? OR destino LIKE ? OR origen LIKE ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String patron = "%" + criterio + "%";
            pstmt.setString(1, patron);
            pstmt.setString(2, patron);
            pstmt.setString(3, patron);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                vuelos.add(mapearVuelo(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error buscando vuelos: " + e.getMessage());
        }
        return vuelos;
    }

    private Vuelo mapearVuelo(ResultSet rs) throws SQLException {
        Vuelo vuelo = new Vuelo();
        vuelo.setId(rs.getInt("id"));
        vuelo.setCodigo(rs.getString("codigo"));
        vuelo.setDestino(rs.getString("destino"));
        vuelo.setOrigen(rs.getString("origen"));
        vuelo.setHoraSalida(rs.getTimestamp("hora_salida").toLocalDateTime());
        vuelo.setHoraLlegada(rs.getTimestamp("hora_llegada").toLocalDateTime());
        vuelo.setAerolinea(rs.getString("aerolinea"));
        vuelo.setPuertaSalida(rs.getString("puerta_salida"));
        vuelo.setCapacidad(rs.getInt("capacidad"));
        vuelo.setPasajeros(rs.getInt("pasajeros"));
        vuelo.setEstado(rs.getString("estado"));
        return vuelo;
    }
}
