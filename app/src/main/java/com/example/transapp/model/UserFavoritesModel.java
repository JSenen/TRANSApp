package com.example.transapp.model;

import static com.example.transapp.database.Constants.DATA_BASE_NAME;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.example.transapp.contract.UserSeeFavoritesContract;
import com.example.transapp.database.FavStationsDB;
import com.example.transapp.domain.FavoriteStations;

import java.util.ArrayList;
import java.util.List;

public class UserFavoritesModel implements UserSeeFavoritesContract.Model {

    private FavoriteStations favoriteStations;
    private FavStationsDB database;
    @Override
    public void loadAllFavorites(OnLoadFavoritesListener listener, Context context) {
        List<FavoriteStations> favoriteStationsList = new ArrayList<>();
        //Añadir a la Base de Datos
        final FavStationsDB database = Room.databaseBuilder(context, FavStationsDB.class,DATA_BASE_NAME)
                .allowMainThreadQueries().build();
        favoriteStationsList =database.favoriteStationsDAO().getAll();
        listener.onFavOk(favoriteStationsList);

        Log.i("TAG","Dato almacenado "+favoriteStations.getName());

    }

}
