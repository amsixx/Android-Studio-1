package com.example.pruebabd;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.database.Cursor;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity  {

    ListView listView;
    SQLiteDatabase db;
    ArrayList<String> lista1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lvLista);
        final BDProvincias bd = new BDProvincias(this,"BDProvincias1",null,3);
        db = bd.getWritableDatabase();

        Cursor c = bd.getResultados();
        if (c.getCount() == 0){
            Toast.makeText(this,"No hay datos en la base de datos",Toast.LENGTH_LONG).show();
        }else{
            while (c.moveToNext()){
                lista1.add(c.getString(1) + "-" +  c.getString(2));
                 final ArrayAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lista1);
                  listView.setAdapter(adapter);
                  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                      @Override
                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item = (String)parent.getAdapter().getItem(position);

                        //A través de substrings sacar el campo Provincia y Poblacion del item clickado
                        int beforeGuion = item.indexOf("-");
                        String provincia = item.substring(0,beforeGuion);
                        String poblacion = item.substring(beforeGuion + 1);
                        int pb = Integer.parseInt(poblacion);
                        pb+=1;
                        System.out.println("Provincia " + provincia + " Poblacion " + pb);

                        //Actualizar la base de datos
                        bd.actualizar(provincia,pb);

                        //Refrescar
                        adapter.notifyDataSetChanged();
                      }
                  });
            }
        }


    }
}
