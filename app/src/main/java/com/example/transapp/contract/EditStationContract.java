package com.example.transapp.contract;

import com.example.transapp.domain.Stations;

public interface EditStationContract {

    interface Model{
        interface OnUpdateStationListener{
            void onUpdateSuccess();
            void onUpdateError(String message);
        }
        void editStation(String token, long idStation, OnUpdateStationListener listener, Stations stationBody);
    }
    interface View{
        void showSnackBar(String message);
    }
    interface Presenter{
        void editStation(String token, long idStation, Stations stationBody);

    }
}
