package com.example.transapp.model;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.SeeStationsContract;
import com.example.transapp.domain.Stations;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeStationsModel implements SeeStationsContract.Model {

    private Context context;
    private long idLinea;

    public SeeStationsModel(long idLinea, Context context) {

        this.idLinea = idLinea;
        this.context = context;
    }

    @Override
    public void loadAllStations(OnLoadStationsListener listener, long idLinea) {

        TransAPIInterface apiInterface = TransAPI.buildInstancce();
        Call<List<Stations>> callStations = apiInterface.getStationsByLine(idLinea);
        Log.d("TAG", "LLAMADA A LA API EN MODEL: " + idLinea);

        //Llamada a la API
        callStations.enqueue(new Callback<List<Stations>>() {
            @Override
            public void onResponse(Call<List<Stations>> call, Response<List<Stations>> response) {
                //Recoge resultados
                if (response.body() != null) {
                    List<Stations> stations = response.body();
                    listener.onLoadStationsSuccess(stations);
                    Log.d("TAG", "Código de respuesta: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<Stations>> call, Throwable t) {
                Log.d("API STATIONS ", "Llamada erronea" + t);
                t.printStackTrace();
                String message = "Error llamada a la API";
                listener.onLoadStationsError(message);

            }
        });
    }

}
