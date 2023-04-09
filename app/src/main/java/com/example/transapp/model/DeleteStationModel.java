package com.example.transapp.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.DeleteStationContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteStationModel implements DeleteStationContract.Model {
    @Override
    public void deleteStation(String token,long stationId, OnDeleteStationListener listener) {
        try {
            TransAPIInterface service = TransAPI.buildInstancce();
            /** Operación API securizada, necesario pasar cabecera Bearer con el token */
            Call<Void> callTasks = service.deleteStationByid("Bearer " + token,stationId);
            Log.i("TAG","LLamasa api DELETE STATION "+"Token"+token);
            Log.i("TAG","LLamasa api DELETE STATION "+"IdStation"+stationId);
            callTasks.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.i("TAG","Rspuesta api OK DELETE STATION "+response.code());
                    listener.onDeleteSuccess();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error invocando a la operación";
                    listener.onDeleteError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
