package com.alquilerapp.myapplication.registrarcasa;

import android.view.View;

import com.alquilerapp.myapplication.Base.BaseView;
import com.alquilerapp.myapplication.Base.IBasePresenter;

public interface interfaz {
    interface presentador extends IBasePresenter {
        void ingresar( String dir, String corr);
    }
    interface view extends BaseView {
        void ocAceptar(View view);
        void salir();

        void avanzar();
    }
}
