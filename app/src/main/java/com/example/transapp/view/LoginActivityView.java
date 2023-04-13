package com.example.transapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.transapp.R;
import com.example.transapp.contract.LoginJWTContract;
import com.example.transapp.domain.UserLogin;
import com.example.transapp.presenter.LoginJWTPresenter;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivityView extends AppCompatActivity implements LoginJWTContract.View {

    private EditText edtxtuser;
    private EditText edtxtpasswrd;
    private Button butLogin;
    private LoginJWTPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        //Inicializar vistas
        edtxtuser = findViewById(R.id.edtxt_user);
        edtxtpasswrd = findViewById(R.id.edtxt_passwrd);
        butLogin = findViewById(R.id.butLogin);

        //Inicializar Presenter
        /** Con getShared guardaremos en preferencias el token */
        presenter  = new LoginJWTPresenter(this, getSharedPreferences("MyPref",MODE_PRIVATE));

        //Titulo en ActionBar
        getSupportActionBar().setTitle(R.string.Zona_Admin);

        // Configurar listener para botón de login
        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtxtuser.getText().toString().trim();
                String password = edtxtpasswrd.getText().toString().trim();
                presenter.login(username,password);
            }
        });
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
    //SnackBar al loguearse correctamente
    @Override
    public void showSnackbar(String message) {
        if (message != null) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
            /** SnackBar con boton de confirmacion */
            snackbar.setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    redirectToOtherActivity();
                }
            });
            snackbar.show();
        }

    }

    private void redirectToOtherActivity() {
        Intent intent = new Intent(this, LogedMainActivityView.class);
        startActivity(intent);
    }


}