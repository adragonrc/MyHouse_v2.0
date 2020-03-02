package com.alquilerapp.myapplication.mi_casa;

import android.content.ContentValues;
import android.graphics.drawable.Drawable;

import com.alquilerapp.myapplication.Base.BasePresenter;
import com.alquilerapp.myapplication.MyAdminDate;
import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.TableCursor;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.UTILIDADES.TCuarto;
import com.alquilerapp.myapplication.UTILIDADES.TUsuario;
import com.alquilerapp.myapplication.mi_casa.Models.ModelAlquilerView;
import com.alquilerapp.myapplication.mi_casa.Models.ModelCuartoView;
import com.alquilerapp.myapplication.mi_casa.Models.ModelUserView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Presenter extends BasePresenter<Interface.View> implements Interface.Presenter {
    private MyAdminDate myAdminDate;
    public Presenter(Interface.View view) {
        super(view);
        myAdminDate = new MyAdminDate();
    }

    @Override
    public void iniciarComandos() {

        //View.mostratCuartos(getListCuartos());
        //View.mostrarAlquileres(getListAlquileres());
        view.mostratCuartos(getListCuartos());
    }

    @Override
    public void terminarAlquiler(String motivo, String id) {
        db.upDateAlquiler(TAlquiler.VAL, "0", id);
        db.upDateAlquiler(TAlquiler.MOTIVO, motivo, id);
        //view.mostrarAlquileres(getListAlquileres());
        verTodos();
    }

    @Override
    public void verTodos() {
        view.mostratCuartos(getListCuartos());
    }

    @Override
    public void verCuartosAlquilados() {
        view.mostratCuartos(getListCuartosAlquilados());
    }

    @Override
    public void verCuartosLibres() {view.mostratCuartos(getListCuartosLibres());}

    private ArrayList<ModelCuartoView> getListCuartosLibres(){
        ArrayList<ModelCuartoView> modelAlquilers= new ArrayList<>();
        String columnas = "*";
        TableCursor tc = db.getCuartosLibres(columnas);
        for (int i = 0; i < tc.getCount(); i++) {
            Drawable drawable = view.getContext().getResources().getDrawable(R.drawable.circle_blue);
            modelAlquilers.add(
                    new ModelCuartoView(tc.getValue(i,TCuarto.NUMERO), tc.getValue(i,TCuarto.DETALLES), tc.getValue(i,TCuarto.PRECIO_E), drawable));
        }
        return modelAlquilers;
    }
    private ArrayList<ModelCuartoView> getListCuartosAlquilados(){
        ArrayList<ModelCuartoView> modelAlquilers= new ArrayList<>();
        String columnas = "*";
        TableCursor tc = db.getCuartosAlquilados(columnas);
        for (int i = 0; i < tc.getCount(); i++) {
            Drawable drawable = view.getContext().getResources().getDrawable(R.drawable.circle_red);
            modelAlquilers.add(
                    new ModelCuartoView(tc.getValue(i,TCuarto.NUMERO), tc.getValue(i,TCuarto.DETALLES), tc.getValue(i,TCuarto.PRECIO_E), drawable));
        }
        return modelAlquilers;
    }
    private ArrayList<ModelCuartoView> getListCuartos() {
        ArrayList<ModelCuartoView> modelAlquilers = new ArrayList<>();
        String columnas = "*";
        TableCursor tc = db.getAllCuartos(columnas);
        ArrayList<String> list = db.getCuartosAlquilados();
        for (int i = 0; i < tc.getCount(); i++) {
            String numC = tc.getValue(i, TCuarto.NUMERO);
            Drawable drawable;
            if (list.contains(numC)) {
                drawable = view.getContext().getResources().getDrawable(R.drawable.circle_red);
            } else {
                drawable = view.getContext().getResources().getDrawable(R.drawable.circle_blue);
            }
            modelAlquilers.add(
                    new ModelCuartoView(numC, tc.getValue(i, TCuarto.DETALLES), tc.getValue(i, TCuarto.PRECIO_E), drawable));
        }
        return modelAlquilers;
    }
}
