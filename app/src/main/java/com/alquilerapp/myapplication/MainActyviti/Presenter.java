package com.alquilerapp.myapplication.MainActyviti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.alquilerapp.myapplication.Base.BasePresenter;
import com.alquilerapp.myapplication.DataBaseAdmin;
import com.alquilerapp.myapplication.MenuPricipal;

import java.text.ParseException;

public class Presenter extends BasePresenter<Interface.view> implements Interface.presenter {
    public Presenter(Interface.view view) {
        super(view);
    }

    @Override
    public void iniciarComandos(){
/*        if (!sp.getBoolean(DataBaseAdmin.KEY_START_SCRIPS,false)) {
            db.startScrips();
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean(DataBaseAdmin.KEY_START_SCRIPS,true);
            e.commit();
        }
        */
    }
    private String getUsuario(){
        return "alex";
    }

    private String getContraseña(){
        return "hola";
    }
    @Override
    public void ingresar(String usuario, String contraseña) {
        if (usuario.equals(getUsuario())) {
            if (contraseña.equals(getContraseña())) {
                view.showMensaje("Bienvenido");
                view.ingresar();
            } else {
                view.negarIngreso();
                view.showMensaje("Contraseña Invalida");
            }
        } else {
            view.negarIngreso();
            view.showMensaje("Usuario Invalido");
        }
    }
}
