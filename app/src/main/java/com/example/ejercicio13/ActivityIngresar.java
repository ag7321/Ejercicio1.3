package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio13.configuraciones.SQLiteConexion;
import com.example.ejercicio13.configuraciones.Transacciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityIngresar extends AppCompatActivity {
    EditText nombres, apellidos, edad, correo,direccion;
    Button btnSalvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        nombres = (EditText) findViewById(R.id.txtNombres);
        apellidos = (EditText) findViewById(R.id.txtApellidos);
        edad = (EditText) findViewById(R.id.txtEdad);
        correo = (EditText) findViewById(R.id.txtCorreo);
        direccion = (EditText) findViewById(R.id.txtDireccion);
        btnSalvar = (Button) findViewById(R.id.btnGuardar);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarVacio() == true){
                    Toast.makeText(getApplicationContext(),"Los campos no deben estar vacios", Toast.LENGTH_LONG).show();
                }else{ AgregarPersonas();}


            }
        });
    }

    private void AgregarPersonas() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null,1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres,nombres.getText().toString());
        valores.put(Transacciones.apellidos,apellidos.getText().toString());

        valores.put(Transacciones.edad,edad.getText().toString());
        valores.put(Transacciones.correo,correo.getText().toString());
        valores.put(Transacciones.direccion,direccion.getText().toString());

        Long resultado = db.insert(Transacciones.tablapersonas,Transacciones.id,valores);

        Toast.makeText(getApplicationContext(),"Registro ingresado con exito!! Codigo"+resultado.toString(), Toast.LENGTH_LONG).show();

        db.close();

        LimpiarPantalla();
    }

    private void LimpiarPantalla() {
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");
    }

    private boolean validarVacio(){
        boolean va=false;
        if(nombres.getText().toString().isEmpty()||apellidos.getText().toString().isEmpty()||edad.getText().toString().isEmpty()||correo.getText().toString().isEmpty()
           ||direccion.getText().toString().isEmpty()){
            va = true;
        }
        return va;
    }

}
