package com.example.transapp.model;

import android.content.Context;
import android.util.Log;

import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.SeeLinesContract;
import com.example.transapp.domain.Line;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SeeLinesModel implements SeeLinesContract.Model {

    private Context context;

    @Override
    public void loadAllLines(OnLoadLinesListener listener) {

        TransAPIInterface apiInterface = TransAPI.buildInstancce();
        Call<List<Line>> callLines = apiInterface.getAllLines();

        //Llamada a la API
        callLines.enqueue(new Callback<List<Line>>() {
            @Override
            public void onResponse(Call<List<Line>> call, Response<List<Line>> response) {
                //Recoge resultados
                if (response.body() != null ){
                    List<Line> lines = response.body();
                    listener.onLoadLinesSuccess(lines);
                    Log.d("TAG", "Código de respuesta: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<Line>> call, Throwable t) {
                Log.d("TAG", "Código de respuesta: ERROR");
                t.printStackTrace();
                String message = "Error llamada a la API";
                listener.onLoadLinesError(message);

            }
        });
    }
}
