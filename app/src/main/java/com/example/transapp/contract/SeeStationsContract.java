package com.example.transapp.contract;

import android.content.Context;

import com.example.transapp.domain.Stations;

import java.util.List;

public interface SeeStationsContract {

    interface Model{
        /** Listener carga todas las estaciones */
        interface OnLoadStationsListener {
            void onLoadStationsSuccess(List<Stations> stationsList);
            void onLoadStationsError(String message);
        }
        void loadAllStations(OnLoadStationsListener listener, long idLinea);

    }

    interface View {

        void showStations(List<Stations> stationsList);

    }

    interface Presenter{
        void loadAllStations();
    }
}

