package com.alquilerapp.myapplication.MainActyviti;

import android.view.View;

import com.alquilerapp.myapplication.Base.BaseView;

public interface Interface {
    interface presenter{
        void ingresar(String usuario, String contraseña);
    }
    interface view extends BaseView {
        void ingresar();
        void negarIngreso();
        void onClickIngresar(View view);
        void onClikCambiarContraseña(View view);
    }
}
