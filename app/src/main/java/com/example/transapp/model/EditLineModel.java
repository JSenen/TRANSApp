package com.example.transapp.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.EditLineContract;
import com.example.transapp.domain.Line;
import com.example.transapp.domain.Stations;
import com.example.transapp.presenter.EditLinePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditLineModel implements EditLineContract.Model {

    private EditLinePresenter presenter;
    private String token;
    private long idLine;
    private Line linebody;

    public EditLineModel(String token,long idLine, Line linebody){
        this.token = token;
        this.idLine = idLine;
        this.linebody = linebody;

    }

    @Override
    public void editLine(String token, long idLine, OnUpdateLineListener listener, Line linebody) {
        try {
            TransAPIInterface service = TransAPI.buildInstancce();
            /** Operación API securizada, necesario pasar cabecera Bearer con el token */
            Call<Line> callTasks = service.updateLine("Bearer " + token,idLine,linebody);
            Log.i("TAG","LLamasa api UPDATE LINE "+"Token"+token);
            Log.i("TAG","LLamasa api UPDATE LINE "+"iDLine"+idLine);
            callTasks.enqueue(new Callback<Line>() {
                @Override
                public void onResponse(Call<Line> call, Response<Line> response) {
                    Log.i("TAG","Rspuesta api OK UPDATE LINE "+response.code());
                    listener.OnUpdateSuccess();
                }

                @Override
                public void onFailure(Call<Line> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error invocando a la operación UPDATE LINE";
                    listener.OnUpdateError("Error update Station call api");
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
