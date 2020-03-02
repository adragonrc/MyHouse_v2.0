package com.alquilerapp.myapplication.tableActivity;

import com.alquilerapp.myapplication.Base.BaseView;

public interface Interfaz {
    interface Presenter{

    }
    interface view extends BaseView {
        void addRow(String ...atts);
        void addTitleMensualidad(String s, String s1);
    }
}
