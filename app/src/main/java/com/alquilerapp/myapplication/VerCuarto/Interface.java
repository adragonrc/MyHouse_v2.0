package com.alquilerapp.myapplication.VerCuarto;

import android.content.ContentValues;
import android.view.View;

import com.alquilerapp.myapplication.Base.BaseView;
import com.alquilerapp.myapplication.Base.IBasePresenter;

import java.io.File;

public interface Interface {
    interface Presenter extends IBasePresenter {
        ContentValues getDatosAlquiler();
        void mostrarDetalles();
        void deshacerContrato(String motivo);
        void actualizarMensualidad(String mensualidad);
        void actualizarDetalles(String detalles);
        void realizarPago();
    }
    interface view extends BaseView {
        void noPago();
        void pago();
        void showCuartolibre(ContentValues cuarto);
        void showCuartoAlquilado(ContentValues cuarto, ContentValues Usuario, String mensualidad);
        void onClickVerInquilino(android.view.View view);
        void onClickTerminarAlquiler(android.view.View view);
        void onClickVermas(android.view.View view);
        void onClickEditarMensualidad(android.view.View view);
        void onClickEditarDetalles(android.view.View view);
        void onClickConfirMensualidad(android.view.View view);
        void onClickConfirDetalles(android.view.View view);
        void mostrarPDF (File pdfFile, ContentValues datosUsuario);
        void onClickPhoto(View view);
        void onClickVerAlquileres(android.view.View view);
        void actualizarMensualidad(String mensualidad);
        void actualizarDetalles(String detalles);

    }
}
