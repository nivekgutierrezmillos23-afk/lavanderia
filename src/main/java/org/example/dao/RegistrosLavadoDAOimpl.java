package org.example.dao;

import org.example.model.Registroslavado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrosLavadoDAOimpl implements RegistroLavadoDAO {
    private final Connection connection;

    public RegistrosLavadoDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Registroslavado registro) {
        String sql = "INSERT INTO registroslavado (vehiculoid, servicioid, fechalavado, horainicio, horafin, preciototal) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, registro.getId_vehiculo());
            st.setInt(2, registro.getId_servicio());
            st.setDate(3, Date.valueOf(registro.getFechaLavado()));
            st.setTime(4, Time.valueOf(registro.getHoraInicio()));
            st.setTime(5, Time.valueOf(registro.getHoraFin()));
            st.setDouble(6, registro.getPrecioTotal());
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al registrar lavado: " + e.getMessage());
        }
    }

    @Override
    public Registroslavado leer(int registro_id) {
        String sql = "SELECT * FROM registroslavado WHERE registroid = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, registro_id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return mapearRegistro(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar registro: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Registroslavado registro) {
        String sql = "UPDATE registroslavado SET vehiculoid=?, servicioid=?, fechalavado=?, horainicio=?, horafin=?, preciototal=? WHERE registroid=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, registro.getId_vehiculo());
            st.setInt(2, registro.getId_servicio());
            st.setDate(3, Date.valueOf(registro.getFechaLavado()));
            st.setTime(4, Time.valueOf(registro.getHoraInicio()));
            st.setTime(5, Time.valueOf(registro.getHoraFin()));
            st.setDouble(6, registro.getPrecioTotal());
            st.setInt(7, registro.getRegistro_id());
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar registro: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int registro_id) {
        String sql = "DELETE FROM registroslavado WHERE registroid = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, registro_id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar registro: " + e.getMessage());
        }
    }

    @Override
    public List<Registroslavado> listar() {
        List<Registroslavado> lista = new ArrayList<>();
        String sql = "SELECT * FROM registroslavado";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearRegistro(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar registros: " + e.getMessage());
        }
        return lista;
    }

    private Registroslavado mapearRegistro(ResultSet rs) throws SQLException {
        Registroslavado r = new Registroslavado(
                rs.getInt("vehiculoid"),
                rs.getInt("servicioid"),
                rs.getDate("fechalavado").toLocalDate(),
                rs.getTime("horainicio").toLocalTime(),
                rs.getTime("horafin").toLocalTime(),
                rs.getDouble("preciototal")
        );
        r.setRegistro_id(rs.getInt("registroid")); // Asignamos el ID autogenerado
        return r;
    }
}

