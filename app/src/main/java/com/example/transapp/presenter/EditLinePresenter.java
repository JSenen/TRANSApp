package com.example.transapp.presenter;

import com.example.transapp.R;
import com.example.transapp.contract.EditLineContract;
import com.example.transapp.domain.Line;
import com.example.transapp.model.EditLineModel;
import com.example.transapp.view.EditLineView;

public class EditLinePresenter implements EditLineContract.Presenter, EditLineContract.Model.OnUpdateLineListener {

    private EditLineView view;
    private EditLineModel model;
    private Line linebody;
    private String token;
    private long idLine;

    public EditLinePresenter(EditLineView view, String token, long idLine, Line linebody){
        this.view = view;
        this.token = token;
        this.idLine = idLine;
        this.linebody = linebody;
        this.model = new EditLineModel(token,idLine,linebody);
    }


    @Override
    public void editLineUpdate(String token, long idLine, Line linebody) {
        model.editLine(token,idLine,this,linebody);
    }

    @Override
    public void OnUpdateSuccess() {
        view.showSnackBar(String.valueOf(R.string.Linea_mod));
    }

    @Override
    public void OnUpdateError(String message) {

    }

}
