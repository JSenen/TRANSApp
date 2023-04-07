package com.example.transapp.contract;

import com.example.transapp.domain.Token;

import retrofit2.Callback;

public interface LoginJWTContract {
    interface Model{
        void login(String username, String passwrd, Callback<Token> callback);

    }
    interface View{
        void showSnackbar(String message);

    }

    interface Presenter{
        void login(String username, String passwrd);

    }
}
