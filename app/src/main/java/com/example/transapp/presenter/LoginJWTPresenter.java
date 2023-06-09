package com.example.transapp.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.transapp.R;
import com.example.transapp.contract.LoginJWTContract;
import com.example.transapp.domain.Token;
import com.example.transapp.domain.UserLogin;
import com.example.transapp.model.LoginJWTModel;
import com.example.transapp.view.LoginActivityView;
import com.example.transapp.view.MainActivityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginJWTPresenter implements LoginJWTContract.Presenter {

    private LoginJWTModel model;
    private LoginActivityView view;
    private SharedPreferences preferences; //Para guardar el token
    private Context context; //Necesario para snackbar

    /** se llama al método login del modelo para obtener el token JWT asociado con el usuario.
     * Si la respuesta es exitosa, se guarda el token JWT en las preferencias de la aplicación utilizando
     * la clase SharedPreferences y se llama al método showSuccessMessage de la vista.
     * Si la respuesta falla, se llama al método showErrorMessage de la vista:
     */
    public LoginJWTPresenter (LoginActivityView view, SharedPreferences preferences, Context context){
        this.view = view;
        this.preferences = preferences;
        this.context = context;
        this.model = new LoginJWTModel();
    }
    @Override
    public void login(String username, String passwrd) {
        model.login(username, passwrd, new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()){
                    String token =response.body().getToken();
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("token", token);
                    editor.apply();
                    view.showSnackbar(context.getString(R.string.Login_ok));
                }else{
                    view.showSnackbar(context.getString(R.string.Login_ko));
                    view.goMainActivity();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                view.showSnackbar(context.getString(R.string.Login_ko));
            }
        });

    }
}
