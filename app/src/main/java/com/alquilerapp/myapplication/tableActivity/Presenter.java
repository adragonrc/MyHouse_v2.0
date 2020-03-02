package com.alquilerapp.myapplication.tableActivity;

import com.alquilerapp.myapplication.Base.BasePresenter;
import com.alquilerapp.myapplication.TableCursor;
import com.alquilerapp.myapplication.UTILIDADES.Mensualidad;
import com.alquilerapp.myapplication.UTILIDADES.TPago;

public class Presenter extends BasePresenter<Interfaz.view> implements Interfaz.Presenter {
    private String idAlquiler;
    public Presenter(Interfaz.view view, String idAlquiler) {
        super(view);
        this.idAlquiler = idAlquiler;
    }

    @Override
    public void iniciarComandos() {
        String columnas = Mensualidad.ID+", "+Mensualidad.COSTO+ ", " +Mensualidad.FECHA_I;
        TableCursor tcMensualidad = db.getMensualidadesOfAlquiler(columnas, idAlquiler);

        for (int i = 0; i< tcMensualidad.getCount(); i++){
            String idMensualidad = tcMensualidad.getValue(i,Mensualidad.ID);
            String costo = tcMensualidad.getValue(i,Mensualidad.COSTO);
            view.addTitleMensualidad("Mensualidad: " +i, tcMensualidad.getValue(i,Mensualidad.FECHA_I));
            TableCursor tcPagos = db.getPagosOf(TPago.ID+", "+ TPago.FECHA, idMensualidad);

            for (int j = 0; j<tcPagos.getCount(); j++ ){
                view.addRow(tcPagos.getS()[j][0], tcPagos.getS()[j][1], costo);
            }
        }
    }
}
