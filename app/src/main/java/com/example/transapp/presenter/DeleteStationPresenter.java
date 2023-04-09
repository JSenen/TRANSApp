package com.example.transapp.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.example.transapp.adapter.SeeStationsAdapter;
import com.example.transapp.contract.DeleteStationContract;
import com.example.transapp.model.DeleteStationModel;

public class DeleteStationPresenter implements DeleteStationContract.Presenter, DeleteStationContract.Model.OnDeleteStationListener {

    private SeeStationsAdapter adapter;
    private DeleteStationModel model;
    public DeleteStationPresenter(SeeStationsAdapter adapter) {
        model = new DeleteStationModel();
        this.adapter = adapter;
    }

    @Override
    public void deleteStation(String token, long stationId) {
        model.deleteStation(token,stationId, this);

    }

    @Override
    public void onDeleteSuccess() {

    }

    @Override
    public void onDeleteError(String message) {

    }
}
