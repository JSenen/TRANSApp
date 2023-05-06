package com.example.transapp.model;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.transapp.R;
import com.example.transapp.api.TransAPI;
import com.example.transapp.api.TransAPIInterface;
import com.example.transapp.contract.UserSeeListStationsContract;
import com.example.transapp.domain.Stations;
import com.example.transapp.presenter.UserSeeStationsPresenter;
import com.example.transapp.view.UserSeeListStationsView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSeeListStationsModel implements UserSeeListStationsContract.Model{

    private long idLine;
    private Context context;

    public UserSeeListStationsModel(long idLine, Context context) {
        this.idLine = idLine;
        this.context = context;
    }


    @Override
    public void downloadCsvList(long idLine) {
        TransAPIInterface apiInterface = TransAPI.buildInstancce();
        Call<ResponseBody> downloadCsv = apiInterface.getStationsCSVList(idLine);
        Log.d("TAG","LLamada API para descarga fichero CSV linea "+idLine);

        downloadCsv.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    //Respuesta OK. Descarga el cuerpo de la respuesta
                    ResponseBody body = response.body();
                    if (body != null){
                        //Obtiene el directorio de descargas del dispositivo
                        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                        if (!dir.exists()) {
                            //Si no existe el directorio, lo crea
                            dir.mkdirs();
                        }
                        try {
                            //Crea el archivo en el directorio de descargas y lo guarda
                            File file = new File(dir, "stationsList.csv");
                            InputStream inputStream = body.byteStream();
                            OutputStream outputStream = new FileOutputStream(file);
                            byte[] buffer = new byte[4096];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                            outputStream.close();
                            inputStream.close();
                            // Notifica al usuario que el archivo se ha descargado correctamente
                            Toast.makeText(context, R.string.DownloadFile_OK, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            // Maneja el error
                            e.printStackTrace();
                            Toast.makeText(context, R.string.DownloadFile_KO, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadAllStations(OnLoadListUserStations listener, long idLine) {

        TransAPIInterface apiInterface = TransAPI.buildInstancce();
        Call<List<Stations>> callStations = apiInterface.getStationsByLine(idLine);
        Log.d("TAG", "LLAMADA A LA API EN MODEL: " + idLine);

        //Llamada a la API
        callStations.enqueue(new Callback<List<Stations>>() {
            @Override
            public void onResponse(Call<List<Stations>> call, Response<List<Stations>> response) {
                //Recoge resultados
                if (response.body() != null) {
                    List<Stations> stations = response.body();
                    listener.OnSucces(stations);
                    Log.d("TAG", "Código de respuesta: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<Stations>> call, Throwable t) {
                Log.d("API STATIONS ", "Llamada erronea" + t);
                t.printStackTrace();
                String message = "Error llamada a la API";
                listener.onError(message);

            }
        });

    }

    @Override
    public void loadStationsByParameters(OnSearchByParams listener, long idLine, boolean wifi, boolean busStation, boolean taxiStation, boolean ptoInfo) {
        TransAPIInterface apiInterface = TransAPI.buildInstancce();
        Call<List<Stations>> callStations = apiInterface.getStationsByParams(idLine, wifi, busStation, taxiStation, ptoInfo);
        Log.d("TAG", "LLAMADA A LA API SEARCH EN MODEL: " + wifi);
        callStations.enqueue(new Callback<List<Stations>>() {
            @Override
            public void onResponse(Call<List<Stations>> call, Response<List<Stations>> response) {
                List<Stations> stations = response.body();
                listener.OnSuccessSearch(stations);
            }

            @Override
            public void onFailure(Call<List<Stations>> call, Throwable t) {

            }
        });
    }

}
