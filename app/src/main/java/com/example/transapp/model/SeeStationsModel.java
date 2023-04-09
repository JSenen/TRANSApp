package com.example.transapp.model;

import android.content.Context;
import android.util.Log;

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
    public SeeStationsModel(long idLinea){
        this.idLinea = idLinea;
    }

    @Override
    public void loadAllStations(OnLoadStationsListener listener, long idLinea) {
//        SharedPreferences preferences = context.getSharedPreferences("MyPref",Context.MODE_PRIVATE);
//        token = preferences.getString("token","");
        TransAPIInterface apiInterface = TransAPI.buildInstancce();
       Call<List<Stations>> callStations = apiInterface.getStationsByLine(idLinea);
        Log.d("TAG", "LLAMADA A LA API EN MODEL: " + idLinea);

        //Llamada a la API
        callStations.enqueue(new Callback<List<Stations>>() {
            @Override
            public void onResponse(Call<List<Stations>> call, Response<List<Stations>> response) {
                //Recoge resultados
                if (response.body() != null ){
                    List<Stations> stations = response.body();
                    listener.onLoadStationsSuccess(stations);
                    Log.d("TAG", "Código de respuesta: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<Stations>> call, Throwable t) {
                Log.d("API STATIONS ", "<-----------------Llamada ERRONEA--------------->");
                t.printStackTrace();
                String message = "Error llamada a la API";
                listener.onLoadStationsError(message);

            }
        });
    }

}
