package com.example.transapp.presenter;

import com.example.transapp.adapter.ModLinesAdapter;
import com.example.transapp.contract.DeleteLineContract;
import com.example.transapp.model.DeleteLineModel;

public class DeleteLinePresenter implements DeleteLineContract.Presenter, DeleteLineContract.Model.OnDeleteLineListener {

    private DeleteLineModel model;
    private ModLinesAdapter adapter;

    public DeleteLinePresenter(ModLinesAdapter adapter) {
        this.model = new DeleteLineModel();
        this.adapter = adapter;
    }

    @Override
    public void deleteLine(String token,long idLine) {
        model.deleteLine(token, idLine,this);
    }
    @Override
    public void OnSucces() {

    }

    @Override
    public void OnError(String messge) {

    }

}
