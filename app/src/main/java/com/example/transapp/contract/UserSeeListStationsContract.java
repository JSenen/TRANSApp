package com.example.transapp.contract;

import com.example.transapp.domain.Stations;

import java.util.List;

public interface UserSeeListStationsContract {

    interface Model{


        void downloadCsvList(long idLine);

        interface OnLoadListUserStations{
            void OnSucces(List<Stations> stations);
            void onError(String message);

        }
        interface OnSearchByParams{
            void OnSuccessSearch(List<Stations> stations);
            void OnErrorSearch();
        }

        void loadAllStations(OnLoadListUserStations listener ,long idLine);
        void loadStationsByParameters(OnSearchByParams listener, long idLine, boolean wifi, boolean busStation, boolean taxiStation, boolean ptoInfo);

    }
    interface View{
        void showAllStations(List<Stations> stationsList);
    }
    interface Presenter{
        void loadAllStations();
        void loadStationsByParameters(boolean wifi, boolean busStation, boolean taxiStation, boolean ptoInfo);

        void downloadCSV(long idLine);
    }
}
