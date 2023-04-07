package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.transapp.R;

public class LogedMainActivityView extends AppCompatActivity {

    private Button logedbut_ver_lineas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loged_main_view);

        //Recuperar elementos
        logedbut_ver_lineas = findViewById(R.id.logedbut_ver_lineas);


    }
    /** Boton ir a listado modificar lineas */
    public void logedModLines(View view){
        Intent intent = new Intent(this,LogedModLinesActivityView.class);
        startActivity(intent);
    }

    /** Menu barra de tareas  */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Regresa a la pantalla anterior
        Intent intent = new Intent(this, MainActivityView.class);
        startActivity(intent);
        return true;
    }
}