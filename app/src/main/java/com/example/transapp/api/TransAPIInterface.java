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
import retrofit2.http.Query;

public interface TransAPIInterface {
    /** operaciones con la api */

    @GET("lines")
    Call<List<Line>> getAllLines();
    @GET("line/{lineId}/stations")
    Call<List<Stations>> getStationsByLine(@Path("lineId") long id);
    @GET("line/{lineId}/stations")
    Call<List<Stations>> getStationsByParams(@Path("lineId") long id, @Query("wifi") boolean wifi,
                                           @Query("busStation") boolean busStation,
                                           @Query("taxiStation") boolean taxiStation);

    @POST("token")
    Call<Token> getToken(@Body UserLogin userLogin);

    @POST("station/{id}/station")
    Call<Stations> addStationToLine(@Header("Authorization") String token, @Path("id") long id, @Body Stations stations);

    @POST("line")
    Call<Line> addOneLine(@Header("Authorization") String token, @Body Line line);

    @PUT("/stations/{id}")
    Call<Stations> updateStation(@Header("Authorization") String token, @Path("id") long id, @Body Stations stations);

    @PUT("lines/{id}")
    Call<Line> updateLine(@Header("Authorization") String token, @Path("id") long id, @Body Line linebody);

    @DELETE("stations/{id}")
    Call<Void> deleteStationByid(@Header("Authorization") String token, @Path("id") long id);
    @DELETE("lines/{id}")
    Call<Void> deleteLineById(@Header("Authorization") String token, @Path("id") long id);



}
