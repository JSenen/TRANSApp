package com.example.transapp.presenter;

import android.content.Context;

import com.example.transapp.contract.SeeStationsContract;
import com.example.transapp.domain.Stations;
import com.example.transapp.model.SeeLinesModel;
import com.example.transapp.model.SeeStationsModel;
import com.example.transapp.view.SeeLinesActivityView;
import com.example.transapp.view.SeeStationesActivityView;

import java.util.List;

public class SeeStationsPresenter implements SeeStationsContract.Presenter, SeeStationsContract.Model.OnLoadLinesListener {

    private SeeStationsModel model;
    private SeeStationesActivityView view;
    private long idLinea;
    private String token;
    private Context context;

    public SeeStationsPresenter(SeeStationesActivityView view, long idLinea, String token, Context context){
        this.view = view;
        this.idLinea = idLinea;
        this.token = token;
        this.context = context;
        this.model = new SeeStationsModel(idLinea,token,context);
    }


    @Override
    public void loadAllStations() {
        model.loadAllStations(this, idLinea, token);
    }

    @Override
    public void onLoadLinesSuccess(List<Stations> stationsList) {
        view.showStations(stationsList);

    }

    @Override
    public void onLoadLinesError(String message) {

    }
}
