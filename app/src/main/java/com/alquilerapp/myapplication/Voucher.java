package com.alquilerapp.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;

import com.alquilerapp.myapplication.UTILIDADES.Mensualidad;
import com.alquilerapp.myapplication.UTILIDADES.TCuarto;

import java.util.ArrayList;

public class Voucher {

    private String [] heares = {"ID", "Nombre", "Apellido"};
    private String shortText = "Hola hola";
    private String longText = "Nunca consideres el estudio como oblicacion, sino como diversion";
    private PDF pdf;

    private ContentValues datosCuarto;
    private ContentValues datosUsuario;
    private ContentValues datosMensualidad;
    private ContentValues datosAlquiler;

    public Voucher(ContentValues datosCuarto, ContentValues datosUsuario, ContentValues datosMensualidad, ContentValues datosAlquiler) {
        this.datosCuarto = datosCuarto;
        this.datosUsuario = datosUsuario;
        this.datosMensualidad = datosMensualidad;
        this.datosAlquiler = datosAlquiler;
    }

    public PDF crearEjemplo(){
        pdf = new PDF();
        pdf.openDocument();
        pdf.addMetaData("Clientes", "Ventanas", "Mari");
        pdf.addTitles("Tienda Codigo", "Clentes", "07.03.2020");
        pdf.addParagraph(shortText);
        pdf.addParagraph(longText);
        pdf.createTable(heares, getClients());
        pdf.closeDocument();
        return pdf;
    }
    public PDF crearVoucher(){
        MyAdminDate myAdminDate = new MyAdminDate();

        pdf = new PDF();
        pdf.openDocument();
        pdf.addMetaData("Alquiler", "voucher", "AlexRodriguez");
        pdf.addParagraph("RECIBO DE ALQUILER");
        pdf.addParagraph("(direccion de casa) - N° HABITACION " + datosCuarto.getAsString(TCuarto.NUMERO));
        pdf.addParagraph(MyAdminDate.getFechaActual());
        pdf.addParagraph("PAGO REALIZADO N°:  #    00000");
        pdf.addParagraph("--------------------------------------------------------");
        pdf.addParagraph("VALOR DE PAGO:     S/  "+ datosMensualidad.getAsString(Mensualidad.COSTO));
        pdf.addParagraph("ESTE ES UN COMPROVANTE DE PAGO \nDE PRUEBA");
        pdf.addParagraph("--------------------------------------------------------");
        pdf.addParagraph("GRACIAS POR PAGAR A TIEMPO");
        pdf.closeDocument();
        return pdf;
    }

    private ArrayList<String []> getClients(){
        ArrayList<String []> rows = new ArrayList<>();
        rows.add(new String[]{"1", "A", "B"});
        rows.add(new String[]{"2", "C", "H"});
        rows.add(new String[]{"3", "D", "G"});
        rows.add(new String[]{"4", "E", "F"});
        return rows;
    }
}
