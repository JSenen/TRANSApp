package com.example.transapp.api;

import static com.example.transapp.api.Constantes.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransAPI {

    public static TransAPIInterface buildInstancce(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL) //URL Base API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(TransAPIInterface.class);

    }
}
