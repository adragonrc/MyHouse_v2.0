package com.alquilerapp.myapplication.alquilerusuario;

import android.view.View;

import com.alquilerapp.myapplication.Base.BaseView;
import com.alquilerapp.myapplication.Base.IBasePresenter;

import java.util.ArrayList;

public interface Interface {
    interface Presentador extends IBasePresenter {

    }
    interface Vista extends BaseView {
        void mostrarRecycleView(ArrayList<Item> list);
        void onClickItemListener(View view);
    }
}
