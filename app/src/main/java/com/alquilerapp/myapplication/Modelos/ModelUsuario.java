package com.alquilerapp.myapplication.Modelos;

public class ModelUsuario {
    private String dni;
    private String nombres;
    private String apellidoPat;
    private String apellidoMat;
    private String alert;

    public ModelUsuario(String dni, String nombres, String apellidoPat, String apellidoMat, String alert) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.alert = alert;
    }

    public String getAlert() {
        return alert;
    }

    public String getDni() {
        return dni;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }
}
