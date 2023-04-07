package com.example.transapp.presenter;

import android.util.Log;

import com.example.transapp.contract.SeeLinesContract;
import com.example.transapp.domain.Line;
import com.example.transapp.model.SeeLinesModel;
import com.example.transapp.view.SeeLinesActivityView;

import java.util.List;

public class SeeLinesPresenter implements SeeLinesContract.Presenter, SeeLinesContract.Model.OnLoadLinesListener {
    /** recupera los datos del modelo y los envia a la vista */

    private SeeLinesModel model;
    private SeeLinesActivityView view;

    public SeeLinesPresenter(SeeLinesActivityView view){
        this.view = view;
        this.model = new SeeLinesModel();
    }

    @Override
    public void loadAllLines() {
        //Llamada para coneguir los datos
        model.loadAllLines(this);

    }

    @Override
    public void onLoadLinesSuccess(List<Line> lines) {
        view.showLines(lines);
    }

    @Override
    public void onLoadLinesError(String message) {
        //TODO Por hacer mostrar error
    }

}
