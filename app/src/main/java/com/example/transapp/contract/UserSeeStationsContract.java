package com.example.transapp.contract;

import com.example.transapp.domain.Stations;

import java.util.List;

public interface UserSeeStationsContract {

    interface Model{

        interface OnloadUserStations{
            void OnSuccessLoad(List<Stations> stationsList);
            void OnErrorLoad(String message);
        }
        void userLoadAllStations(OnloadUserStations listener,long idLine);

    }
    interface View{
        void showAllStations(List<Stations> stationsList);

    }
    interface Presenter{

        void userLoadAllStations();

    }
}
