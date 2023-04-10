package com.example.transapp.api;


import com.example.transapp.domain.Line;
import com.example.transapp.domain.Stations;
import com.example.transapp.domain.Token;
import com.example.transapp.domain.UserLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TransAPIInterface {
    /** operaciones con la api */

    @GET("lines")
    Call<List<Line>> getAllLines();
    @GET("line/{lineId}/stations")
    Call<List<Stations>> getStationsByLine(@Path("lineId") long id);

    @POST("station/{lineid}/station") //TODO implementar
    Call<Stations> postStation(@Path("lineid") String lineid, @Body Stations stations);

    @POST("token")
    Call<Token> getToken(@Body UserLogin userLogin);

    @POST("/station/{lineId}/station")
    Call<Stations> addStationToLine(@Header("Authorization") String token, @Path("lineId") long id, @Body Stations stations);

    @PUT("/stations/{id}")
    Call<Stations> updateStation(@Header("Authorization") String token, @Path("id") long id, @Body Stations stations);

    @DELETE("stations/{id}")
    Call<Void> deleteStationByid(@Header("Authorization") String token, @Path("id") long id);




}
