package com.alquilerapp.myapplication.VerCuarto;

import android.content.ContentValues;

import com.alquilerapp.myapplication.Base.BasePresenter;
import com.alquilerapp.myapplication.MyAdminDate;
import com.alquilerapp.myapplication.UTILIDADES.Mensualidad;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.UTILIDADES.TCuarto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Presentador extends BasePresenter<Interface.view> implements Interface.Presenter {

    private ContentValues datosCuarto;
    private ContentValues datosUsuario;
    private ContentValues datosMensualidad;
    private ContentValues datosAlquiler;

    private MyAdminDate adminDate;
    private String numeroCuarto;

    public Presentador(Interface.view view, String numeroCuarto) {
        super(view);
        this.numeroCuarto = numeroCuarto;
    }

    @Override
    public void iniciarComandos(){
        adminDate = new MyAdminDate();
        mostrarDetalles();
    }

    @Override
    public void deshacerContrato(String motivo) {
        int id = datosAlquiler.getAsInteger(TAlquiler.ID);
        db.upDateAlquiler(TAlquiler.VAL, "0", id);
        db.upDateAlquiler(TAlquiler.MOTIVO, motivo, id);
        db.upDateAlquiler(TAlquiler.FECHA_C, adminDate.getFechaActual(), id);
        mostrarDetalles();
    }

    private void actualizarDatos(){
        datosAlquiler = db.getFilaAlquilerByCuartoOf("*", numeroCuarto);
        view.pago();
    }

    @Override
    public void mostrarDetalles(){
        datosCuarto = db.getFilaInCuarto("*", numeroCuarto);
        datosAlquiler = db.getFilaAlquilerByCuartoOf("*", numeroCuarto);
        if (datosAlquiler.size()!=0){
            datosMensualidad = db.getFilaInMensualidadActual("*",datosAlquiler.get(TAlquiler.ID));
            datosUsuario = db.getFilaInUsuariosOf("*",datosAlquiler.get(TAlquiler.DNI));
            if ((adminDate.stringToDate(datosAlquiler.getAsString(TAlquiler.FECHA_C))).before(new Date())) {
                view.noPago();
            }else {
                view.pago();
            }
            view.showCuartoAlquilado(datosCuarto, datosUsuario, datosMensualidad.getAsString(Mensualidad.COSTO));
        }else{
            view.showCuartolibre(datosCuarto);
        }
    }

    @Override
    public ContentValues getDatosAlquiler() {
        return datosAlquiler;
    }

    //private boolean consultarConfirmacion(String s){
    //    return true;
    //}
    private void setValCV(ContentValues cv, String  key, String newValue){
        cv.remove(key);
        cv.put(key, newValue);
    }


    @Override
    public void actualizarMensualidad(String mensualidad) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_i = dateFormat.format(new Date());
        int id = datosAlquiler.getAsInteger(TAlquiler.ID);
        db.agregarMensualidad(Double.parseDouble(mensualidad), fecha_i, id);
        datosMensualidad = db.getFilaInMensualidadActual("*",datosAlquiler.get(TAlquiler.ID));
        view.actualizarMensualidad(mensualidad);
    }

    @Override
    public void actualizarDetalles(String detalles) {
        db.upDateCuarto(TCuarto.DETALLES, detalles, numeroCuarto);
        setValCV(datosCuarto, TCuarto.DETALLES, detalles);
        view.actualizarDetalles(detalles);
    }

    @Override
    public void realizarPago() {
        String s = adminDate.getFecha(datosAlquiler.getAsString(TAlquiler.FECHA_C));
        if(db.agregarPago(adminDate.getDateFormat().format(new Date()),datosMensualidad.getAsLong(Mensualidad.ID))){
            view.showMensaje("pago agregado");
            db.upDateAlquiler(TAlquiler.FECHA_C,s,datosAlquiler.getAsInteger(TAlquiler.ID));
            actualizarDatos();
        }else{
            view.showMensaje("Error al pagar");
        }
    }
}
