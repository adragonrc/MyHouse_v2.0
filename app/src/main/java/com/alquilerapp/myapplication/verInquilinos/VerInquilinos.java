package com.alquilerapp.myapplication.verInquilinos;

import android.graphics.Color;

import androidx.annotation.NonNull;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alquilerapp.myapplication.Base.BaseActivity;
import com.alquilerapp.myapplication.R;

public class VerInquilinos extends BaseActivity<Interface.Presenter> implements Interface.View{

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
    }

    public void showError() {
    }

    protected void iniciarViews(){
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
