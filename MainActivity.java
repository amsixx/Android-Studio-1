package com.example.tarea2_2_ramos_morales_ismael;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BDAdaptador bd = new BDAdaptador(this);
    ArrayList<String> array =  new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Cursor c = bd.consultar();
        if(c.getCount() == 0){
            Toast.makeText(this,"No hay datos en la base de datos",Toast.LENGTH_LONG).show();
        }else{
            while (c.moveToNext()){
                Toast.makeText(this,"El ListView va a recibir datos",Toast.LENGTH_LONG).show();

                array.add(c.getString(0));
                System.out.println(array.get(0));

            }
            ArrayAdapter adapter = new ArrayAdapter <>(this,
                    android.R.layout.simple_list_item_1,array);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_insertar) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);

            final EditText nombre = new EditText(this);
            nombre.setHint("Nombre");
            layout.addView(nombre);

            final EditText capital = new EditText(this);
            capital.setHint("Capital");
            layout.addView(capital);

            final EditText continente = new EditText(this);
            continente.setHint("Continente");
            layout.addView(continente);

            final EditText habitantes = new EditText(this);
            habitantes.setHint("Habitantes");
            habitantes.setInputType(InputType.TYPE_CLASS_NUMBER);
            layout.addView(habitantes);
            builder.setView(layout);
            builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    bd.insertar(nombre.getText().toString(),capital.getText().toString(),
                                continente.getText().toString(),Integer.valueOf(habitantes.getText().toString()));
                }
            });


//Falta insertar los botones al cuadro de diálogo.


            builder.setTitle("Insertar País");
            builder.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
