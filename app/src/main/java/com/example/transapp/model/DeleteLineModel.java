package com.example.transapp.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.DeleteLineContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteLineModel implements DeleteLineContract.Model {
    @Override
    public void deleteLine(String token, long idLine,OnDeleteLineListener listener) {

        try {
            TransAPIInterface service = TransAPI.buildInstancce();
            /** Operación API securizada, necesario pasar cabecera Bearer con el token */
            Call<Void> callTasks = service.deleteLineById("Bearer " + token,idLine);
            Log.i("TAG","LLamasa api DELETE LINE "+"Token"+token);
            Log.i("TAG","LLamasa api DELETE LINE "+"IdStation"+idLine);
            callTasks.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.i("TAG","Rspuesta api OK DELETE LINE "+response.code());
                    listener.OnSucces();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.i("TAG","Respuesta api KO DELETE LINE "+t);
                    t.printStackTrace();
                    String message = "Error invocando a la operación";
                    listener.OnError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }

    }
}
