package org.example.dao;

import org.example.model.Servicios;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ServiciosDAOimpl implements ServiciosDAO {

    private final Connection connection;

    public ServiciosDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Servicios s) {
        String sql = "INSERT INTO servicios (nombre, precio) VALUES (?,?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setString(1, s.getNombre());
            st.setDouble(2, s.getPrecio());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Servicios leer(int id) {
        String sql = "SELECT * FROM servicios WHERE servicioid=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Servicios s = new Servicios(
                        rs.getString("nombre"),
                        rs.getDouble("precio")
                );
                return s;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void actualizar(Servicios s) {
        String sql = "UPDATE servicios SET nombre=?, precio=? WHERE servicioid=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setString(1, s.getNombre());
            st.setDouble(2, s.getPrecio());

            // ⚠️ como tu modelo NO tiene id
            st.setInt(3, 1); // ← cámbialo cuando agregues servicioid

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM servicios WHERE servicioid=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Servicios> listar() {
        List<Servicios> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicios";

        try (PreparedStatement st = connection.prepareStatement(sql)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                Servicios s = new Servicios(
                        rs.getString("nombre"),
                        rs.getDouble("precio")
                );

                lista.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}

