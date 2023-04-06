package com.example.transapp.model;

import android.content.Context;

import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.SeeLinesContract;
import com.example.transapp.domain.Lines;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SeeLinesModel implements SeeLinesContract.Model {

    private Context context;

    public SeeLinesModel(Context context){
        this.context = context;
    }

    @Override
    public void loadAllLines(OnLoadLinesListener listener) {

        TransAPIInterface apiInterface = TransAPI.buildInstancce();
        Call<List<Lines>> callLines = apiInterface.getLines();

        //Llamada a la API
        callLines.enqueue(new Callback<List<Lines>>() {
            @Override
            public void onResponse(Call<List<Lines>> call, Response<List<Lines>> response) {
                //Recoge resultados
                List<Lines> lines = response.body();
                listener.onLoadLinesSuccess(lines);
            }

            @Override
            public void onFailure(Call<List<Lines>> call, Throwable t) {
                String message = "Error llamada a la API";
                listener.onLoadLinesError(message);

            }
        });
    }
}
