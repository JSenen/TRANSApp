package com.example.transapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.transapp.R;

public class MainActivityView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Boton ver lineas*/
    public void butSeeLines(View view){
        Intent intent = new Intent(this, SeeLinesActivityView.class);
        startActivity(intent);
    }
    /** Boton ver Trenes*/
    public void butSeeTrains(View view){
        Intent intent = new Intent(this, SeeTrainsActivityView.class);
        startActivity(intent);
    }
    /** Boton ver Estaciones*/
    public void butSeeStations(View view){
        Intent inten = new Intent(this,SeeStationesActivityView.class);
        startActivity(inten);
    }
}