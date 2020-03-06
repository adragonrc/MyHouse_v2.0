package com.alquilerapp.myapplication.HistorialCasa;

import com.alquilerapp.myapplication.Base.BaseView;
import com.alquilerapp.myapplication.Base.IBasePresenter;
import com.alquilerapp.myapplication.mi_casa.Models.ModelAlquilerView;
import com.alquilerapp.myapplication.mi_casa.Models.ModelUserView;

import java.util.ArrayList;

public interface Interface {
    interface Presenter extends IBasePresenter {
        void mostrarUsuarios();
        void mostrarAlquileres();
    }
    interface View extends BaseView {
        void mostarListUsuarios(ArrayList<ModelUserView> list);
        void mostarListAlquileres(ArrayList<ModelAlquilerView> list);
    }
}
