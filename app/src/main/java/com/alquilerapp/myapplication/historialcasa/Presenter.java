package com.alquilerapp.myapplication.historialcasa;

import android.content.ContentValues;
import android.graphics.drawable.Drawable;

import com.alquilerapp.myapplication.Base.BasePresenter;
import com.alquilerapp.myapplication.MyAdminDate;
import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.TableCursor;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.UTILIDADES.TUsuario;
import com.alquilerapp.myapplication.mi_casa.Models.ModelAlquilerView;
import com.alquilerapp.myapplication.mi_casa.Models.ModelUserView;

import java.util.ArrayList;

public class Presenter extends BasePresenter<Interface.View> implements Interface.Presenter {
    private MyAdminDate myAdminDate;
    public Presenter(Interface.View view) {
        super(view);
    }

    @Override
    public void iniciarComandos(){
        myAdminDate = new MyAdminDate();
        mostrarUsuarios();
    }

    @Override
    public void mostrarUsuarios() {
        view.mostarListUsuarios(getListUsuarios());
    }
    private ArrayList<ModelUserView> getListUsuarios(){
        ArrayList<ModelUserView> list= new ArrayList<>();
        TableCursor tc = db.getallUsuarios("*");
        for (int i= 0; i<tc.getCount(); i++){
            Drawable drawable;
            String dni = tc.getValue(i, TUsuario.DNI);
            if(db.usuarioAlertado(dni))
                drawable =  view.getContext().getResources().getDrawable(R.drawable.circle_red);
            else drawable = view.getContext().getResources().getDrawable(R.drawable.circle_blue);
            list.add(new ModelUserView(dni, tc.getValue(i, TUsuario.NOMBRES) + " " + tc.getValue(i, TUsuario.APELLIDO_PAT), drawable));
        }
        return  list;
    }

    @Override
    public void mostrarAlquileres() {
        view.mostarListAlquileres(getListAlquileres());
    }

    private ArrayList<ModelAlquilerView> getListAlquileres(){
        ArrayList<ModelAlquilerView> list= new ArrayList<>();
        String columnas = "*";
        TableCursor tcAlquileres = db.getAllAlquileres(columnas);
        for (int i = 0; i < tcAlquileres.getCount(); i++) {
            ContentValues nombres = db.getFilaInUsuariosOf(TUsuario.NOMBRES+ ", " + TUsuario.APELLIDO_PAT, tcAlquileres.getValue(i,TAlquiler.DNI));

            String fechac = tcAlquileres.getValue(i, TAlquiler.FECHA_C);
            String id =tcAlquileres.getValue(i,TAlquiler.ID);
            String dni = tcAlquileres.getValue(i, TUsuario.DNI);
            String noombres = nombres.getAsString(TUsuario.NOMBRES) + " " +nombres.getAsString(TUsuario.APELLIDO_PAT);
            String numeroCuarto = tcAlquileres.getValue(i, TAlquiler.NUMERO_C);
            Drawable drawable = view.getContext().getResources().getDrawable(R.drawable.circle_blue);

            list.add(new ModelAlquilerView(id, dni, noombres, fechac,numeroCuarto, drawable));
        }
        return list;
    }
}
