package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ejercicio13.configuraciones.SQLiteConexion;
import com.example.ejercicio13.tablas.Personas;

import java.util.ArrayList;

public class ActivityInicio extends AppCompatActivity {
    Button agregar,editar,eliminar,seleccionar;
    SQLiteConexion conexion;
    ListView lista;
    ArrayList<Personas> listaPersonas;
    ArrayList<String> arregloPersonas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        agregar = (Button) findViewById(R.id.btnAgregar);
        editar = (Button) findViewById(R.id.btnEditar);
        eliminar = (Button) findViewById(R.id.btnEliminar);
        seleccionar = (Button) findViewById(R.id.btnSeleccionar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityIngresar.class);
                startActivity(intent);
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityEditar.class);
                startActivity(intent);
            }
        });
        seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivitySeleccionar.class);
                startActivity(intent);
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityEliminar.class);
                startActivity(intent);
            }
        });
    }



}