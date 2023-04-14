package com.example.transapp.view;

import static com.example.transapp.database.Constants.DATA_BASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.transapp.R;
import com.example.transapp.adapter.UserFavoritesAdapter;
import com.example.transapp.adapter.UserSeeStationsAdapter;
import com.example.transapp.contract.UserSeeFavoritesContract;
import com.example.transapp.database.FavStationsDB;
import com.example.transapp.domain.FavoriteStations;
import com.example.transapp.domain.Stations;
import com.example.transapp.presenter.UserFavoritesPresenter;
import com.example.transapp.presenter.UserSeeListStationsPresenter;

import java.util.ArrayList;
import java.util.List;

public class UserFavoritesView extends AppCompatActivity implements UserSeeFavoritesContract.View {

    private long idLine;
    private List<FavoriteStations> favoriteStations;
    private UserFavoritesPresenter presenter;
    private UserFavoritesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_favorites_view);

        favoriteStations = new ArrayList<>();

        presenter = new UserFavoritesPresenter(this,this);
        //presenter.loadAllFavorites();
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        favoriteStations = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rcview_userfavorites);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserFavoritesAdapter(this, favoriteStations);
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();

        final FavStationsDB db = Room.databaseBuilder(this,FavStationsDB.class,DATA_BASE_NAME)
                .allowMainThreadQueries().build();

        //Limpiamos la lista defavoritos
        favoriteStations.clear();
        //Añadimos los favoritos
        favoriteStations.addAll(db.favoriteStationsDAO().getAll());
        //Notificamos cambios al adapter
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showAllFavorites(List<FavoriteStations> favoriteStationslist) {

    }

    /** Menu barra de tareas */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.taskbar_user_simplemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //Regresa a la pantalla anterior
        Intent intent = new Intent(this, MainActivityView.class);
        startActivity(intent);
        return true;
    }
}