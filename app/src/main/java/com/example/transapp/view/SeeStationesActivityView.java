package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.transapp.R;
import com.example.transapp.adapter.SeeStationsAdapter;
import com.example.transapp.contract.SeeStationsContract;
import com.example.transapp.domain.Stations;
import com.example.transapp.presenter.SeeStationsPresenter;

import java.util.ArrayList;
import java.util.List;

public class SeeStationesActivityView extends AppCompatActivity implements SeeStationsContract.View {

    private List<Stations> stations;
    private SeeStationsAdapter adapter;
    private SeeStationsPresenter presenter;
    private long idLinea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_stationes_view);

        //Recuperamos la id de la linea. Asignamos 0 por defecto si no hay id
        Intent intent = getIntent();
        idLinea = intent.getLongExtra("idLine",0);
        if (idLinea == 0)
            return;

        SharedPreferences preferences = getSharedPreferences("preferences",MODE_PRIVATE);
        String token = preferences.getString("token","");
        presenter = new SeeStationsPresenter(this,idLinea,token,getApplicationContext());

        initializeRecyclerView();


    }

    private void initializeRecyclerView() {
        stations = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rcview_seestations);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SeeStationsAdapter(this, stations);
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
        Intent intent = new Intent(this, LogedMainActivityView.class);
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
        presenter.loadAllStations();
    }


}