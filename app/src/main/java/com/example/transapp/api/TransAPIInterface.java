package com.example.transapp.api;


import com.example.transapp.domain.Line;
import com.example.transapp.domain.Token;
import com.example.transapp.domain.UserLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TransAPIInterface {
    /** operaciones con la api */

    @GET("lines")
    Call<List<Line>> getLines();

    @POST("token")
    Call<Token> getToken(@Body UserLogin userLogin);




}
