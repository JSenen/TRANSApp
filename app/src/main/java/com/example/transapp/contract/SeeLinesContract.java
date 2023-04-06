package com.example.transapp.contract;

import com.example.transapp.domain.Lines;

import java.util.List;

public interface SeeLinesContract {

    interface Model {
        interface OnLoadLinesListener{
            void onLoadLinesSuccess(List<Lines> lines);
            void onLoadLinesError(String message);
        }
        void loadAllLines(OnLoadLinesListener listener);

    }

    interface View{

        void showLines(List<Lines> lines);
    }
    /** Presenter interactua con el Model y el View */
    interface Presenter{
        void loadAllLines();


    }
}
