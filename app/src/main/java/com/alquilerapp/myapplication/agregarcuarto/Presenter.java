package com.alquilerapp.myapplication.agregarcuarto;
import android.content.Intent;

import com.alquilerapp.myapplication.AdministradorCamara;
import com.alquilerapp.myapplication.Base.BasePresenter;
import com.alquilerapp.myapplication.menuPrincipal.MenuPricipal;

public class Presenter extends BasePresenter<Interfaz.View> implements Interfaz.Presenter {
    private AdministradorCamara adc;
    public Presenter(Interfaz.View view) {
        super(view);
    }

    public void insertarCuarto(String numCuarto, String precio, String detalles, String URL){
        if (!numCuarto.equals("") && !precio.equals("")) {
            if (db.existIntoCuarto(numCuarto)) {
                view.showMensaje("Numero de cuarto ya existe");
            } else {
                db.agregarCuarto(numCuarto, detalles, precio, URL);
                view.showMensaje("Agregado");
                view.getContext().startActivity(new Intent(view.getContext(), MenuPricipal.class));
            }
        }else{
            view.showMensaje("Campo vacio");
        }
    }

    @Override
    public void iniciarComandos() {
    }

}
