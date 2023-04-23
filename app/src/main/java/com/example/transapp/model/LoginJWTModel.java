package com.example.transapp.model;

import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.LoginJWTContract;
import com.example.transapp.domain.Token;
import com.example.transapp.domain.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginJWTModel implements LoginJWTContract.Model {


    @Override
    public void login(String username, String passwrd, Callback<Token> callback) {
        UserLogin userLogin = new UserLogin(username,passwrd);
        TransAPIInterface apiservice = TransAPI.buildInstancce();
        Call<Token> call = apiservice.getToken(userLogin);
        call.enqueue(callback);

    }
}
