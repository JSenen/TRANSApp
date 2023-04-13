package com.example.transapp.presenter;

import com.example.transapp.R;
import com.example.transapp.contract.EditStationContract;
import com.example.transapp.domain.Stations;
import com.example.transapp.model.EditStationModel;
import com.example.transapp.view.EditStationView;

public class EditStationPresenter implements EditStationContract.Presenter, EditStationContract.Model.OnUpdateStationListener {

    private EditStationView view;
    private EditStationModel model;
    private String token;
    private long idStation;
    private Stations stationBody;

    public EditStationPresenter(EditStationView view,String token,long idStation, Stations stationBody){
        this.view = view;
        this.token = token;
        this.idStation = idStation;
        this.stationBody = stationBody;
        this.model = new EditStationModel(token,idStation);

    }


    @Override
    public void editStation(String token, long idStation, Stations stationBody) {
        model.editStation(token,idStation,this,stationBody);

    }

    @Override
    public void onUpdateSuccess() {
        view.showSnackBar("Estacion modificada");

    }

    @Override
    public void onUpdateError(String message) {

    }
}
