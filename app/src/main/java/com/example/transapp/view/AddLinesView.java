package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.transapp.R;
import com.example.transapp.contract.AddLineContract;
import com.example.transapp.domain.Line;
import com.example.transapp.presenter.AddLinePresenter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;

public class AddLinesView extends AppCompatActivity implements AddLineContract.View {

    private AddLinePresenter presenter;
    private Line linebody;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lines_view);

        //Recuperar Token
        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        presenter = new AddLinePresenter(this, linebody, token);

        //texto en action bar
        getSupportActionBar().setTitle("Zona Administradores");

        /** Recuperar elementos entrada horas. Al tratarse de material se debe indicar primero el layout */
        TextInputLayout addline_hopen_layout = findViewById(R.id.addline_hopen_layout);
        TextInputEditText edtxt_addline_hopen = findViewById(R.id.edtxt_addline_hopen);
        /** Para dar formato a las horas */
        final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setLenient(false);
        /** Listener en el campo horas */
        addline_hopen_layout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = edtxt_addline_hopen.getText().toString();
                boolean isValid = true;

                try {
                    dateFormat.parse(inputText);
                } catch (ParseException e) {
                    isValid = false;
                }

                if (isValid) {
                    addline_hopen_layout.setError(null);
                } else {
                    addline_hopen_layout.setError("Formato incorrecto. Use HH:mm");
                }
            }
        });


    }

    public void showSnackBar(String message) {
        Snackbar.make(findViewById(R.id.edtxt_addline_codeline), message, Snackbar.LENGTH_LONG).show();

    }

    public void addLineButton(View view) {
        createLineBody();
        presenter.addLine(linebody, token);
    }

    private void createLineBody() {
        linebody = new Line();

        linebody.setCodeLine(((TextInputEditText) findViewById(R.id.edtxt_addline_codeline)).getText().toString());
        linebody.setColor(((TextInputEditText) findViewById(R.id.edtxt_addline_color)).getText().toString());
        linebody.setFirstTime(((TextInputEditText) findViewById(R.id.edtxt_addline_hopen)).getText().toString());
        linebody.setLastTime(((TextInputEditText) findViewById(R.id.edtxt_addline_hclose)).getText().toString());
        linebody.setStopTime(Integer.parseInt(((TextInputEditText) findViewById(R.id.edtxt_addline_stoptime)).getText().toString()));

    }

    /**
     * Menu barra de tareas Lineas Add
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
            finish();
            startActivity(intent);

            return true;


        }

        return false;
    }
}