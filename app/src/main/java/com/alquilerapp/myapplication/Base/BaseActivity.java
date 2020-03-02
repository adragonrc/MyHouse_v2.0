package com.alquilerapp.myapplication.Base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.IOError;


public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements BaseView{
    protected P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        presenter = createPresenter();
        iniciarViews();
        iniciarComandos();
        try {
            presenter.iniciarComandos();
        }catch (java.text.ParseException e){
            Toast.makeText(this,e.getErrorOffset(),Toast.LENGTH_LONG).show();
        }
    }
    public Context getContext(){
        return this;
    }
    protected abstract void iniciarComandos();

    protected abstract int getLayout();

    @NonNull
    protected abstract P createPresenter();

    protected abstract void iniciarViews();

    @Override
    public void showMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

    }
}
