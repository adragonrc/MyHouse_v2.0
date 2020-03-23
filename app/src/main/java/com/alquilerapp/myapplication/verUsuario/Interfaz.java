package com.alquilerapp.myapplication.verUsuario;

import android.content.ContentValues;
import android.view.View;

import com.alquilerapp.myapplication.Base.BaseView;
import com.alquilerapp.myapplication.Base.IBasePresenter;

import java.text.ParseException;

public interface Interfaz{

    interface Presenter{
        void iniciarComandos() ;
    }
    interface View{
        void setTextOfTV(ContentValues du, ContentValues dC, ContentValues dA, ContentValues dM);

    }
}
