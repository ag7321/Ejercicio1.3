package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ejercicio13.configuraciones.SQLiteConexion;
import com.example.ejercicio13.configuraciones.Transacciones;
import com.example.ejercicio13.tablas.Personas;

import java.util.ArrayList;

public class ActivityEditar extends AppCompatActivity {
    SQLiteConexion conexion;
    Spinner sppersonas;
    EditText id,nombres,apellidos,edad,correo,direccion;
    Button btnEditar;

    ArrayList<String> lista_personas;
    ArrayList<Personas> lista;
    ArrayAdapter<CharSequence> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);

        sppersonas = (Spinner) findViewById(R.id.spPersonas);
        id = (EditText) findViewById(R.id.txtIdd);
        nombres = (EditText) findViewById(R.id.txtNombres);
        apellidos = (EditText) findViewById(R.id.txtApellidos);
        edad = (EditText) findViewById(R.id.txtEdad);
        correo = (EditText) findViewById(R.id.txtCorreo);
        direccion = (EditText) findViewById(R.id.txtDireccion);
        btnEditar = (Button) findViewById(R.id.btnGuardar);

        obtenerListaPersonas();

        adp = new ArrayAdapter(this,android.R.layout.simple_spinner_item,lista_personas);
        sppersonas.setAdapter(adp);


        sppersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id.setText(lista.get(i).getId().toString());
                nombres.setText(lista.get(i).getNombres());
                apellidos.setText(lista.get(i).getApellidos());
                edad.setText(lista.get(i).getEdad().toString());

                correo.setText(lista.get(i).getCorreo());
                direccion.setText(lista.get(i).getDireccion());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditarPersonas();
            }
        });


    }



    public void obtenerListaPersonas(){
        Personas personas = null;
        lista = new ArrayList<Personas>();
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Transacciones.tablapersonas,null);

        while(cursor.moveToNext()){
            personas = new Personas();

            personas.setId(cursor.getInt(0));
            personas.setNombres(cursor.getString(1));
            personas.setApellidos(cursor.getString(2));
            personas.setEdad(cursor.getInt(3));
            personas.setCorreo(cursor.getString(4));
            personas.setDireccion(cursor.getString(5));

            lista.add(personas);
        }
        cursor.close();

        fillCombo();
    }

    private void fillCombo(){
        lista_personas = new ArrayList<String>();

        for (int i = 0; i < lista.size(); i++) {
            lista_personas.add(lista.get(i).getId()+" | "+
                    lista.get(i).getNombres()+" | "+
                    lista.get(i).getApellidos());
        }

    }

    private void EditarPersonas() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null,1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(Transacciones.id, id.getText().toString());
        valores.put(Transacciones.nombres,nombres.getText().toString());
        valores.put(Transacciones.apellidos,apellidos.getText().toString());

        valores.put(Transacciones.edad,edad.getText().toString());
        valores.put(Transacciones.correo,correo.getText().toString());
        valores.put(Transacciones.direccion,direccion.getText().toString());

        Long resultado = db.replace(Transacciones.tablapersonas,Transacciones.id,valores);

        Toast.makeText(getApplicationContext(),"Registro Editado con exito!! Codigo"+resultado.toString(), Toast.LENGTH_LONG).show();

        db.close();




    }

}


