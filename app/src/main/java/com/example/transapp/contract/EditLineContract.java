package com.example.transapp.contract;

import com.example.transapp.domain.Line;
import com.example.transapp.domain.Stations;

public interface EditLineContract {
    interface Model{

        interface OnUpdateLineListener{
            void OnUpdateSuccess();
            void OnUpdateError(String message);
        }
        void editLine(String token, long idLine, EditLineContract.Model.OnUpdateLineListener listener, Line linebody);

    }
    interface View {

        void showSnackBar(String message);

    }
    interface Presenter{

        void editLineUpdate(String token, long idLine, Line linebody);
    }
}
