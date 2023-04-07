package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.transapp.R;
import com.example.transapp.contract.LogedModLinesContract;
import com.example.transapp.presenter.LogedModLinesPresenter;

public class LogedModLinesActivityView extends AppCompatActivity implements LogedModLinesContract.View {

    private LogedModLinesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loged_mod_lines_view);

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
}