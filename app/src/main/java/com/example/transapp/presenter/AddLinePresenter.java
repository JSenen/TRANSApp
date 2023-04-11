package com.example.transapp.presenter;

import android.view.View;

import com.example.transapp.contract.AddLineContract;
import com.example.transapp.contract.AddStationContract;
import com.example.transapp.domain.Line;
import com.example.transapp.model.AddLineModel;
import com.example.transapp.view.AddLinesView;

public class AddLinePresenter implements AddLineContract.Presenter, AddLineContract.Model.OnAddLineListener {

    private AddLineModel model;
    private AddLinesView view;
    private Line linebody;
    private String token;

    public AddLinePresenter(AddLinesView view, Line linebody, String token){
        this.view = view;
        this.linebody = linebody;
        this.token = token;
        this.model = new AddLineModel(linebody,token);

    }
    @Override
    public void addLine(Line linebody,String token) {
        model.addLine(linebody,this,token);
    }


    @Override
    public void OnAddLineSuccess() {
        view.showSnackBar("Linea añadida");
    }

    @Override
    public void OnAddLineError() {

    }
}
