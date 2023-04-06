package com.example.transapp.api;


import com.example.transapp.domain.Lines;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TransAPIInterface {
    /** operaciones con la api */

    @GET("Lines")
    Call<List<Lines>> getLines();


}
