package com.example.evento;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDProvincias extends SQLiteOpenHelper {

    public static final String NOMBRE_TABLA = "DBProvincias1";
    String crearTabla = "CREATE TABLE " + NOMBRE_TABLA + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "Provincia TEXT,POBLACION TEXT)";
    String insert1 = "INSERT INTO " + NOMBRE_TABLA + "(Provincia,Poblacion) VALUES('Caceres',395585)";
    String insert2 = "INSERT INTO " + NOMBRE_TABLA + "(Provincia,Poblacion) VALUES('Badajoz',654754)";
    public BDProvincias(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crearTabla);
        db.execSQL(insert1);
        db.execSQL(insert2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getResultados(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + NOMBRE_TABLA,null);
        return c;
    }
}
