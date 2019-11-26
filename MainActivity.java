package com.example.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    ListView listView;
    BDProvincias db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        db = new BDProvincias(this,"BDProvincias1",null,1);

        ArrayList<String> array = new ArrayList<>();
        Cursor c=db.getResultados();

        if (c.getCount() == 0){
            Toast.makeText(this,"NO SE HAN OBTENIDO DATOS",Toast.LENGTH_LONG).show();
        }else{
            while (c.moveToNext()){
                array.add(c.getString(1));
                array.add(c.getString(2));
                ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,array);
                listView.setAdapter(adapter);
            }
        }
    }


}
