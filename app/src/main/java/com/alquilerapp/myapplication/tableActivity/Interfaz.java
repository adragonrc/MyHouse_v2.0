package com.alquilerapp.myapplication.tableActivity;

import android.content.ContentValues;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.alquilerapp.myapplication.Base.BaseView;

public interface Interfaz {
    interface Presenter{

    }
    interface view extends BaseView {
        void addRow(String ...atts);
        void addTitleMensualidad(String s, String s1);

        void gotoShowPDF(String string, ContentValues datoUsuario);

        void addTable(TableLayout tl);

        ViewGroup getGrup();
    }
}
