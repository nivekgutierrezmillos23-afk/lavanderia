package org.example.dao;

import org.example.model.Vehiculos;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class VehiculosDAOimpl implements VehiculosDAO {

    private final Connection connection;

    public VehiculosDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Vehiculos v) {
        String sql = "INSERT INTO vehiculos (clienteid, marca, modelo, placa, color, tipo) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, v.getId_cliente());
            st.setString(2, v.getMarca());
            st.setString(3, v.getModelo());
            st.setString(4, v.getPlaca());
            st.setString(5, v.getColor());
            st.setString(6, v.getTipo());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vehiculos leer(int id) {
        String sql = "SELECT * FROM vehiculos WHERE vehiculoid=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                Vehiculos v = new Vehiculos(
                        rs.getInt("clienteid"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("placa"),
                        rs.getString("color"),
                        rs.getString("tipo")
                );

                return v;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void actualizar(Vehiculos v) {
        String sql = "UPDATE vehiculos SET clienteid=?, marca=?, modelo=?, placa=?, color=?, tipo=? WHERE vehiculoid=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, v.getId_cliente());
            st.setString(2, v.getMarca());
            st.setString(3, v.getModelo());
            st.setString(4, v.getPlaca());
            st.setString(5, v.getColor());
            st.setString(6, v.getTipo());

            st.setInt(7, 1);

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM vehiculos WHERE vehiculoid=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Vehiculos> listar() {
        List<Vehiculos> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";

        try (PreparedStatement st = connection.prepareStatement(sql)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                Vehiculos v = new Vehiculos(
                        rs.getInt("clienteid"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("placa"),
                        rs.getString("color"),
                        rs.getString("tipo")
                );

                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
