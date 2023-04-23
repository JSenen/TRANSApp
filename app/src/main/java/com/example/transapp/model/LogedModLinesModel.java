package com.example.transapp.model;

import android.content.Context;
import android.util.Log;

import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.LogedModLinesContract;
import com.example.transapp.domain.Line;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogedModLinesModel implements LogedModLinesContract.Model {

    private Context contex;

    @Override
    public void loadAllLines(OnLoadLinesListener listener) {

        Log.d("MODEL MODLINES","Llamada a la api");
        TransAPIInterface apiInterface = TransAPI.buildInstancce();
        Call<List<Line>> callLines = apiInterface.getAllLines();

        //Llamada a la API desde el model. Cuando acabe devuelve resultados
        callLines.enqueue(new Callback<List<Line>>() {
            @Override
            public void onResponse(Call<List<Line>> call, Response<List<Line>> response) {
                //Recoge resultados
                Log.d("API LINES ", "Llamada api desde MODEL LogedModLines");
                List<Line> lines = response.body();
                Log.d("API LINES","Carga datos Lines exito");
                listener.onLoadLinesSuccess(lines);
            }

            @Override
            public void onFailure(Call<List<Line>> call, Throwable t) {
                Log.d("API LINES ", "Llamada api desde MODEL LogedModLines ERRONEA");
                t.printStackTrace();
                String message = "Error llamada a la API";
                listener.onLoadLinesError(message);

            }
        });

    }
}
