package com.example.transapp.contract;

import com.example.transapp.domain.Line;
import com.example.transapp.domain.Stations;

import java.util.List;

public interface SeeStationsContract {

    interface Model{
        interface OnLoadLinesListener{
            void onLoadLinesSuccess(List<Stations> stationsList);
            void onLoadLinesError(String message);
        }
        void loadAllStations(SeeStationsContract.Model.OnLoadLinesListener listener, long idLinea, String token);

    }

    interface View {

        void showStations(List<Stations> stationsList);

    }

    interface Presenter{
        void loadAllStations();
    }
}

