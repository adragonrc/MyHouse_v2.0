package com.alquilerapp.myapplication.registrarcasa;

import android.content.Context;
import android.content.SharedPreferences;

import com.alquilerapp.myapplication.Base.BasePresenter;
import com.alquilerapp.myapplication.R;

import java.text.ParseException;

public class Presentador extends BasePresenter<interfaz.view> implements interfaz.presentador {
    public Presentador(interfaz.view view) {
        super(view);
    }

    private boolean validarImputs(String ...s){
        for (String s1 : s)
            if (s1.equals("")) {
                view.showMensaje("Campo vacio");
                return false;
            }
        return true;
    }
    @Override
    public void ingresar(String dir, String corr) {
        if(!validarImputs( dir, corr)) {
            view.showMensaje("Campo vacio");
            return;
        };
        Context c = view.getContext();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(c.getString(R.string.direccion), dir);
        editor.putString(c.getString(R.string.correo), corr);
        editor.putBoolean(c.getString(R.string.start), true);
        editor.commit();
        view.salir();
    }

    @Override
    public void iniciarComandos() {
        Context c = view.getContext();
        if(sp.getBoolean(c.getString(R.string.start), false)){
            view.avanzar();
        }else {
        }
    }
}
