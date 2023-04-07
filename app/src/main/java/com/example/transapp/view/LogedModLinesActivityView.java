package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.hardware.lights.LightState;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.transapp.R;
import com.example.transapp.adapter.ModLinesAdapter;
import com.example.transapp.adapter.SeeLinesAdapter;
import com.example.transapp.contract.LogedModLinesContract;
import com.example.transapp.domain.Line;
import com.example.transapp.presenter.LogedModLinesPresenter;

import java.util.ArrayList;
import java.util.List;

public class LogedModLinesActivityView extends AppCompatActivity implements LogedModLinesContract.View {

    private LogedModLinesPresenter presenter;
    private List<Line> linesList;
    private ModLinesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loged_mod_lines_view);

        //Pasamos vista al presenter
        presenter = new LogedModLinesPresenter(this);

        //Iniciamos RecyclerView
        initializeRecyclerView();

    }

    private void initializeRecyclerView() {

        linesList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rcview_modlines);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ModLinesAdapter(this, linesList);
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
    public void showLines(List<Line> lines) {
        linesList.clear();
        linesList.addAll(lines);
        //Notificamos al adapter
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //Cargar cuando la pantalla se regresa a ella
        presenter.loadAllLines();
    }
}