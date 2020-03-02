package com.alquilerapp.myapplication.ListAlquileres;

import android.content.ContentValues;
import android.graphics.drawable.Drawable;

import com.alquilerapp.myapplication.Base.BasePresenter;
import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.TableCursor;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.UTILIDADES.TCuarto;
import com.alquilerapp.myapplication.UTILIDADES.TUsuario;
import com.alquilerapp.myapplication.mi_casa.Models.ModelAlquilerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Presentador extends BasePresenter<Interface.Vista> {
    private String numeroCuarto;
    public Presentador(Interface.Vista view, String numeroCuarto) {
        super(view);
        this.numeroCuarto = numeroCuarto;
    }

    @Override
    public void iniciarComandos() {
        view.mostarListAlquileres(getListAlquileres());
    }
    private ArrayList<ModelAlquilerView> getListAlquileres(){
        ArrayList<ModelAlquilerView> list;
        String columnas = "*";
        TableCursor tcAlquileres = db.getAllAlquileres(columnas, TCuarto.NUMERO, numeroCuarto);
        list = new ArrayList<>();
        for (int i = 0; i < tcAlquileres.getCount(); i++) {
            ContentValues nombres = db.getFilaInUsuariosOf(TUsuario.NOMBRES+ ", " + TUsuario.APELLIDO_PAT, tcAlquileres.getValue(i, TAlquiler.DNI));
            Drawable drawable;
            if (tcAlquileres.getValue(i,TAlquiler.VAL).equals("0"))
            drawable = view.getContext().getResources().getDrawable(R.drawable.circle_blue);
            else drawable = view.getContext().getResources().getDrawable(R.drawable.circle_red);
            list.add(new ModelAlquilerView(tcAlquileres.getValue(i,TAlquiler.ID),
                    tcAlquileres.getValue(i, TUsuario.DNI),
                    nombres.getAsString(TUsuario.NOMBRES) + " " +nombres.getAsString(TUsuario.APELLIDO_PAT),
                    tcAlquileres.getValue(i, TAlquiler.FECHA_C),
                    tcAlquileres.getValue(i, TAlquiler.NUMERO_C), drawable));
        }
        return list;
    }
}
