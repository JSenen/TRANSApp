package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.lights.LightState;
import android.os.Bundle;
import android.util.Log;
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
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loged_mod_lines_view);

        /** recuperamos Token para usarlo en delete*/
        SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        token = preferences.getString("token", "");
        Log.i("TOKEN", "Token enviado desde LogedModLinesActivityView " + token);
        //Pasamos vista al presenter
        presenter = new LogedModLinesPresenter(this);

        //texto en action bar
        getSupportActionBar().setTitle("Zona Administradores");

        //Iniciamos RecyclerView
        initializeRecyclerView();

    }

    private void initializeRecyclerView() {

        linesList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rcview_modlines);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ModLinesAdapter(this, linesList, token);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Menu barra de tareas
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskbar_admin_addline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.taskbar_admin_lines_back){
            //Regresa a la pantalla anterior
            Intent intent = new Intent(this, LogedMainActivityView.class);
            startActivity(intent);
            return true;
        }else if(item.getItemId() == R.id.taskbar_admin_lines_addone){
            //Va a pantalla añadir linea
            Intent intent = new Intent(this, AddLinesView.class);
            startActivity(intent);
            return true;
        }
        return false;
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

    @Override
    protected void onResume() {
        super.onResume();
        //Evitamos bloqueo de la app
        if (presenter != null) {
            presenter.loadAllLines();
        }

    }
}