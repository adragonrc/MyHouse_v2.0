package com.alquilerapp.myapplication.agregarInquilino;

import android.widget.ArrayAdapter;
import android.widget.RadioGroup;

import com.alquilerapp.myapplication.Base.BaseView;
import com.alquilerapp.myapplication.Base.IBasePresenter;
import com.alquilerapp.myapplication.Modelos.ModelUsuario;

import java.util.ArrayList;

public interface Interfaz {
    interface Presenter  extends IBasePresenter{
        boolean doPago(RadioGroup radioGroup);
        void agregarUsuario(ModelUsuario mu, String numCuarto, String mensualidad, String fecha, boolean pago);
         void confirmar();
    }
    interface View extends BaseView {
        void onClickAgregar(android.view.View view);
        void onClickTomarFoto(android.view.View view);
        void onClickCancel(android.view.View view);
        void showError(String error);
        void showDialog(String s);
        void prepararSpinsers(ArrayAdapter<String> a);
        void sinCuartos();
        void close();
    }
}
