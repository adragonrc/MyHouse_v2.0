package com.alquilerapp.myapplication.Base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.widget.Toast;

import com.alquilerapp.myapplication.R;

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

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
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

    public void modificarTransicion(){
        ChangeBounds fade =  new ChangeBounds();
        //Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
    }
}
