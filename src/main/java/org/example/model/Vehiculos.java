package org.example.model;

public class Vehiculos {
    private int vehiculo_id;
    private int id_cliente;
    private String marca;
    private String modelo;
    private String placa;
    private String color;
    private String tipo;

    public Vehiculos() {
    }

    public Vehiculos(int id_cliente, String marca, String modelo, String placa, String color, String tipo) {
        this.id_cliente = id_cliente;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.color = color;
        this.tipo = tipo;
    }

    public int getVehiculo_id() {
        return vehiculo_id;
    }

    public void setVehiculo_id(int vehiculo_id) {
        this.vehiculo_id = vehiculo_id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return String.format("Vehiculo [#%d] | Placa: %-7s | %s %s | Tipo: %s | Dueño ID: %d",
                vehiculo_id, placa, marca, modelo, tipo, id_cliente);
    }
}