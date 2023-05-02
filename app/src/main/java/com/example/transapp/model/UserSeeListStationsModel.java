package com.example.transapp.model;

import android.content.Context;
import android.util.Log;

import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.UserSeeListStationsContract;
import com.example.transapp.domain.Stations;
import com.example.transapp.presenter.UserSeeStationsPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSeeListStationsModel implements UserSeeListStationsContract.Model{

    private long idLine;
    private Context context;

    public UserSeeListStationsModel(long idLine, Context context) {
        this.idLine = idLine;
        this.context = context;
    }


    @Override
    public void loadAllStations(OnLoadListUserStations listener, long idLine) {

        TransAPIInterface apiInterface = TransAPI.buildInstancce();
        Call<List<Stations>> callStations = apiInterface.getStationsByLine(idLine);
        Log.d("TAG", "LLAMADA A LA API EN MODEL: " + idLine);

        //Llamada a la API
        callStations.enqueue(new Callback<List<Stations>>() {
            @Override
            public void onResponse(Call<List<Stations>> call, Response<List<Stations>> response) {
                //Recoge resultados
                if (response.body() != null) {
                    List<Stations> stations = response.body();
                    listener.OnSucces(stations);
                    Log.d("TAG", "Código de respuesta: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<Stations>> call, Throwable t) {
                Log.d("API STATIONS ", "Llamada erronea" + t);
                t.printStackTrace();
                String message = "Error llamada a la API";
                listener.onError(message);

            }
        });

    }

    @Override
    public void loadStationsByParameters(OnSearchByParams listener, long idLine, boolean wifi, boolean busStation, boolean taxiStation, boolean ptoInfo) {
        TransAPIInterface apiInterface = TransAPI.buildInstancce();
        Call<List<Stations>> callStations = apiInterface.getStationsByParams(idLine, wifi, busStation, taxiStation, ptoInfo);
        Log.d("TAG", "LLAMADA A LA API SEARCH EN MODEL: " + wifi);
        callStations.enqueue(new Callback<List<Stations>>() {
            @Override
            public void onResponse(Call<List<Stations>> call, Response<List<Stations>> response) {
                List<Stations> stations = response.body();
                listener.OnSuccessSearch(stations);
            }

            @Override
            public void onFailure(Call<List<Stations>> call, Throwable t) {

            }
        });
    }

}
