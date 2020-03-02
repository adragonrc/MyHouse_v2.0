package com.alquilerapp.myapplication.AlquilerUsuario;

import android.view.View;

import com.alquilerapp.myapplication.Base.BaseView;

import java.util.ArrayList;

public interface Interface {
    interface Presentador{

    }
    interface Vista extends BaseView {
        void mostrarRecycleView(ArrayList<Item> list);
        void onClickItemListener(View view);
    }
}
