package com.example.transapp.contract;

import com.example.transapp.domain.Line;

import java.util.List;

public interface LogedModLinesContract {

    interface Model{
        interface OnLoadLinesListener{
            void onLoadLinesSuccess(List<Line> lines);
            void onLoadLinesError(String message);
        }
        void loadAllLines(LogedModLinesContract.Model.OnLoadLinesListener listener);

    }
    interface View{
        void showLines(List<Line> lines);
    }
    interface Presenter{

        void loadAllLines();

    }
}
