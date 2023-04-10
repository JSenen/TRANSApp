package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.transapp.R;
import com.example.transapp.adapter.SeeStationsAdapter;
import com.example.transapp.contract.SeeStationsContract;
import com.example.transapp.domain.Stations;
import com.example.transapp.presenter.SeeStationsPresenter;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.Style;


import java.util.ArrayList;
import java.util.List;


public class SeeStationesActivityView extends AppCompatActivity implements SeeStationsContract.View {

    private List<Stations> stations;
    private SeeStationsAdapter adapter;
    private SeeStationsPresenter presenter;
    private String token;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_stationes_view);

        //Recuperamos la id de la linea. Asignamos 0 por defecto si no hay id
        Intent intent = getIntent();
        long idLinea = intent.getLongExtra("idLine",0);

        if (idLinea == 0){
            return;
        }


        /** recuperamos Token para usarlo en delete*/
        SharedPreferences preferences = getSharedPreferences("MyPref",MODE_PRIVATE);
        token = preferences.getString("token","");
        Log.i("TOKEN","Token enviado desde SeeStationsView "+token);
        presenter = new SeeStationsPresenter(this,idLinea,token,this);

        initializeRecyclerView();


    }

    private void initializeRecyclerView() {
        stations = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rcview_seestations);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SeeStationsAdapter(this, stations, token); //Pasamos tambien token de SharedPreferences
        recyclerView.setAdapter(adapter);
    }

    /** Menu barra de tareas */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Regresa a la pantalla anterior
        Intent intent = new Intent(this, LogedModLinesActivityView.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void showStations(List<Stations> stationsList) {
        stations.clear();
        stations.addAll(stationsList);
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Evitamos cuelgue de la app
        if (presenter != null) {
            presenter.loadAllStations();
        }

    }


}
