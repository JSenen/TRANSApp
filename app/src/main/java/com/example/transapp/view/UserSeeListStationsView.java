package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.transapp.R;
import com.example.transapp.adapter.SeeStationsAdapter;
import com.example.transapp.adapter.UserSeeStationsAdapter;
import com.example.transapp.contract.UserSeeListStationsContract;
import com.example.transapp.contract.UserSeeStationsContract;
import com.example.transapp.domain.Stations;
import com.example.transapp.presenter.SeeStationsPresenter;
import com.example.transapp.presenter.UserSeeListStationsPresenter;
import com.example.transapp.presenter.UserSeeStationsPresenter;

import java.util.ArrayList;
import java.util.List;

public class UserSeeListStationsView extends AppCompatActivity implements UserSeeListStationsContract.View {

    private long idLine;
    private List<Stations> stations;
    private UserSeeListStationsPresenter presenter;
    private UserSeeStationsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_see_list_stations_view);

        //Recuperamos el id de la Linea
        Intent intent = getIntent();
        idLine = intent.getLongExtra("idLine",0);
        if (idLine == 0){
            return;
        }

        presenter = new UserSeeListStationsPresenter(idLine,this,this);


        initializeRecyclerView();


    }

    private void initializeRecyclerView() {
        stations = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rcview_user_liststations);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserSeeStationsAdapter(this, stations);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void showAllStations(List<Stations> stationsList) {
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
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        showAllStations(stations);
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
        Intent intent = new Intent(this, SeeLinesActivityView.class);
        startActivity(intent);
        return true;
    }
    /** Nos permite recibir los datos como OK, para poder relanzar
     * el adapter y asi usar 1 activity para 2 */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            //Obtener los datos del intent
            String idStation = data.getStringExtra("RESULT_DATA");

        }
    }

}