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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicio13.configuraciones.SQLiteConexion;
import com.example.ejercicio13.configuraciones.Transacciones;
import com.example.ejercicio13.tablas.Personas;

import java.util.ArrayList;

public class ActivityVerSeleccionado extends AppCompatActivity {
    EditText id,nombre,apellido,correo,edad,direccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_seleccionado);

        id = (EditText) findViewById(R.id.txtId);
        nombre= (EditText) findViewById(R.id.txtN);
        apellido = (EditText) findViewById(R.id.txtA);
        edad = (EditText) findViewById(R.id.txtE);
        correo = (EditText) findViewById(R.id.txtC);
        direccion = (EditText) findViewById(R.id.txtD);



        Bundle obj = getIntent().getExtras();

        Personas pers=null;

        if(obj!=null){
            pers = (Personas) obj.getSerializable("persona");

            id.setText(pers.getId().toString());
            nombre.setText(pers.getNombres().toString());
            apellido.setText(pers.getApellidos().toString());
            edad.setText(pers.getEdad().toString());
            correo.setText(pers.getCorreo().toString());
            direccion.setText(pers.getDireccion().toString());
        }

    }




}