package com.example.transapp.presenter;

import android.content.Context;

import com.example.transapp.R;
import com.example.transapp.contract.AddStationContract;
import com.example.transapp.domain.Stations;
import com.example.transapp.model.AddStationModel;
import com.example.transapp.view.AddStationView;

public class AddStationPresenter implements AddStationContract.Presenter, AddStationContract.Model.OnAddStationListener{

    private AddStationView view;
    private AddStationModel model;
    private String token;
    private long idLinea;
    private Stations stationBody;
    private Context context;

    public AddStationPresenter(AddStationView view, String token, Stations stationBody, Context context){
        this.view = view;
        this.token = token;
        this.stationBody = stationBody;
        this.context = context;
        this.model = new AddStationModel(token,idLinea);

    }

    /** Metodos del Listener del Model*/
    @Override
    public void onAddSuccess() {
        view.showSanckBar(context.getString(R.string.Estacion_add));
    }

    @Override
    public void onAddError() {

    }

    @Override
    public void addStation(String token, long idLinea, Stations stationBody) {
        model.addStation(token,idLinea,this,stationBody);
    }
}
