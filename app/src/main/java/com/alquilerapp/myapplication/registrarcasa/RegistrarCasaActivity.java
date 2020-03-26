package com.alquilerapp.myapplication.registrarcasa;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alquilerapp.myapplication.Base.BaseActivity;
import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.menuPrincipal.MenuPricipal;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrarCasaActivity extends BaseActivity<interfaz.presentador> implements interfaz.view {
    public static final int ON_BACK_PRESED = -1;
    public static final String ON_EXIT = "exit";
    private TextInputEditText etDireccion;
    private TextInputEditText etCorreo;

    @Override
    protected void iniciarComandos() {
        setTitle("Registro");
        if(getIntent().getBooleanExtra(ON_EXIT, false)){
            finishAffinity();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_registrar_casa;
    }

    @NonNull
    @Override
    protected interfaz.presentador createPresenter() {
        return new Presentador(this);
    }

    @Override
    protected void iniciarViews() {
        etDireccion = findViewById(R.id.etDireccion);
        etCorreo = findViewById(R.id.etCorreo);
    }

    @Override
    public void ocAceptar(View view){
        String direccion = etDireccion.getText().toString();
        String correo = etCorreo.getText().toString();
        presenter.ingresar(direccion, correo);
    }
    @Override
    public void salir(){
        avanzar();
    }

    @Override
    public void avanzar() {
        startActivity(new Intent(this, MenuPricipal.class));
    }

}
