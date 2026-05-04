package org.example.dao;

import org.example.model.Vehiculos;
import java.util.List;

public interface VehiculosDAO {
    void crear(Vehiculos vehiculo);
    Vehiculos leer(int vehiculo_id);
    void actualizar(Vehiculos vehiculo);
    void eliminar(int vehiculo_id);
    List<Vehiculos> listar();
}
