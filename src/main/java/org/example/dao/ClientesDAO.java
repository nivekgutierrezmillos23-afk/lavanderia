package org.example.dao;

import org.example.model.Clientes;
import java.util.List;

public interface ClientesDAO {
    void crear(Clientes cliente);

    Clientes leer(int cliente_id);

    void actualizar(Clientes cliente);

    void eliminar(int cliente_id);

    List<Clientes> listar();
}
