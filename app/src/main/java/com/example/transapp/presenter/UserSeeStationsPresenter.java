package com.example.transapp.presenter;

import android.content.Context;

import com.example.transapp.contract.UserSeeStationsContract;
import com.example.transapp.domain.Stations;
import com.example.transapp.model.UserSeeStationsModel;
import com.example.transapp.view.UserSeeStationsView;

import java.util.List;

public class UserSeeStationsPresenter implements UserSeeStationsContract.Presenter, UserSeeStationsContract.Model.OnloadUserStations {

    private UserSeeStationsView view;
    private UserSeeStationsModel model;
    private long idLine;
    private Context context;

    public UserSeeStationsPresenter(UserSeeStationsView view, long idLine, Context context){
        this.view = view;
        this.idLine = idLine;
        this.context = context;
        this.model = new UserSeeStationsModel(idLine,context);
    }

    @Override
    public void OnSuccessLoad(List<Stations> stationsList) {
        view.showAllStations(stationsList);
    }

    @Override
    public void OnErrorLoad(String message) {

    }

    @Override
    public void userLoadAllStations() {
        model.userLoadAllStations(this,idLine);
    }
}
