package com.example.transapp.api;


import com.example.transapp.domain.Line;
import com.example.transapp.domain.Stations;
import com.example.transapp.domain.Token;
import com.example.transapp.domain.UserLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TransAPIInterface {
    /** operaciones con la api */

    @GET("lines")
    Call<List<Line>> getAllLines();

    @POST("station/{lineid}/station") //TODO implementar
    Call<Stations> postStation(@Path("lineid") String lineid, @Body Stations stations);

    @POST("token")
    Call<Token> getToken(@Body UserLogin userLogin);

    @GET("line/{lineId}/stations")
    Call<List<Stations>> getStationsByLine(@Path("lineId") long id);

    //    @GET("/line/{lineId}/stations")//TODO Implementar
    //    Call<Line> getLineById(@Header("Authorization") String token, @Path("id") long id);





}
