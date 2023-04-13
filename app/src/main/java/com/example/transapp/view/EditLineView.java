package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.transapp.R;
import com.example.transapp.contract.EditLineContract;
import com.example.transapp.domain.Line;
import com.example.transapp.domain.LineDataSingleton;
import com.example.transapp.presenter.EditLinePresenter;
import com.google.android.material.snackbar.Snackbar;

public class EditLineView extends AppCompatActivity implements EditLineContract.View {

    private Line linebody;
    private EditLinePresenter presenter;
    private String codeline,color,firstTime,lastTime,stopTimeString;
    private long idLine;
    private int stopTime;
    private ImageButton butEdit;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_line_view);

        //Recogemos datos de la linea
        LineDataSingleton lineDataSingleton = LineDataSingleton.getInstance();
        idLine = lineDataSingleton.getId();
        codeline = lineDataSingleton.getCodeLine();
        color = lineDataSingleton.getColor();
        firstTime = lineDataSingleton.getFirstTime();
        lastTime = lineDataSingleton.getLastTime();
        stopTime = lineDataSingleton.getStopTime();

        //Recuperamos button layout

        butEdit = findViewById(R.id.button_editline);

        //texto en action bar
        getSupportActionBar().setTitle(R.string.Zona_Admin);

        //Mostramos datos
        showDataLine();

        //Inicializar presenter y pasar Token para realizar endpoint
        SharedPreferences preferences = getSharedPreferences("MyPref",MODE_PRIVATE);
        String token = preferences.getString("token","");
        presenter = new EditLinePresenter(this,token,idLine,linebody,context);

        //Listener boton modificar

        butEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recuperamos body a enviar a la api
                linebody = createLineBody();
                presenter.editLineUpdate(token,idLine,linebody);

            }
        });

    }

    private void showDataLine() {

        //Recuperamos elementos y mostramos datos

        EditText linecodeLine = findViewById(R.id.edtxt_editline_codeline);
        EditText linecolorLine = findViewById(R.id.edtxt_editline_color);
        EditText linehopen = findViewById(R.id.edtxt_editline_hopen);
        EditText linehclose = findViewById(R.id.edtxt_editline_hclose);
        EditText linestopTime = findViewById(R.id.edtxt_editline_stoptime);

        stopTimeString = String.valueOf(stopTime);
        linecodeLine.setText(codeline);
        linecolorLine.setText(color);
        linehopen.setText(firstTime);
        linehclose.setText(lastTime);
        linestopTime.setText(stopTimeString);

    }
    /** Metodo para crear el body json necesario en la api con las modificaciones */
    private Line createLineBody() {

        linebody = new Line();

        linebody.setCodeLine(((EditText) findViewById(R.id.edtxt_editline_codeline)).getText().toString());
        linebody.setColor(((EditText) findViewById(R.id.edtxt_editline_color)).getText().toString());
        linebody.setFirstTime(((EditText) findViewById(R.id.edtxt_editline_hopen)).getText().toString());
        linebody.setLastTime(((EditText) findViewById(R.id.edtxt_editline_hclose)).getText().toString());
        linebody.setStopTime(Integer.parseInt(stopTimeString));


        return linebody;
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
            finish();
            startActivity(intent);

            return true;
        }
        return false;
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(findViewById(R.id.editline_codeline_layout),message,Snackbar.LENGTH_LONG).show();
    }
}