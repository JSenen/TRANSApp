package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.transapp.R;
import com.example.transapp.contract.EditLineContract;
import com.example.transapp.domain.Line;
import com.example.transapp.presenter.EditLinePresenter;

public class EditLineView extends AppCompatActivity implements EditLineContract.View {

    private Line linebody;
    private EditLinePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_line_view);
    }

    /**
     * Menu barra de tareas Lineas Edit
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.taskbar_menu_back) {
            //Regresa a la pantalla anterior y modificar Recycler

            Intent intent = new Intent(this, LogedModLinesActivityView.class);
            intent.putExtra("RESULT_DATA", linebody.getId());
            setResult(RESULT_OK, intent);
            finish();

            return true;


        }

        return false;
    }
}