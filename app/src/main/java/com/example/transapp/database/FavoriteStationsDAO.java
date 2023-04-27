package com.example.transapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.transapp.domain.FavoriteStations;
import com.example.transapp.domain.Stations;

import java.util.List;

@Dao
public interface FavoriteStationsDAO {

    /** Obtener datos */
    @Query("SELECT * FROM favstations")
    List<FavoriteStations> getAll();

    /** Grabar datos */
    @Insert
    void addFavStation(FavoriteStations favoriteStations);

    /** Eliminar datos */
    @Delete
    void delFavStation(FavoriteStations favoriteStations);
    /** Comprobar si esta en favoritos */
    @Query("SELECT * FROM favstations WHERE id = :id")
    FavoriteStations getFavStationById(long id);
}
