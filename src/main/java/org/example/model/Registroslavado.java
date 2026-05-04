package org.example.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Registroslavado {
    private int registro_id;
    private int id_vehiculo;
    private int id_servicio;
    private LocalDate fechaLavado;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private double precioTotal;

    public Registroslavado() {
    }

    public Registroslavado(int id_vehiculo, int id_servicio, LocalDate fechaLavado, LocalTime horaInicio, LocalTime horaFin, double precioTotal) {
        this.id_vehiculo = id_vehiculo;
        this.id_servicio = id_servicio;
        this.fechaLavado = fechaLavado;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.precioTotal = precioTotal;
    }

    public int getRegistro_id() {
        return registro_id;
    }

    public void setRegistro_id(int registro_id) {
        this.registro_id = registro_id;
    }

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public LocalDate getFechaLavado() {
        return fechaLavado;
    }

    public void setFechaLavado(LocalDate fechaLavado) {
        this.fechaLavado = fechaLavado;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Override
    public String toString() {
        return String.format("Lavado [#%d] | Vehiculo: %d | Servicio: %d | Fecha: %s | Horario: %s - %s | Total: $%.2f",
                registro_id, id_vehiculo, id_servicio, fechaLavado, horaInicio, horaFin, precioTotal);
    }
}