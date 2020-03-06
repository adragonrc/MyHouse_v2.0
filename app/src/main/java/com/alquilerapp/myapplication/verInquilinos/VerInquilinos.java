package com.alquilerapp.myapplication.verInquilinos;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alquilerapp.myapplication.Base.BaseActivity;
import com.alquilerapp.myapplication.R;

public class VerInquilinos extends BaseActivity<Interface.Presenter> implements Interface.View{
    private LinearLayout layout;
    private ScrollView sv;
    private TextView tvError;

    @Override
    protected void iniciarComandos() {
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_ver_inquilinos;
    }

    @NonNull
    @Override
    protected Interface.Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void agregarFragmento(String idAlquiler){
        /*LinearLayoutUser llu = new LinearLayoutUser(getLayoutInflater(),getContext(),getSupportFragmentManager());
        View View = llu.createView(idAlquiler);
        layout.addView(View);*/
    }

    public void showError() {
        sv.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);
    }

    protected void iniciarViews(){
        layout = findViewById(R.id.llInquilinos);
        sv = findViewById(R.id.svContenedor);
        tvError = findViewById(R.id.mensajeError);
    }

    private View.OnTouchListener onTouchListener  = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:{
                    v.setBackgroundColor(Color.rgb(130,130,130));
                    break;
                }
                case MotionEvent.ACTION_UP:{
                    v.setBackgroundColor(Color.argb(0,255,255,255));
                    break;
                }
            }
            return false;
        }
    };
}
