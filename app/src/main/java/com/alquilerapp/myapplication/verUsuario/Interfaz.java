package com.alquilerapp.myapplication.verUsuario;

import android.content.ContentValues;
import android.view.View;

import com.alquilerapp.myapplication.Base.BaseView;

public interface Interfaz{

    interface Presenter{
        void crearPago();
        void positive(String value, String in);
        ContentValues getUser();
        ContentValues getAlquiler();
        ContentValues getCuarto();
        ContentValues getMensualidad();
    }
    interface View extends BaseView {
        void setTextOfTV(ContentValues du, ContentValues dC, ContentValues dA, ContentValues dM);

        void showNoPago();
        void showPago();
        void onClickPagarMensualidad(android.view.View view);
        void modoEdicion();
    }
}
