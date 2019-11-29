package com.example.tarea2_2_ramos_morales_ismael;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class BDAdaptador {
    private Context contexto;
    private BDPaises bdPaises;
    private SQLiteDatabase bd;


    public BDAdaptador(Context c) {
        this.contexto = c;
        bdPaises=new BDPaises(c);
    }

    //Método de inserción. Indicar el tipo de datos devuelto
    public boolean insertar(String nombre,String capital,String continente,int habitantes){
        bd=bdPaises.getWritableDatabase();
        boolean insertado;
        ContentValues values = new ContentValues();
        values.put("Nombre",nombre);
        values.put("Capital",capital);
        values.put("Continente",continente);
        values.put("Habitantes",habitantes);
        long insercion = bd.insert("Paises",null,values);
        if(insercion != -1){
            Toast.makeText(contexto,"La inserción ha sido correcta",Toast.LENGTH_LONG).show();
            insertado = true;
        }else{
            Toast.makeText(contexto,"Error en la inserción",Toast.LENGTH_LONG).show();
            insertado = false;
        }
        bd.close();
        return insertado;
    }

    //Método de consulta. Indicar el tipo de datos devuelto
    public Cursor consultar(){
        Cursor c;
        bd=bdPaises.getReadableDatabase();
        String [] columnas = {"Nombre","Capital","Continente","Habitantes"};
        c = bd.query("Paises",columnas,null,null,null,null,columnas[0]);
        return c;
    }

    public void eliminar(){
        bd=bdPaises.getWritableDatabase();
        //Insertar el código de eliminación aquí



        //Fin código de consulta
        bd.close();


    }
}