package com.alquilerapp.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.Toast;

import com.alquilerapp.myapplication.DataBase.DataBaseInterface;
import com.alquilerapp.myapplication.UTILIDADES.Mensualidad;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.UTILIDADES.TCuarto;
import com.alquilerapp.myapplication.UTILIDADES.TPago;
import com.alquilerapp.myapplication.UTILIDADES.TUsuario;

import java.util.ArrayList;

public class DataBaseAdmin extends SQLiteOpenHelper implements DataBaseInterface {
    private Context context;
    public static final String DATABASE_NAME = "alquiler";
    public static final String KEY_START_SCRIPS = "start_scrips";
    private static String WHERE = " WHERE ";
    private static String SELECT= " SELECT ";
    private static String FROM = " FROM ";
    private ContentValues cv;

    public DataBaseAdmin(Context context,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, 1);
        this.context = context;
        cv = new ContentValues();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(TUsuario.CREATE_TABLE);
            db.execSQL(TCuarto.CREATE_TABLE);
            db.execSQL(TPago.CREATE_TABLE);
            db.execSQL(TAlquiler.CREATE_TABLE);
            db.execSQL(Mensualidad.CREATE_TABLE);
            Toast.makeText(context, "Base de datos creada..", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Error al crear la Base de datos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private ContentValues consultaInFila(String sql){
        SQLiteDatabase editor = this.getWritableDatabase();
        Cursor cursor = editor.rawQuery(sql, null);
        ContentValues cv= new ContentValues();
        if (cursor.moveToFirst()) cv=cursorToCV(cursor);
        editor.close();
        cursor.close();
        return cv;
    }
    private TableCursor consultarAll(String sql){
        SQLiteDatabase editor = getWritableDatabase();
        Cursor c = editor.rawQuery(sql, null);
        TableCursor tc = new TableCursor(c);
        editor.close();
        c.close();
        return tc;
    }

    private boolean agregar(String tableName){
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        long l = writableDatabase.insert(tableName,null,cv);
        writableDatabase.close();
        cv.clear();
        return l!=-1;
    }

    private void upDate(String consulta){
        SQLiteDatabase editor = this.getWritableDatabase();
        editor.execSQL(consulta);
        editor.close();
    }
    public ContentValues getFilaInCuarto(String columnas, Object numCuarto){
        String sql = "select "+columnas+" from "+ TCuarto.T_NOMBRE+" where "+TCuarto.NUMERO+" = '"+numCuarto+ "';";
        return consultaInFila(sql);
    }
    public ContentValues getFilaInMensualidadActual(String columnas, Object idAlquiler){
        String sql = "select "+columnas+" from " + Mensualidad.T_NOMBRE + " where " + Mensualidad.ID +
                " = (select max(" + Mensualidad.ID + ") from " + Mensualidad.T_NOMBRE + "  where " + Mensualidad.ID_A + " = " + idAlquiler + ") ";
        return consultaInFila(sql);
    }

    public TableCursor getMensualidadesOfAlquiler(String columnas, Object idAlquiler){
        String sql = "select "+columnas+" from " + Mensualidad.T_NOMBRE + " where "+ Mensualidad.ID_A + " = " + idAlquiler + ";";
        return consultarAll(sql);
    }
    public ContentValues getFilaInUsuariosOf(String columnas, Object DNI){
        String sql = "select "+columnas+" from "+ TUsuario.T_NOMBRE+" where "+TUsuario.DNI +" = "+DNI;
        return consultaInFila(sql);
    }
    public String getValueOn(String columna, String tableName, String key, Object value){
        SQLiteDatabase  editor = this.getWritableDatabase();
        Cursor c = editor.rawQuery("select "+ columna + " from " + tableName + " where " + key +" = '"+value+"';" , null);
        String s  = "";
        if (c.moveToFirst()){
            s = c.getString(0);
        }
        return s;
    }
    public TableCursor getAlquileresValidos(String columnas, String key, Object value){
        String sql = "select " + columnas+ " from " + TAlquiler.T_NOMBRE +
                " where " +key+ "= '" + value + "' and " + TAlquiler.VAL + "= 1;";
        return consultarAll(sql);
    }

    public ArrayList<String> getCuartosAlquilados(){
        String sql = "select " + TAlquiler.NUMERO_C+ " from " + TAlquiler.T_NOMBRE + " where " + TAlquiler.VAL + "= 1;";
        ArrayList<String > list = new ArrayList<>();
        SQLiteDatabase editor = getWritableDatabase();
        Cursor cursor = editor.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                list.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<String> getDniEnCasa(){
        String sql = "select " + TAlquiler.DNI+ " from " + TAlquiler.T_NOMBRE + " where " + TAlquiler.VAL + "= 1;";
        ArrayList<String > list = new ArrayList<>();
        SQLiteDatabase editor = getWritableDatabase();
        Cursor cursor = editor.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                list.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public TableCursor getAllAlquileres(String columnas, String key, Object value){
        String sql = "select " + columnas+ " from " + TAlquiler.T_NOMBRE +
                " where " +key+ "= '" + value + "'; ";
        return consultarAll(sql);
    }
    public TableCursor getAllAlquileres(String columnas){
        String sql = "select " + columnas+ " from " + TAlquiler.T_NOMBRE ;
        return consultarAll(sql);
    }


    @Override
    public TableCursor getAllCuartos(String columnas) {
        String sql = "select " + columnas+ " from " + TCuarto.T_NOMBRE;
        return consultarAll(sql);
    }
    public TableCursor getCuartosLibres(String columnas) {
        String sql = "select "+columnas +" from "+TCuarto.T_NOMBRE +" where "+TCuarto.NUMERO+" not in (select "+TAlquiler.NUMERO_C+" from "+TAlquiler.T_NOMBRE+" where "+TAlquiler.VAL+" = 1);" ;
        return consultarAll(sql);
    }

    public TableCursor getCuartosAlquilados(String columnas) {
        String sql = "select " + columnas+ " from " + TCuarto.T_NOMBRE +" natural join " + TAlquiler.T_NOMBRE + " where " +TAlquiler.VAL +" = 1; ";
        return consultarAll(sql);
    }

    @Override
    public TableCursor getallUsuarios(String columnas) {
        String sql = "select " + columnas + " from " + TUsuario.T_NOMBRE ;
        return consultarAll(sql);
    }

    public ContentValues getFilaAlquilerByCuartoOf(String columnas, Object numCuarto){
        String sql = "select " + columnas+ " from " + TAlquiler.T_NOMBRE +
                " where " + TAlquiler.NUMERO_C + "= '" + numCuarto + "' and " + TAlquiler.VAL + "= 1;";
        return consultaInFila(sql);
    }
    public ContentValues getFilaAlquilerOf(String columnas, Object id){
        String sql = "select " + columnas+ " from " + TAlquiler.T_NOMBRE +
                " where " + TAlquiler.ID+ "= " + id + " and " + TAlquiler.VAL + "= 1;";
        return consultaInFila(sql);
    }
    public ContentValues getFilaAlquilerByUserOf(String columnas, Object DNI){
        String sql = "select " + columnas+ " from " + TAlquiler.T_NOMBRE +
                " where " + TAlquiler.DNI+ "= " + DNI+ " and " + TAlquiler.VAL + "= 1;";
        return consultaInFila(sql);
    }

    public TableCursor getPagosOf(String columnas, Object idMensualidad){
        String sql = "select "+columnas+" from "+TPago.T_NOMBRE +" where " +TPago.ID_M +" = '"+idMensualidad+"';";
        return consultarAll(sql);
    }

    private String[] getCols(String sql){
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery(sql,null);
        String[] s = null;
        if (fila.moveToFirst()) s = rowInCursorToString(fila);
        fila.close();
        return s;
    }

    public String[] getIdOfAllAlquileres(){
        String consulta ="select "+ TAlquiler.ID+" from " + TAlquiler.T_NOMBRE + " where "+ TAlquiler.VAL + " = 1";
        return getCols(consulta);
    }

    public String[] consultarNumerosDeCuarto(){
        return getCols("select "+ TCuarto.NUMERO + " from "+ TCuarto.T_NOMBRE);
    }

    private String[] rowInCursorToString(Cursor cursor){
        String s[] = new String[cursor.getCount()];
        for (int i = 0; i<s.length; i++,cursor.moveToNext()){
            s[i] = cursor.getString(0);
        }
        return s;
    }

    public String contAlquileresOf(String key, Object value){
        SQLiteDatabase editor = getWritableDatabase();

        Cursor cursor = editor.rawQuery(SELECT +"count(*)" +FROM+ TAlquiler.T_NOMBRE + WHERE + key + " ='" + value +"'; ", null);
        String s = "-1";
        if (cursor.moveToFirst()){
            s = cursor.getString(0);
        }
        return  s;
    }

    public void upDateUsuario(String columna, Object valor, Object DNI){
        String consulta = "update "+ TUsuario.T_NOMBRE + " set "+columna +" = '" + valor +"' where "+ TUsuario.DNI +" = '"+DNI+"';";
        upDate(consulta);
    }
    public void upDateCuarto(String columna, Object valor , Object numeroDeCuarto){
        String consulta = "update "+ TCuarto.T_NOMBRE + " set "+columna +" = '" + valor +"' where "+ TCuarto.NUMERO+" = '"+ numeroDeCuarto + "';";
        upDate(consulta);
    }
    public void upDateAlquiler(String columna, String valor, Object id){
        upDate("update "+ TAlquiler.T_NOMBRE + " set "+columna +" = '" + valor +"' where "+ TAlquiler.ID+" = "+ id);
    }
    public boolean agregarCuarto(String numCuarto, String detalles, String precio, String URL){
        cv.put(TCuarto.NUMERO, numCuarto);
        cv.put(TCuarto.DETALLES, detalles);
        cv.put(TCuarto.PRECIO_E, precio);
        cv.put(TCuarto.URL, URL);
        return agregar(TCuarto.T_NOMBRE);
    }
    public boolean usuarioAlertado(Object DNI){
        SQLiteDatabase editor = getWritableDatabase();
        Cursor c = editor.rawQuery("select * from "+TAlquiler.T_NOMBRE + " where " + TAlquiler.DNI + " = '" + DNI + "' and " + TAlquiler.ALERT +"= 1;", null);
        return  c.moveToNext();
    }
    @Override
    public boolean agregarInquilino(int DNI,String nombres, String apellidoPat, String apellidoMat, String URI){
        cv.put(TUsuario.DNI, DNI);
        cv.put(TUsuario.NOMBRES, nombres);
        cv.put(TUsuario.APELLIDO_PAT, apellidoPat);
        cv.put(TUsuario.APELLIDO_MAT, apellidoMat);
        cv.put(TUsuario.URI, URI);
        return agregar(TUsuario.T_NOMBRE);
    }
    public boolean agregarAlquiler(int DNI, String numC, String fecha, String fecha_c){
        cv.put(TAlquiler.FECHA, fecha);
        cv.put(TAlquiler.FECHA_C, fecha_c);
        cv.put(TAlquiler.VAL, "1");
        cv.put(TAlquiler.DNI, DNI);
        cv.put(TAlquiler.NUMERO_C, numC);
        cv.put(TAlquiler.ALERT, false);
        return agregar(TAlquiler.T_NOMBRE);
    }
    public boolean agregarMensualidad(double costo, String fecha_i, long idA){
        cv.put(Mensualidad.COSTO, costo);
        cv.put(Mensualidad.FECHA_I, fecha_i);
        cv.put(Mensualidad.ID_A, idA);
        return agregar(Mensualidad.T_NOMBRE);
    }
    public boolean agregarPago(String fecha, long idM){
        cv.put(TPago.FECHA, fecha);
        cv.put(TPago.ID_M, idM);
        return agregar(TPago.T_NOMBRE);
    }

    public void agregarInquilinoExist(int DNI, String numC, double costo, @NonNull String fecha_i, @Nullable String fecha_c){
        SQLiteDatabase writableDatabase;
        boolean f = true;
        if (fecha_c == null) {
            fecha_c = fecha_i;
            f =false;
        }
        if (agregarAlquiler(DNI, numC, fecha_i,fecha_c)){
            writableDatabase = getWritableDatabase();
            Cursor fila = writableDatabase.rawQuery("select max(" + TAlquiler.ID + ") from " + TAlquiler.T_NOMBRE + ";", null);
            if (fila.moveToFirst()) {
                long idMax = fila.getInt(0);
                if (agregarMensualidad(costo, fecha_i, idMax)) {
                    if (f) {
                        fila.close();
                        writableDatabase = getWritableDatabase();
                        fila = writableDatabase.rawQuery("select max(" + Mensualidad.ID + ") from " + Mensualidad.T_NOMBRE + ";", null);
                        if (fila.moveToFirst()){
                            idMax = fila.getInt(0);
                            agregarPago(fecha_c, idMax);
                        }
                    }
                }
            }
            fila.close();
            writableDatabase.close();
        }
    }
    public void agregarNuevoInquilino(int DNI, String nombres, String apellidoPat, String apellidoMat, String uri, String numC, double costo, @NonNull String fecha_i, @Nullable String fecha_c) {
        SQLiteDatabase writableDatabase;
        boolean f = true;
        if (fecha_c == null) {
            fecha_c = fecha_i;
            f =false;
        }
        if (agregarInquilino(DNI, nombres, apellidoPat, apellidoMat, uri)){
            if (agregarAlquiler(DNI, numC, fecha_i,fecha_c)){
                writableDatabase = getWritableDatabase();
                Cursor fila = writableDatabase.rawQuery("select max(" + TAlquiler.ID + ") from " + TAlquiler.T_NOMBRE + ";", null);
                if (fila.moveToFirst()) {
                    long idMax = fila.getInt(0);
                    if (agregarMensualidad(costo, fecha_i, idMax)) {
                        if (f) {
                            fila.close();
                            writableDatabase = getWritableDatabase();
                            fila = writableDatabase.rawQuery("select max(" + Mensualidad.ID + ") from " + Mensualidad.T_NOMBRE + ";", null);
                            if (fila.moveToFirst()){
                                idMax = fila.getInt(0);
                                agregarPago(fecha_c, idMax);
                            }
                        }
                    }
                }
                fila.close();
                writableDatabase.close();
            }
        }
    }
    public String[] consultarNumerosDeCuartoDisponibles(){
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select "+TCuarto.NUMERO +" from "+TCuarto.T_NOMBRE +" where "+TCuarto.NUMERO+" not in (select "+TAlquiler.NUMERO_C+" from "+TAlquiler.T_NOMBRE+" where "+TAlquiler.VAL+" = 1);", null);
        String s[] = new String[fila.getCount()];
        if (fila.moveToFirst()) {
            for (int i = 0; i<s.length; i++, fila.moveToNext()){
                s[i] = fila.getString(0);
            }
        }
        fila.close();
        return s;
    }

    public boolean existIntoCuarto(String valor){
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select "+ TCuarto.NUMERO+" from "+ TCuarto.T_NOMBRE+" where "+ TCuarto.NUMERO+" = '" +valor+"';",null);
        return fila.moveToFirst();
    }
    public boolean existeUsuario(String dni){
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select *  from "+ TUsuario.T_NOMBRE+
                " where "+ TUsuario.DNI+" = '" +dni+"';",null);
        return fila.moveToFirst();
    }
    public boolean esUsuarioAntiguo(String dni){
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select *  from "+ TAlquiler.T_NOMBRE+
                " where "+ TAlquiler.DNI+" = '" +dni+"' and "+ TAlquiler.VAL + " = 0;",null);
        return fila.moveToFirst();
    }
    public boolean esUsuarioInterno(String dni){
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select *  from "+ TAlquiler.T_NOMBRE+
                " where "+ TAlquiler.DNI+" = '" +dni +"' and "+ TAlquiler.VAL + " = 1;",null);
        return fila.moveToFirst();
    }
    private ContentValues cursorToCV(Cursor cursor){
        ContentValues cv = new ContentValues();
        for (int i = 0; i<cursor.getColumnCount(); i++){
            cv.put(cursor.getColumnName(i),cursor.getString(i));
        }
        return cv;
    }
    public void startScrips(){
        agregarCuarto("1","detalle1","150.00", "");
        agregarCuarto("2","detalle2","180.00", "");
        agregarCuarto("3","detalle3","130.00", "");
        agregarCuarto("4","detalle4","110.00", "");
        agregarCuarto("5","detalle5","170.00", "");
        agregarInquilino(1,"Juan1", "Perez1", "Garcia1", "");
        agregarInquilino(2,"Juan2", "Perez2", "Garcia2", "");
        agregarInquilino(3,"Juan3", "Perez3", "Garcia3", "");
        agregarInquilino(4,"Juan4", "Perez4", "Garcia4", "");
        agregarInquilino(5,"Juan5", "Perez5", "Garcia5", "");
        agregarAlquiler(1,"1","24/08/2019","24/08/2020");
        agregarAlquiler(2,"2","24/08/2019","24/08/2020");
        agregarAlquiler(3,"3","24/08/2019","24/08/2020");
        agregarAlquiler(4,"4","24/08/2019","24/08/2020");
        agregarAlquiler(5,"5","24/08/2019","24/08/2020");
        agregarMensualidad(60,"24/08/2019",1);
        agregarMensualidad(70,"22/12/2019",1);
        agregarMensualidad(90,"04/03/2020",1);
        {
            agregarPago("24/08/2019", 1);
            agregarPago("24/09/2019", 1);
            agregarPago("24/10/2019", 1);
            agregarPago("24/11/2019", 1);
            agregarPago("24/12/2019", 2);
            agregarPago("24/01/2020", 2);
            agregarPago("24/02/2020", 2);
            agregarPago("24/03/2020", 3);
            agregarPago("24/04/2020", 3);
            agregarPago("24/05/2020", 3);
            agregarPago("24/06/2020", 3);
            agregarPago("24/07/2020", 3);
        }

        agregarMensualidad(60,"24/08/2019",2);
        agregarMensualidad(70,"25/04/2020",2);
        agregarMensualidad(80,"04/07/2020",2);
        {
            agregarPago("24/08/2019", 4);
            agregarPago("24/09/2019", 4);
            agregarPago("24/10/2019", 4);
            agregarPago("24/11/2019", 4);
            agregarPago("24/12/2019", 4);
            agregarPago("24/01/2020", 4);
            agregarPago("24/02/2020", 4);
            agregarPago("24/03/2020", 4);
            agregarPago("24/04/2020", 4);
            agregarPago("24/05/2020", 5);
            agregarPago("24/06/2020", 5);
            agregarPago("24/07/2020", 6);
        }
        agregarMensualidad(50,"24/08/2019",3);
        agregarMensualidad(60,"14/11/2019",3);
        agregarMensualidad(80,"27/05/2020",3);

        agregarPago("24/08/2019",7);
        agregarPago("24/09/2019",7);
        agregarPago("24/10/2019",7);
        agregarPago("24/11/2019",8);
        agregarPago("24/12/2019",8);
        agregarPago("24/01/2020",8);
        agregarPago("24/02/2020",8);
        agregarPago("24/03/2020",8);
        agregarPago("24/04/2020",8);
        agregarPago("24/05/2020",8);
        agregarPago("24/06/2020",9);
        agregarPago("24/07/2020",9);

        agregarMensualidad(50,"24/08/2019",4);
        agregarMensualidad(60,"14/09/2019",4);
        agregarMensualidad(80,"27/05/2020",4);

        agregarPago("24/08/2019",10);
        agregarPago("24/09/2019",11);
        agregarPago("24/10/2019",11);
        agregarPago("24/11/2019",11);
        agregarPago("24/12/2019",11);
        agregarPago("24/01/2020",11);
        agregarPago("24/02/2020",11);
        agregarPago("24/03/2020",11);
        agregarPago("24/04/2020",11);
        agregarPago("24/05/2020",11);
        agregarPago("24/06/2020",12);
        agregarPago("24/07/2020",12);
        agregarMensualidad(50,"24/08/2019",5);
        agregarMensualidad(60,"14/12/2019",5);
        agregarMensualidad(80,"27/03/2020",5);

        agregarPago("24/08/2019",13);
        agregarPago("24/09/2019",13);
        agregarPago("24/10/2019",13);
        agregarPago("24/11/2019",13);
        agregarPago("24/12/2019",14);
        agregarPago("24/01/2020",14);
        agregarPago("24/02/2020",14);
        agregarPago("24/03/2020",14);
        agregarPago("24/04/2020",15);
        agregarPago("24/05/2020",15);
        agregarPago("24/06/2020",15);
        agregarPago("24/07/2020",15);
    }

}
