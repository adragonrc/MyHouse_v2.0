package com.alquilerapp.myapplication.menuPrincipal;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.view.View;

import com.alquilerapp.myapplication.Base.BaseActivity;
import com.alquilerapp.myapplication.Base.IBasePresenter;
import com.alquilerapp.myapplication.historialcasa.HistorialCasaActivity;
import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.agregarcuarto.AgregarCuarto;
import com.alquilerapp.myapplication.agregarInquilino.AgregarInquilino;
import com.alquilerapp.myapplication.mi_casa.MiCasaActivity;
import com.alquilerapp.myapplication.registrarcasa.RegistrarCasaActivity;

public class MenuPricipal extends BaseActivity<IBasePresenter> implements Interface.View {

    @Override
    protected void iniciarComandos() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_menu_principal;
    }

    @NonNull
    @Override
    protected IBasePresenter createPresenter() {
        return new Presentador(this);
    }

    @Override
    protected void iniciarViews() {
        setTitle("Menu Principal");
    }

    public void onClickMasAlquiler(View view) {
        startActivity(new Intent(this, AgregarInquilino.class));
    }

    @Override
    public void onClickMiCasa(View view) {

        startActivity(new Intent(this, MiCasaActivity.class));
    }

    @Override
    public void showRegistrarCasa() {
        startActivity(new Intent(this, RegistrarCasaActivity.class));
    }

    public void onClickMasCuartos(View view) {
        startActivity(new Intent(this, AgregarCuarto.class));
    }

    public void onClickHistorialCasa(View view) {
        startActivity(new Intent(this, HistorialCasaActivity.class));
    }

    @Override
    public void onBackPressed() {
        alertDialog().show();
    }

    public AlertDialog alertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mensaje").
                setMessage("¿Desea salir?").
                setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(MenuPricipal.this, RegistrarCasaActivity.class);
                        i.putExtra(RegistrarCasaActivity.ON_EXIT, true);
                        startActivity(i);
                        System.exit(0);
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }
}
