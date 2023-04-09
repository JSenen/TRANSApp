package com.example.transapp.presenter;

import android.util.Log;

import com.example.transapp.contract.SeeStationsContract;
import com.example.transapp.domain.Stations;
import com.example.transapp.model.SeeStationsModel;
import com.example.transapp.view.SeeStationesActivityView;

import java.util.List;

public class SeeStationsPresenter implements SeeStationsContract.Presenter, SeeStationsContract.Model.OnLoadStationsListener {

    private SeeStationsModel model;
    private SeeStationesActivityView view;
    private long idLinea;

    public SeeStationsPresenter(SeeStationesActivityView view, long idLinea){
        this.view = view;
        this.idLinea = idLinea;
        this.model = new SeeStationsModel(idLinea);
        Log.d("TAG", "ID de la LINEA en PRESENTER: " + idLinea);
    }


    @Override
    public void loadAllStations() {
        model.loadAllStations(this, idLinea);
    }

    @Override
    public void onLoadStationsSuccess(List<Stations> stationsList) {
        view.showStations(stationsList);

    }

    @Override
    public void onLoadStationsError(String message) {

    }
}
