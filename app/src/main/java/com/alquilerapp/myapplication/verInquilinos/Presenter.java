package com.alquilerapp.myapplication.verInquilinos;

import com.alquilerapp.myapplication.Base.BasePresenter;
import com.alquilerapp.myapplication.UTILIDADES.TUsuario;

public class Presenter extends BasePresenter<Interface.View> implements Interface.Presenter {
    private String idAlquileres[];

    public Presenter(Interface.View view) {
        super(view);
    }

    @Override
    public void iniciarComandos(){
    }
}
