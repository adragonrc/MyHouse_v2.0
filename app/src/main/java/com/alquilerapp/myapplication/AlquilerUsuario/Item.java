package com.alquilerapp.myapplication.AlquilerUsuario;

public class Item {
    private String idAlquiler;
    private String motivoDeSalida;

    public Item(String idAlquiler, String motivoDeSalida) {
        this.idAlquiler = idAlquiler;
        this.motivoDeSalida = motivoDeSalida;
    }

    public String getIdAlquiler() {
        return idAlquiler;
    }

    public String getMotivoDeSalida() {
        return motivoDeSalida;
    }
}
