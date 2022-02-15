package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ejercicio13.configuraciones.SQLiteConexion;
import com.example.ejercicio13.configuraciones.Transacciones;
import com.example.ejercicio13.tablas.Personas;

import java.util.ArrayList;

public class ActivityEliminar extends AppCompatActivity {
    SQLiteConexion conexion;
    ListView lista;
    ArrayList<Personas> listaPersonas;
    ArrayList<String> arregloPersonas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        conexion= new SQLiteConexion(this, Transacciones.NameDatabase,null,1);

        lista=(ListView) findViewById(R.id.lis);

        ObtenerListaPersona();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arregloPersonas);
        lista.setAdapter(adp);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Personas per = listaPersonas.get(i);

                Intent intent = new Intent(ActivityEliminar.this, ActivityConfirmarEliminar.class);

                Bundle bundle=new Bundle();//Bundle

                bundle.putSerializable("persona",per);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void ObtenerListaPersona(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas list_personas = null;
        listaPersonas = new ArrayList<Personas>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Transacciones.tablapersonas,null);

        while(cursor.moveToNext()){
            list_personas = new Personas();
            list_personas.setId(cursor.getInt(0));
            list_personas.setNombres(cursor.getString(1));
            list_personas.setApellidos(cursor.getString(2));
            list_personas.setEdad(cursor.getInt(3));
            list_personas.setCorreo(cursor.getString(4));
            list_personas.setDireccion(cursor.getString(5));

            listaPersonas.add(list_personas);
        }

        cursor.close();;

        llenalista();

    }

    private void llenalista(){
        arregloPersonas = new ArrayList<String>();
        for (int i=0;i<listaPersonas.size();i++){
            arregloPersonas.add(listaPersonas.get(i).getId()+" | "
                    +listaPersonas.get(i).getNombres()+" | "+
                    listaPersonas.get(i).getApellidos());
        }
    }
}