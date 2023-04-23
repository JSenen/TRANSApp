package com.example.transapp.contract;

import android.content.Context;

import com.example.transapp.domain.FavoriteStations;

import java.util.List;

public interface UserSeeFavoritesContract {

    interface Model{
        interface OnLoadFavoritesListener{
            void onFavOk(List<FavoriteStations> favoriteStations);
            void onFavKO();
        }
        void loadAllFavorites(OnLoadFavoritesListener listener, Context context);

    }
    interface View{
        void showAllFavorites(List<FavoriteStations> favoriteStationslist);
    }
    interface Presenter{
        void loadAllFavorites();
    }
}
