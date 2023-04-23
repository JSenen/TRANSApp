package com.example.transapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.transapp.domain.FavoriteStations;

/**Base de datos favoritos usuario */
@Database(entities = {FavoriteStations.class}, version = 1)
public abstract class FavStationsDB extends RoomDatabase {

    public abstract FavoriteStationsDAO favoriteStationsDAO();

}
