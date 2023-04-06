package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.transapp.R;
import com.example.transapp.contract.SeeLinesContract;
import com.example.transapp.domain.Lines;
import com.example.transapp.presenter.SeeLinesPresenter;

import java.util.List;

public class SeeLinesActivityView extends AppCompatActivity implements SeeLinesContract.View {
    private List<Lines> lines;
    //private LinesAdapter linesAdapter;
    private SeeLinesPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_lines_view);
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
        Intent intent = new Intent(this, MainActivityView.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void showLines(List<Lines> lines) {

    }
}