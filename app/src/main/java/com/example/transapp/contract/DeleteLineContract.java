package com.example.transapp.contract;

public interface DeleteLineContract {

    interface Model{

        interface OnDeleteLineListener {
            void OnSucces();
            void OnError(String message);
        }
        void deleteLine(String token,long idLine,OnDeleteLineListener listener);
    }
    interface View{

    }
    interface Presenter{
        void deleteLine(String token, long idLine);

    }
}
