package com.example.transapp.presenter;

import com.example.transapp.contract.LogedModLinesContract;
import com.example.transapp.domain.Line;
import com.example.transapp.model.LogedModLinesModel;
import com.example.transapp.view.LogedModLinesActivityView;

import java.util.List;


public class LogedModLinesPresenter implements LogedModLinesContract.Presenter,LogedModLinesContract.Model.OnLoadLinesListener{


    private LogedModLinesModel model;
    private LogedModLinesActivityView view;

    public LogedModLinesPresenter(LogedModLinesActivityView view) {
        //Union presenter con Model y View
        this.view = view;
        this.model = new LogedModLinesModel();
    }

    /** REcuperamos los datos del modelo y los enviamos a la vista */
    @Override
    public void loadAllLines() {
        model.loadAllLines(this);
    }

    @Override
    public void onLoadLinesSuccess(List<Line> lines) {
        /** enviamos a la vista */
        view.showLines(lines);

    }

    @Override
    public void onLoadLinesError(String message) {
        //TODO Caso de error
    }
}
