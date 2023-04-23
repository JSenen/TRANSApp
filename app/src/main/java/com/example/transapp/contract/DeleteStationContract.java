package com.example.transapp.contract;

public interface DeleteStationContract {

    interface Model{
        interface OnDeleteStationListener {
            void onDeleteSuccess();
            void onDeleteError(String message);
        }
        void deleteStation(String token,long taskId, OnDeleteStationListener listener);

    }
    interface View{
        void showMessage(String message);
    }
    interface Presenter{
        void deleteStation(String token,long stationId);

    }
}
