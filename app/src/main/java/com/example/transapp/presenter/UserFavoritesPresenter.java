package com.example.transapp.presenter;

import android.content.Context;
import android.view.View;

import com.example.transapp.contract.UserSeeFavoritesContract;
import com.example.transapp.domain.FavoriteStations;
import com.example.transapp.model.UserFavoritesModel;
import com.example.transapp.view.UserFavoritesView;

import java.util.List;

public class UserFavoritesPresenter implements UserSeeFavoritesContract.Presenter, UserSeeFavoritesContract.Model.OnLoadFavoritesListener {

    private UserFavoritesView view;
    private UserFavoritesModel model;
    private Context context;

    public UserFavoritesPresenter(UserFavoritesView view, Context context){
        this.view = view;
        this.context = context;
        this.model = new UserFavoritesModel();
    }
    @Override
    public void loadAllFavorites() {
        model.loadAllFavorites(this,context);
    }

    @Override
    public void onFavOk(List<FavoriteStations> favoriteStations) {
        view.showAllFavorites(favoriteStations);
    }

    @Override
    public void onFavKO() {

    }
}
