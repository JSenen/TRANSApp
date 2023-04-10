package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.transapp.R;
import com.example.transapp.adapter.SeeLinesAdapter;
import com.example.transapp.contract.SeeLinesContract;
import com.example.transapp.domain.Line;
import com.example.transapp.presenter.SeeLinesPresenter;

import java.util.ArrayList;
import java.util.List;

public class SeeLinesActivityView extends AppCompatActivity implements SeeLinesContract.View {
    private List<Line> linesList;
    private SeeLinesAdapter seeLinesAdapter;
    private SeeLinesPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_lines_view);

        presenter = new SeeLinesPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        linesList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rcview_seeLinesview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        seeLinesAdapter = new SeeLinesAdapter(this, linesList);
        recyclerView.setAdapter(seeLinesAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadAllLines();
    }
    /** Menu barra de tareas */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskbarmenu_admin_lines,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Regresa a la pantalla anterior
        Intent intent = new Intent(this, MainActivityView.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void showLines(List<Line> lines) {
        linesList.clear();
        linesList.addAll(lines);
        seeLinesAdapter.notifyDataSetChanged();


    }
}