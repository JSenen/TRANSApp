package com.example.transapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.transapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;

public class AddLinesView extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lines_view);

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
}