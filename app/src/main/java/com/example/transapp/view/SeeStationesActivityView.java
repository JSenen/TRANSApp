package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private long idLinea;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_stationes_view);

        //Recuperamos la id de la linea. Asignamos 0 por defecto si no hay id
        Intent intent = getIntent();
        idLinea = intent.getLongExtra("idLine",0);

        if (idLinea == 0){
            return;
        }

        //texto en action bar
        getSupportActionBar().setTitle(R.string.Zona_Admin);


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
        getMenuInflater().inflate(R.menu.taskbarmenu_admin_stations,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.taskbar_stations_itemBack){
            //Regresa a la pantalla anterior
            Intent intent = new Intent(this, LogedModLinesActivityView.class);
            startActivity(intent);
            return true;
        }else if(item.getItemId() == R.id.taskbar_stations_itemAdd){
            //Va a pantalla añadir
            Intent intent = new Intent(this, AddStationView.class);
            //Pasamos el id de la Linea
            intent.putExtra("idLinea",idLinea);
            startActivity(intent);
            return true;
        }

        return false;
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
        //Evitamos bloqueo de la app
        if (presenter != null) {
            presenter.loadAllStations();
        }

    }
    /** Actualiamos adapter al regresar de MapStationView */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == idLinea && resultCode == RESULT_OK) {
            // procesa los datos recibidos de la actividad secundaria
            Bundle extras = data.getExtras();
            if (extras != null) {
                Object resultData = extras.get("RESULT_DATA");
                // actualiza los datos en el Adapter
                presenter.loadAllStations();
            }
        }
    }

}
