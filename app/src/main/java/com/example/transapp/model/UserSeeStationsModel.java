package com.example.transapp.model;

import android.content.Context;
import android.util.Log;

import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.UserSeeStationsContract;
import com.example.transapp.domain.Stations;
import com.example.transapp.presenter.UserSeeStationsPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSeeStationsModel implements UserSeeStationsContract.Model {

    private long idLine;
    private Context context;

    public UserSeeStationsModel(long idLine, Context context){
        this.idLine = idLine;
        this.context = context;
    }

    @Override
    public void userLoadAllStations(OnloadUserStations listener, long idLine) {
        TransAPIInterface apiInterface = TransAPI.buildInstancce();
        Call<List<Stations>> callStations = apiInterface.getStationsByLine(idLine);
        Log.d("TAG", "LLAMADA A LA API USERLOADSTATIONSL: " + idLine);

        //Llamada a la API
        callStations.enqueue(new Callback<List<Stations>>() {
            @Override
            public void onResponse(Call<List<Stations>> call, Response<List<Stations>> response) {
                //Recoge resultados
                if (response.body() != null) {
                    List<Stations> stations = response.body();
                    listener.OnSuccessLoad(stations);
                    Log.d("TAG", "Código de respuesta: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<Stations>> call, Throwable t) {
                Log.d("API STATIONS ", "Llamada erronea" + t);
                t.printStackTrace();
                String message = "Error llamada a la API";
                listener.OnErrorLoad(message);

            }
        });
    }
}
