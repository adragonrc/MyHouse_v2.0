package com.alquilerapp.myapplication.mi_casa.Models;

import android.graphics.drawable.Drawable;

public class ModelCuartoView {
    private String letra;
    private String numero;
    private String descripcion;
    private String precio;
    private Drawable background;

    public ModelCuartoView(String numero, String descripcion, String precio, Drawable background) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.precio = precio;
        this.background = background;
        this.letra = numero.substring(0,1);

    }

    public String getNumero() {
        return numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public String getLetra() {
        return letra;
    }

    public Drawable getBackground() {
        return background;
    }
}
