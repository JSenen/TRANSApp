package com.example.transapp.contract;

import com.example.transapp.domain.Stations;

public interface AddStationContract {

    interface Model{

        interface OnAddStationListener{
            void onAddSuccess();
            void onAddError();
        }
        void addStation(String token, long idLinea, OnAddStationListener listener, Stations stationBody);

    }
    interface View{

        void showSanckBar(String message);

    }
    interface Presenter{

        void addStation(String token, long idLinea, Stations stationBody);

    }

}
