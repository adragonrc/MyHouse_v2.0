package com.alquilerapp.myapplication.agregarCuarto;

import android.content.Intent;
import android.view.View;

import com.alquilerapp.myapplication.Base.BaseView;
import com.alquilerapp.myapplication.Base.IBasePresenter;

public interface Interfaz{
    interface Presenter extends IBasePresenter {
        void insertarCuarto(String numCuarto, String precio, String detalles, String URL);
    }
    interface View extends BaseView {
        void onClickAgregar(android.view.View view);
        void onClickCamara(android.view.View view);
    }

}
