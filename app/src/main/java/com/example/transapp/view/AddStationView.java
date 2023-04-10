package com.example.transapp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.transapp.R;

public class AddStationView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station_view);

        EditText editTextHoraOpen = findViewById(R.id.edtxt_addstation_hopen);
        EditText editTextHoraClose = findViewById(R.id.edtxt_addstation_hclose);


       // Crear una instancia de Calendar y obtener la hora actual
        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minuto = calendar.get(Calendar.MINUTE);

        // Crear un objeto TimePickerDialog con el estilo Material
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, com.google.android.material.R.style.MaterialAlertDialog_MaterialComponents_Title_Icon_CenterStacked, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Aquí puedes hacer lo que necesites con la hora seleccionada
                editTextHoraOpen.setText(hourOfDay + ":" + minute);
            }
        }, hora, minuto, false);

        // Mostrar el diálogo de selección de tiempo al hacer clic en el EditText
        editTextHoraOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextHoraOpen.requestFocus();
                timePickerDialog.show();
            }
        });


    }
}