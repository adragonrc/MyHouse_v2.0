package com.alquilerapp.myapplication.verUsuario;

import android.content.ContentValues;

import com.alquilerapp.myapplication.Base.BasePresenter;
import com.alquilerapp.myapplication.MyAdminDate;
import com.alquilerapp.myapplication.UTILIDADES.Mensualidad;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.UTILIDADES.TUsuario;

import java.io.IOError;
import java.util.Date;

public class Presentador extends BasePresenter<Interfaz.View> implements Interfaz.Presenter{
    private ContentValues datosCuarto;
    private ContentValues datosUser;
    private ContentValues datosMensualidad;
    private ContentValues datosAlquiler;

    private MyAdminDate adminDate;

    private int dni;
    public Presentador (Interfaz.View view, int dni){
        super(view);
        this.dni = dni;
        adminDate = new MyAdminDate();
        actualizarDatos();
    }
    private void actualizarDatos(){
        datosUser = db.getFilaInUsuariosOf("*", dni);
        datosAlquiler = db.getFilaAlquilerByUserOf("*",datosUser.getAsInteger(TUsuario.DNI));
        datosCuarto = db.getFilaInCuarto("*",datosAlquiler.getAsInteger(TAlquiler.NUMERO_C));
        datosMensualidad = db.getFilaInMensualidadActual("*",datosAlquiler.getAsInteger(TAlquiler.ID));
    }
    @Override
    public void iniciarComandos() {
        if ((adminDate.stringToDate(datosAlquiler.getAsString(TAlquiler.FECHA_C))).before(new Date())) {
            view.showNoPago();
        }else {
            view.showPago();
        }
        view.setTextOfTV(datosUser,datosCuarto,datosAlquiler,datosMensualidad);
        view.modoEdicion();
    }
    @Override
    public void crearPago() {
        String s = adminDate.getFecha(datosAlquiler.getAsString(TAlquiler.FECHA_C));
        if(db.agregarPago(adminDate.getDateFormat().format(new Date()),datosMensualidad.getAsLong(Mensualidad.ID))){
            view.showMensaje("pago agregado");
            db.upDateAlquiler(TAlquiler.FECHA_C,s,datosAlquiler.getAsInteger(TAlquiler.ID));
            actualizarDatos();
            iniciarComandos();
        }else{
            view.showMensaje("Error al pagar");
        }
    }

    @Override
    public void positive(String value, String in) {
        switch (in){
            case TUsuario.DNI:{
            //    db.upDateUsuario(TUsuario.DNI,value,datosUser.getAsInteger(TUsuario.DNI));
                break;
            }
            case TUsuario.NOMBRES:{
                db.upDateUsuario(TUsuario.NOMBRES,value,datosUser.getAsInteger(TUsuario.DNI));
                actualizarDatos();
                iniciarComandos();
                break;
            }
            case TUsuario.APELLIDO_PAT:{
                db.upDateUsuario(TUsuario.APELLIDO_PAT,value,datosUser.getAsInteger(TUsuario.DNI));
                actualizarDatos();
                iniciarComandos();
                break;
            }
            case TUsuario.APELLIDO_MAT:{
                db.upDateUsuario(TUsuario.APELLIDO_MAT,value,datosUser.getAsInteger(TUsuario.DNI));
                actualizarDatos();
                iniciarComandos();
                break;
            }
            case Mensualidad.COSTO:{
                try{
                    Double d  = Double.parseDouble(value);
                    db.agregarMensualidad(d,adminDate.getDateFormat().format(new Date()),datosAlquiler.getAsLong(TAlquiler.ID));
                }catch (IOError error){
                    view.showMensaje(value+ ": no es double");
                }
                break;
            }
            default:
                view.showMensaje("no hay caso para :"+ in);
        }
    }
    @Override
    public ContentValues getUser() {
        return datosUser;
    }

    @Override
    public ContentValues getAlquiler() {
        return datosAlquiler;
    }

    @Override
    public ContentValues getMensualidad() {
        return datosMensualidad;
    }

    @Override
    public ContentValues getCuarto() {
        return datosCuarto;
    }

}
