package com.example.transapp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.SeeLinesContract;
import com.example.transapp.contract.SeeStationsContract;
import com.example.transapp.domain.Line;
import com.example.transapp.domain.Stations;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeStationsModel implements SeeStationsContract.Model {

    private Context context;
    private long idLinea;
    private String token;

    public SeeStationsModel(long idLinea, String token,Context context){
        this.idLinea = idLinea;
        this.token = token;
        this.context = context;
    }

    @Override
    public void loadAllStations(OnLoadLinesListener listener, long idLinea,String token) {
        SharedPreferences preferences = context.getSharedPreferences("MyPref",Context.MODE_PRIVATE);
        token = preferences.getString("token","");
        TransAPIInterface apiInterface = TransAPI.buildInstancce();
        Call<Line> callLineById = apiInterface.getLineById("Bearer" + token,idLinea);
        System.out.println("TOKEN ------------------------------>>>"+token);

        //Llamada a la API
        callLineById.enqueue(new Callback<Line>() {
            @Override
            public void onResponse(Call<Line> call, Response<Line> response) {
                //Recoge resultados
                Line line  = response.body();
                List<Stations> stationsList = line.getStationsList();
                Log.d("TAG", "Código de respuesta: " + response.code());
                listener.onLoadLinesSuccess(line.getStationsList());

            }

            @Override
            public void onFailure(Call<Line> call, Throwable t) {
                Log.d("API LINES ", "<-----------------Llamada ERRONEA--------------->");
                t.printStackTrace();
                String message = "Error llamada a la API";
                listener.onLoadLinesError(message);

            }
        });
    }

}
