package com.alquilerapp.myapplication.ListAlquileres;

import com.alquilerapp.myapplication.Base.BaseView;
import com.alquilerapp.myapplication.mi_casa.Models.ModelAlquilerView;

import java.util.ArrayList;

public interface Interface {
    interface Presentador{
    }
    interface Vista extends BaseView {
        void mostarListAlquileres(ArrayList<ModelAlquilerView> list);
    }
}
