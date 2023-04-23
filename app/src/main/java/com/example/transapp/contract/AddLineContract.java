package com.example.transapp.contract;

import com.example.transapp.domain.Line;

public interface AddLineContract {

    interface Model{

        interface OnAddLineListener{
            void OnAddLineSuccess();
            void OnAddLineError();
        }
        void addLine(Line linebody, AddLineContract.Model.OnAddLineListener listener, String token);
    }
    interface View{
        void showSnackBar(String message);
    }
    interface Presenter{
        void addLine(Line linebody,String token);
    }
}
