package com.example.ejercicio13;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio13.configuraciones.SQLiteConexion;
import com.example.ejercicio13.configuraciones.Transacciones;
import com.example.ejercicio13.tablas.Personas;

public class ActivityConfirmarEliminar extends AppCompatActivity {
    EditText txtid,nombre,apellido,correo,edad,direccion;
    Button eliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_eliminar);

        txtid = (EditText) findViewById(R.id.id);
        nombre= (EditText) findViewById(R.id.nombre);
        apellido = (EditText) findViewById(R.id.apellidos);
        edad = (EditText) findViewById(R.id.edad);
        correo = (EditText) findViewById(R.id.correo);
        direccion = (EditText) findViewById(R.id.direccion);

        eliminar = (Button) findViewById(R.id.eli);


        Bundle obj = getIntent().getExtras();

        Personas pers=null;

        if(obj!=null){
            pers = (Personas) obj.getSerializable("persona");

            txtid.setText(pers.getId().toString());
            nombre.setText(pers.getNombres().toString());
            apellido.setText(pers.getApellidos().toString());
            edad.setText(pers.getEdad().toString());
            correo.setText(pers.getCorreo().toString());
            direccion.setText(pers.getDireccion().toString());
        }

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityConfirmarEliminar.this);
                builder.setMessage("Esta seguro(a) de borrar esta persona?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                eliminarPersona();
                                volver();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    public void eliminarPersona(){
        boolean correc = false;
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null,1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        long resultado = db.delete(Transacciones.tablapersonas,Transacciones.id+"=?", new String[]{txtid.getText().toString()});
        db.close();
    }

    private void volver(){
        Intent intent = new Intent(this, ActivityEliminar.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);
    }




}