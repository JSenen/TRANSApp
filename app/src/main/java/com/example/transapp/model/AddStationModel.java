package com.example.transapp.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.AddStationContract;
import com.example.transapp.domain.Stations;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStationModel implements AddStationContract.Model {

    private long idLinea;
    private String token;
    private Stations stationBody;
    public AddStationModel(String token, long idLinea,Stations stationBody){
        this.stationBody = stationBody;
        this.idLinea = idLinea;
        this.token = token;

    }

    @Override
    public void addStation(String token, long idLinea, OnAddStationListener listener, Stations stationBody) {
        try {
            TransAPIInterface service = TransAPI.buildInstancce();
            /** Operación API securizada, necesario pasar cabecera Bearer con el token */
            Call<Stations> callTasks = service.addStationToLine("Bearer " + token,idLinea,stationBody);
            Log.i("TAG","LLamasa api POST STATION "+"Token ->"+token);
            Log.i("TAG","LLamasa api POST STATION "+"idLinea ->"+idLinea);
            Log.i("TAG","Body -> "+stationBody.getName()+" "+stationBody.getLatitude());
            callTasks.enqueue(new Callback<Stations>() {
                @Override
                public void onResponse(Call<Stations> call, Response<Stations> response) {
                    Log.i("TAG","Rspuesta api OK ADD STATION "+response.code());
                    listener.onAddSuccess();
                }

                @Override
                public void onFailure(Call<Stations> call, Throwable t) {
                    t.printStackTrace();
                    listener.onAddError();
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
