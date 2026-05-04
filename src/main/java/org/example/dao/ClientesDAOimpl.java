package org.example.dao;

import org.example.model.Clientes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAOimpl implements ClientesDAO {
    private final Connection connection;

    public ClientesDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Clientes cliente) {
        String sql = "INSERT INTO Clientes (nombre, apellido, telefono, email, direccion) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getDireccion());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al crear cliente: " + e.getMessage());
        }
    }

    @Override
    public Clientes leer(int cliente_id) {
        String sql = "SELECT * FROM Clientes WHERE cliente_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cliente_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCliente(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al leer cliente: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Clientes cliente) {
        String sql = "UPDATE Clientes SET nombre=?, apellido=?, telefono=?, email=?, direccion=? WHERE cliente_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getDireccion());
            ps.setInt(6, cliente.getCliente_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int cliente_id) {
        String sql = "DELETE FROM Clientes WHERE cliente_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cliente_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
        }
    }

    @Override
    public List<Clientes> listar() {
        List<Clientes> lista = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearCliente(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    private Clientes mapearCliente(ResultSet rs) throws SQLException {
        Clientes c = new Clientes(
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("telefono"),
                rs.getString("email"),
                rs.getString("direccion")
        );
        c.setCliente_id(rs.getInt("cliente_id"));
        return c;
    }
}