package com.example.transapp.presenter;

import com.example.transapp.contract.SeeLinesContract;
import com.example.transapp.domain.Lines;
import com.example.transapp.model.SeeLinesModel;
import com.example.transapp.view.SeeLinesActivityView;

import java.util.List;

public class SeeLinesPresenter implements SeeLinesContract.Presenter, SeeLinesContract.Model.OnLoadLinesListener {
    /** recupera los datos del modelo y los envia a la vista */

    private SeeLinesModel model;
    private SeeLinesActivityView view;

    public SeeLinesPresenter(SeeLinesActivityView view){
        this.view = view;
    }

    @Override
    public void loadAllLines() {
        //Llamada para coneguir los datos
        model.loadAllLines(this);

    }

    @Override
    public void onLoadLinesSuccess(List<Lines> lines) {
        view.showLines(lines);
    }

    @Override
    public void onLoadLinesError(String message) {
        //TODO Por hacer mostrar error
    }
}
