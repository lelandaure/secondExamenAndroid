package com.example.dam1_cl2_stephanyrojasalejandro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "PACIENTES.DB";
    public static final String TABLE_NAME = "PACIENTE";
    public static final String KEY_ID = "id";
    public static final String KEY_DNI = "dni";
    public static final String KEY_NOMBRES = "nombres";
    public static final String KEY_MOTIVO = "motivo";
    public static final String KEY_DOCTOR = "doctor";
    public static final String KEY_COSTO = "costo";
    public static final String KEY_FECHA = "fecha";

    public  DbHandler(Context context){
        super(context, DB_NAME, null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                + KEY_DNI + " TEXT ,"
                                + KEY_NOMBRES + " TEXT ,"
                                + KEY_MOTIVO + " TEXT , "
                                + KEY_DOCTOR + " TEXT , "
                                + KEY_COSTO + " REAL , "
                                + KEY_FECHA + " TEXT " + ");";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //METODO REGISTRAR
    void insertarPaciente(String dni, String nombres, String motivo, String doctor, Double costo, String fecha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHandler.KEY_DNI, dni);
        contentValues.put(DbHandler.KEY_NOMBRES, nombres);
        contentValues.put(DbHandler.KEY_MOTIVO, motivo);
        contentValues.put(DbHandler.KEY_DOCTOR, doctor);
        contentValues.put(DbHandler.KEY_COSTO, costo);
        contentValues.put(DbHandler.KEY_FECHA, fecha);
        long newRowId = db.insert(DbHandler.TABLE_NAME, null, contentValues);
        db.close();
    }
    //METODO OBTENER
    public ArrayList<HashMap<String,String>> obtenerPaciente(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String,String>>listaPacientes = new ArrayList<>();
        String query = "SELECT dni, nombres, fecha FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            HashMap<String,String> paciente = new HashMap<>();
            paciente.put("dni", cursor.getString(cursor.getColumnIndex(KEY_DNI)));
            paciente.put("nombres", cursor.getString(cursor.getColumnIndex(KEY_NOMBRES)));
            paciente.put("fecha", cursor.getString(cursor.getColumnIndex(KEY_FECHA)));
            listaPacientes.add(paciente);
        }
        return listaPacientes;
    }
    //METODO OBTENER PACIENTE POR FILTRO
    public ArrayList<HashMap<String,String>> obtenerPacientePorNombre(String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String,String>> listaPacientes = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ KEY_DNI, KEY_NOMBRES, KEY_FECHA},
                KEY_NOMBRES+" LIKE ?", new String[]{"%" + nombre + "%"},null,
                null, null,null);
        if (cursor.moveToNext()){
            HashMap<String,String> paciente = new HashMap<>();
            paciente.put("dni",cursor.getString(cursor.getColumnIndex(KEY_DNI)));
            paciente.put("nombres",cursor.getString(cursor.getColumnIndex(KEY_NOMBRES)));
            paciente.put("fecha",cursor.getString(cursor.getColumnIndex(KEY_FECHA)));
            listaPacientes.add(paciente);
        }
        return listaPacientes;
    }

}
