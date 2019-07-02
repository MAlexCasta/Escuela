package com.example.escuela;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.escuela.database.myDbAdapter;

public class Nueva_Materia extends AppCompatActivity {

    Spinner spinner;
    String idUsuario;
    myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva__materia);

        idUsuario=getIntent().getStringExtra("idUsuario");
        Log.d("Nuevo Usuario id",idUsuario);
        helper=Admin.helper;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent admin=new Intent(Nueva_Materia.this,Materias.class);
        admin.putExtra("LoginId",idUsuario);
        Nueva_Materia.this.startActivity(admin);
    }

    public void newMateria(View view)
    {
        AutoCompleteTextView txtnombre,txtdias,txtHoraEntrada,txtHoraSalida;
        String nombre,dias,horaEntrada,horaSalida;

        txtnombre=(AutoCompleteTextView)findViewById(R.id.txtNombreR);

        txtdias=(AutoCompleteTextView)findViewById(R.id.txtMatriculaR);
        txtHoraEntrada=(AutoCompleteTextView)findViewById(R.id.txtHI);
        txtHoraSalida=(AutoCompleteTextView)findViewById(R.id.txtHS);


        nombre=txtnombre.getText().toString();
        dias=txtdias.getText().toString();
        horaEntrada=txtHoraEntrada.getText().toString();
        horaSalida=txtHoraSalida.getText().toString();


        if(nombre.equals("")||dias.equals("")||horaEntrada.equals("")||horaSalida.equals(""))
        {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_SHORT);
            toast1.show();
        }
        else
        {

            helper.insertMateria(nombre,horaEntrada,horaSalida,dias,idUsuario);
            Toast toast1 = Toast.makeText(getApplicationContext(), "Materia Agregada", Toast.LENGTH_SHORT);
            toast1.show();

            Intent admin=new Intent(Nueva_Materia.this,Materias.class);
            admin.putExtra("LoginId",idUsuario);
            Nueva_Materia.this.startActivity(admin);
            finish();
        }



    }
}
