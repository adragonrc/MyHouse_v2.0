package com.alquilerapp.myapplication.historialUserPakage;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.ImageView;

import com.alquilerapp.myapplication.AlquilerUsuario.Item;
import com.alquilerapp.myapplication.Base.BasePresenter;
import com.alquilerapp.myapplication.TableCursor;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.UTILIDADES.TUsuario;

import java.util.ArrayList;

public class Presenter extends BasePresenter<Interfaz.view> implements Interfaz.presenter {

    private String dni;
    public Presenter(Interfaz.view view, String dni) {
        super(view);
        this.dni = dni;
    }

    @Override
    public void iniciarComandos() {
        if (dni !=null) {
            solicitarDatos();
        }else {
            view.showMensaje("DNI no encontrado");
            view.salir();
        }
    }
    public void setImage(ImageView i, String path){
        if(path == null) return;
        if(path.equals("")) return;
        Bitmap bm = BitmapFactory.decodeFile(path);
        i.setImageBitmap(bm);
    }
    private void solicitarDatos() {
        try {
            ContentValues datosUsuario = db.getFilaInUsuariosOf("*", dni);
            String cont = db.contAlquileresOf(TAlquiler.DNI, dni);
            view.mostrarDatosUsuario(datosUsuario, cont);
            if (db.usuarioAlertado(dni)){
                view.mostrarAlerta();
            }else view.noMostrarAlerta();
        }catch (Error e){
            view.modoError("fallo: " + e.getMessage());
        }
    }

    @Override
    public void actualizarNombres(String nombres) {
        db.upDateUsuario(TUsuario.NOMBRES, nombres, dni);
        view.actualizarNombres(nombres);
    }

    @Override
    public void actualizarApePat(String apellidoPat) {
        db.upDateUsuario(TUsuario.APELLIDO_PAT, apellidoPat, dni);
        view.actualizarApePat(apellidoPat);
    }

    @Override
    public void actualizarApeMat(String apellidoMat) {
        db.upDateUsuario(TUsuario.APELLIDO_MAT, apellidoMat, dni);
        view.actualizarApeMat(apellidoMat);
    }
}
