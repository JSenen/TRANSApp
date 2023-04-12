package com.example.transapp.contract;

import com.example.transapp.domain.Stations;

import java.util.List;

public interface UserSeeListStationsContract {

    interface Model{
        interface OnLoadListUserStations{
            void OnSucces(List<Stations> stations);
            void onError(String message);
        }
        void loadAllStations(OnLoadListUserStations listener ,long idLine);

    }
    interface View{
        void showAllStations(List<Stations> stationsList);
    }
    interface Presenter{
        void loadAllStations();
    }
}
