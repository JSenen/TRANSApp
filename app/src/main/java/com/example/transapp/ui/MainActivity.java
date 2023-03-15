package com.example.transapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.transapp.R;
import com.example.transapp.model.User;

public class MainActivity extends AppCompatActivity {
    public EditText etUsername;
    public EditText etPassword;
    public Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.edtxt_main_username);
        etPassword = findViewById(R.id.edtxt_main_password);

        btnLogin = findViewById(R.id.button_main_login);

    }
}