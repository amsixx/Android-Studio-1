package com.example.tarea2_2_ramos_morales_ismael;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class BDPaises extends SQLiteOpenHelper {

    private Context context;
    //Cadena de creación de la tabla. Añadir los campos necesarios
    private String CREAR_TABLA="CREATE TABLE Paises(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                   "Nombre TEXT,Capital TEXT,Continente TEXT,Habitantes INTEGER)";
    //Cadena de eliminación de la tabla. Falta por indicar el nombre de la tabla
    private String ELIMINAR_TABLA="DROP TABLE IF EXISTS Paises";

    public BDPaises(Context context) {
        super(context, "BD", null, 1);
        this.context = context;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        try {
            db.execSQL(CREAR_TABLA);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        try {
            db.execSQL(ELIMINAR_TABLA);
            onCreate(db);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
        }
    }
}
