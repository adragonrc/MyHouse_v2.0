package com.alquilerapp.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.alquilerapp.myapplication.HistorialCasa.HistorialCasaActivity;
import com.alquilerapp.myapplication.agregarCuarto.AgregarCuarto;
import com.alquilerapp.myapplication.agregarInquilino.AgregarInquilino;
import com.alquilerapp.myapplication.menuPrincipal.Interface;
import com.alquilerapp.myapplication.mi_casa.MiCasaActivity;
import com.alquilerapp.myapplication.verInquilinos.VerInquilinos;

public class MenuPricipal extends AppCompatActivity implements Interface.View {
    DataBaseAdmin bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        bd = new DataBaseAdmin(this, null, 1);
        bd.getWritableDatabase();
        //bd.startScrips();
    }

    public void onClickMasAlquiler(View view) {
        startActivity(new Intent(this, AgregarInquilino.class));
    }

    @Override
    public void onClickMiCasa(View view) {
        startActivity(new Intent(this, MiCasaActivity.class));
    }

    public void onClickVerAlquiler(View view) {
        startActivity(new Intent(this, VerInquilinos.class));
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
                setMessage("¿Cerrar Sesión?").
                setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }
}
