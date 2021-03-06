package com.alquilerapp.myapplication.Base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.alquilerapp.myapplication.R;


public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements BaseView{
    protected P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        presenter = createPresenter();
        iniciarViews();
        iniciarComandos();
        presenter.iniciarComandos();

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
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
    }

    protected void ocultarTeclado(){
        View view = getCurrentFocus();
        if (view != null){
            view.clearFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
