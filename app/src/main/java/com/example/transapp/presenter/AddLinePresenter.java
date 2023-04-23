package com.example.transapp.presenter;

import android.content.Context;
import android.view.View;

import com.example.transapp.R;
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
    private Context context;

    public AddLinePresenter(AddLinesView view, Line linebody, String token, Context context){
        this.view = view;
        this.linebody = linebody;
        this.token = token;
        this.context = context;
        this.model = new AddLineModel(linebody,token);

    }
    @Override
    public void addLine(Line linebody,String token) {
        model.addLine(linebody,this,token);
    }


    @Override
    public void OnAddLineSuccess() {
        view.showSnackBar(context.getString(R.string.Linea_add));
    }

    @Override
    public void OnAddLineError() {

    }
}
