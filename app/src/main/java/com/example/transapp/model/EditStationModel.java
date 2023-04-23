package com.example.transapp.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.EditStationContract;
import com.example.transapp.domain.Stations;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditStationModel implements EditStationContract.Model {

    private String token;
    private long idStation;

    public EditStationModel (String token, long idStation){
        this.token = token;
        this.idStation = idStation;
    }
    @Override
    public void editStation(String token, long idStation, OnUpdateStationListener listener, Stations stationsBody) {

        try {
            TransAPIInterface service = TransAPI.buildInstancce();
            /** Operación API securizada, necesario pasar cabecera Bearer con el token */
            Call<Stations> callTasks = service.updateStation("Bearer " + token,idStation,stationsBody);
            Log.i("TAG","LLamasa api UPDATE STATION "+"Token"+token);
            Log.i("TAG","LLamasa api UPDATE STATION "+"IdStation"+idStation);
            callTasks.enqueue(new Callback<Stations>() {
                @Override
                public void onResponse(Call<Stations> call, Response<Stations> response) {
                    Log.i("TAG","Rspuesta api OK UPDATE STATION "+response.code());
                    listener.onUpdateSuccess();
                }

                @Override
                public void onFailure(Call<Stations> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error invocando a la operación UPDATE STATION";
                    listener.onUpdateError("Error update Station call api");
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }

}

