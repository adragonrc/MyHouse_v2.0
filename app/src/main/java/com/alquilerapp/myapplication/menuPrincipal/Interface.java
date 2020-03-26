package com.alquilerapp.myapplication.menuPrincipal;

import com.alquilerapp.myapplication.Base.BaseView;

public interface Interface {
    interface View extends BaseView {
        void onClickHistorialCasa(android.view.View view);
        void onClickMasCuartos(android.view.View view);
        void onClickMasAlquiler(android.view.View view);
        void onClickMiCasa(android.view.View view);

        void showRegistrarCasa();
    }
}
