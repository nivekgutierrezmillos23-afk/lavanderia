package org.example.dao;

import org.example.model.Servicios;
import java.util.List;

public interface ServiciosDAO {
    void crear(Servicios servicio);
    Servicios leer(int servicio_id);
    void actualizar(Servicios servicio);
    void eliminar(int servicio_id);
    List<Servicios> listar();
}
