package com.alquilerapp.myapplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyAdminDate {
    private Date date;
    private DateFormat dateFormat;
    public MyAdminDate(){
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }
    public String[] getFechas(){
        String f[] = new String[2];
        f[0] = dateFormat.format(new Date());
        f[1] = "";
        try {
            Date date = dateFormat.parse(f[0]);
            date.setMonth(date.getMonth() + 1);
            f[1] = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return f;
    }
    public String getFecha(String fechai){
        String f = null;
        try {
            Date date = dateFormat.parse(fechai);
            date.setMonth(date.getMonth() + 1);
            f = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return f;
    }
    public String getFechaActual(){
        return dateFormat.format(new Date());
    }
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public Date stringToDate(String s){
        try {
            Date d= dateFormat.parse(s);
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
