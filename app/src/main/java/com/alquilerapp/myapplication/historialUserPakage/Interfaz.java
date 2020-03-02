package com.alquilerapp.myapplication.historialUserPakage;

import android.content.ContentValues;
import android.view.View;
import android.widget.ImageView;

import com.alquilerapp.myapplication.AlquilerUsuario.Item;
import com.alquilerapp.myapplication.Base.BaseView;

import java.util.ArrayList;

public interface Interfaz {
    interface presenter{
        void actualizarNombres(String nombres);
        void actualizarApePat(String apellidoPat);
        void actualizarApeMat(String apellidoMat);
        void setImage(ImageView i, String path);
    }
    interface view extends BaseView {
        void mostrarAlerta();
        void noMostrarAlerta();
        void mostrarDatosUsuario(ContentValues datos, String i);
        void modoError(String error);
        void ocEditarNombres(View view);
        void ocEditarApePat(View view);
        void ocEditarApeMat(View view);
        void ocConfirNombres(View view);
        void ocConfirApePat(View view);
        void ocConfirApeMat(View view);
        void ocVerMas(View view);
        void actualizarNombres(String nombres);
        void actualizarApePat(String apellidoPaterno);
        void actualizarApeMat(String apellidoMaterno);

        void salir();
    }
}
