package com.example.transapp.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.AddLineContract;
import com.example.transapp.contract.AddStationContract;
import com.example.transapp.domain.Line;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddLineModel implements AddLineContract.Model {

    private Line linebody;
    private String token;

    public AddLineModel(Line linebody,String token){
        this.linebody = linebody;
        this.token = token;

    }

    @Override
    public void addLine(Line linebody, AddLineContract.Model.OnAddLineListener listener, String token) {

        try {
            TransAPIInterface service = TransAPI.buildInstancce();
            /** Operación API securizada, necesario pasar cabecera Bearer con el token */
            Call<Line> callTasks = service.addOneLine("Bearer " + token,linebody);
            Log.i("TAG","LLamasa api POST LINE "+"Token ->"+token);
            Log.i("TAG","LLamasa api POST LINE "+"Body ->"+linebody.getCodeLine());
            callTasks.enqueue(new Callback<Line>() {
                @Override
                public void onResponse(Call<Line> call, Response<Line> response) {
                    Log.i("TAG","Rspuesta api OK ADD LINE "+response.code());
                    listener.OnAddLineSuccess();
                }

                @Override
                public void onFailure(Call<Line> call, Throwable t) {
                    t.printStackTrace();
                    listener.OnAddLineError();
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }

    }

}
