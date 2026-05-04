package org.example.dao;

import org.example.model.Registroslavado;
import java.util.List;

public interface RegistroLavadoDAO {
    void crear(Registroslavado registro);

    Registroslavado leer(int registro_id);

    void actualizar(Registroslavado registro);

    void eliminar(int registro_id);

    List<Registroslavado> listar();
}