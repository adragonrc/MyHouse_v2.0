package com.alquilerapp.myapplication.UTILIDADES;

public class TPago {
    public static final String T_NOMBRE = "pagos";
    public static final String ID = "idPago";
    public static final String FECHA = "fechaPago";
    public static final String DNI = TUsuario.DNI;
    public static final String ID_M=  Mensualidad.ID;
    public static final String CREATE_TABLE =
            "create table " + T_NOMBRE + "(" +
                    ID + " integer primary key autoincrement not null,"+
                    FECHA + " varchar(10)," +
                    ID_M+ " integer," +
                    DNI+ " integer," +
                    "foreign key ("+ ID_M + ")references " + Mensualidad.T_NOMBRE +"("+Mensualidad.ID+"),"+
                    "foreign key ("+ DNI  + ")references " + TUsuario.T_NOMBRE+"("+TUsuario.DNI+")"+
                    ");";
}
