package com.alquilerapp.myapplication.alquilerusuario;

import com.alquilerapp.myapplication.Base.BasePresenter;
import com.alquilerapp.myapplication.TableCursor;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;

import java.util.ArrayList;

public class Presenter extends BasePresenter<Interface.Vista> implements Interface.Presentador {
    private int dni;

    public Presenter(Interface.Vista view, int dni) {
        super(view);
        this.dni = dni;
    }

    @Override
    public void iniciarComandos(){
        view.mostrarRecycleView(getList());
    }
    private ArrayList<Item> getList(){
        ArrayList<Item> arrayList = new ArrayList<>();
        TableCursor tc = db.getAllAlquileres(TAlquiler.ID + ", " + TAlquiler.MOTIVO, TAlquiler.DNI, dni);
        for (int i = 0; i < tc.getCount(); i++) {
            String id = tc.getValue(i, TAlquiler.ID);
            String motivo = tc.getValue(i, TAlquiler.MOTIVO);
            arrayList.add(new Item(id, motivo));
        }
        return arrayList;
    }
}
