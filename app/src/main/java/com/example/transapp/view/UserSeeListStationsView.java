package com.example.transapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
    }

}