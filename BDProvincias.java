package com.example.pruebabd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import androidx.annotation.Nullable;


public class BDProvincias extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Provincias1";
    String sqlTabla = "CREATE TABLE Provincias1(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Provincia TEXT,Poblacion INTEGER)";
    String sqlInsert1 = "INSERT INTO Provincias1(Provincia,Poblacion)" +
            "VALUES('Caceres',395528)";
    String sqlInsert2 = "INSERT INTO Provincias1(Provincia,Poblacion)" +
            "VALUES('Badajoz',674925)";
    public BDProvincias(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("LOG","Entrada a la creacion de la tabla");
        db.execSQL(sqlTabla);
        db.execSQL(sqlInsert1);
        db.execSQL(sqlInsert2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlInsert1);
        db.execSQL(sqlInsert2);
    }
    public Cursor getResultados(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return datos;
    }
    public void actualizar(String s,int i){
        SQLiteDatabase db = this.getWritableDatabase();
 /*       ContentValues values = new ContentValues();
        System.out.println("ENTRADA AL PROCESO DE ACTUALIZACION Provincia : " + s + " Poblacion: " +i);
        values.put("Provincia",s);
        values.put("Poblacion",i);
        int row =db.update(TABLE_NAME,values,"id=1",null);
        if (row == 0){
            System.out.println("NO SE HA ACTUALIZADO NINGUNA FILA");
        }else{
            System.out.println("SE HA ACTUALIZADO ALGUNA FILA");
        }*/
     Cursor c = db.rawQuery("UPDATE " + TABLE_NAME + " SET Poblacion = " + i + " WHERE Provincia = '" + s + "'",null);
        c.moveToNext();
        c.close();
    }
}
