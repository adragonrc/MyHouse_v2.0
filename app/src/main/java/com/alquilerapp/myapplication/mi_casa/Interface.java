package com.alquilerapp.myapplication.mi_casa;

import com.alquilerapp.myapplication.Base.BaseView;
import com.alquilerapp.myapplication.mi_casa.Models.ModelCuartoView;

import java.util.ArrayList;

public interface Interface{
    interface Presenter{
        void terminarAlquiler(String motivo, String id);
        void verTodos();
        void verCuartosAlquilados();
        void verCuartosLibres();
    }

    interface View extends BaseView {
        void mostratCuartos(ArrayList<ModelCuartoView> list);
        //     void mostrarAlquileres(ArrayList<ModelAlquilerView> list);
        //void mostrarUsuarios(ArrayList<ModelUserView> list);

        void showDialogOptions(String idAlquiler);
        void showDialogImput(String idAlquiler);
    }
}
