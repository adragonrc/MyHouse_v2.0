package com.alquilerapp.myapplication.agregarInquilino;

import android.widget.ArrayAdapter;
import android.widget.RadioGroup;

import com.alquilerapp.myapplication.Base.BaseView;
import com.alquilerapp.myapplication.Base.IBasePresenter;

import java.util.ArrayList;

public interface Interfaz {
    interface Presenter <V extends BaseView> extends IBasePresenter<V>{
        boolean doPago(RadioGroup radioGroup);
        void agregarUsuario(String DNI, String nombres, String apellidoPat, String apellidoMat, String uri, String numCuarto, String mensualidad, String fecha, boolean pago);
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
