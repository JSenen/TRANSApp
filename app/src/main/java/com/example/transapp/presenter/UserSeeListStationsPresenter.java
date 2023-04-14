package com.example.transapp.presenter;

import android.content.Context;

import com.example.transapp.contract.UserSeeListStationsContract;
import com.example.transapp.contract.UserSeeStationsContract;
import com.example.transapp.domain.Stations;
import com.example.transapp.model.UserSeeListStationsModel;
import com.example.transapp.model.UserSeeStationsModel;
import com.example.transapp.view.UserSeeListStationsView;
import com.example.transapp.view.UserSeeStationsView;

import java.util.List;

public class UserSeeListStationsPresenter implements UserSeeListStationsContract.Presenter, UserSeeListStationsContract.Model.OnLoadListUserStations ,
UserSeeListStationsContract.Model.OnSearchByParams{

    private List<Stations> stationsList;
    private long idLine;
    private UserSeeListStationsView view;
    private UserSeeListStationsModel model;
    private Context context;

    public UserSeeListStationsPresenter(long idLine, UserSeeListStationsView view, Context context) {
        this.stationsList = stationsList;
        this.idLine = idLine;
        this.view = view;
        this.model = new UserSeeListStationsModel(idLine,context);
    }

    @Override
    public void OnSucces(List<Stations> stationsList) {
        view.showAllStations(stationsList);

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void loadAllStations() {
        model.loadAllStations(this,idLine);
    }

    @Override
    public void loadStationsByParameters(boolean wifi, boolean busStation, boolean taxiStation) {
        model.loadStationsByParameters(this, idLine,wifi,busStation,taxiStation);
    }
    /** Respuestas a busqueda por parametros */
    @Override
    public void OnSuccessSearch(List<Stations> stations) {
        view.showAllStations(stations);
    }

    @Override
    public void OnErrorSearch() {

    }
}
