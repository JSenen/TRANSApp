package com.example.transapp.adapter;

import static com.example.transapp.database.Constants.DATA_BASE_NAME;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.transapp.R;
import com.example.transapp.database.FavStationsDB;
import com.example.transapp.database.FavoriteStationsDAO;
import com.example.transapp.domain.DataSingleton;
import com.example.transapp.domain.FavoriteStations;
import com.example.transapp.domain.Stations;
import com.example.transapp.view.MapStationsView;

import java.util.List;

public class UserSeeStationsAdapter extends RecyclerView.Adapter<UserSeeStationsAdapter.UserSeeStationsHolder> {


    private Context context;
    private List<Stations> stationsList;
    private FavoriteStationsDAO favoriteStationsDAO;
    private boolean isFavorite;



    public UserSeeStationsAdapter(Context context, List<Stations> stationsList) {
        this.context = context;
        this.stationsList = stationsList;
        isFavorite = false;

    }
    public Context getContext() {
        return context;
    }

    @Override
    public UserSeeStationsAdapter.UserSeeStationsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_user_see_list_stations_item, parent, false);
        return new UserSeeStationsAdapter.UserSeeStationsHolder(view);
    }
    @Override
    public void onBindViewHolder(UserSeeStationsAdapter.UserSeeStationsHolder holder, int position) {
        holder.stationName.setText(stationsList.get(position).getName());
        holder.hopen.setText((stationsList.get(position).getHopen()));
        holder.hclose.setText(stationsList.get(position).getHclose());
        holder.wifi.setChecked(stationsList.get(position).isWifi());
        holder.info.setChecked(stationsList.get(position).isPtoInfo());
        holder.bus.setChecked(stationsList.get(position).isBusStation());
        holder.taxi.setChecked(stationsList.get(position).isTaxiStation());

        if (isFavorite(position)){
            holder.favButton.setImageResource(R.drawable.ic_favorites_on_foreground);
        } else {
            holder.favButton.setImageResource(R.drawable.ic_favorites_off_foreground);
        }

    }
    private boolean isFavorite(int position) {
        Stations station = stationsList.get(position);
        final FavStationsDB database = Room.databaseBuilder(context, FavStationsDB.class, DATA_BASE_NAME)
                .allowMainThreadQueries().build();
        FavoriteStations existingFavorite = database.favoriteStationsDAO().getFavStationById(station.getId());
        return existingFavorite != null;
    }

    @Override
    public int getItemCount() {
        return stationsList.size();
    }

    public class UserSeeStationsHolder extends RecyclerView.ViewHolder {

        public TextView stationName, hopen, hclose;
        public CheckBox wifi, bus, taxi, info;
        public ImageButton mapButton,favButton;
        public View parentView;

        public UserSeeStationsHolder(View view) {
            super(view);
            parentView = view;
            isFavorite = false;

            stationName = view.findViewById(R.id.rcview_user_seestations_stationName_item);
            hopen = view.findViewById(R.id.rcview_user_seestations_stationHOpen_item);
            hclose = view.findViewById(R.id.rcview_user_seestations_stationHclose_item);
            wifi = view.findViewById(R.id.rcview_user_checkBox_stations_wifi);
            info = view.findViewById(R.id.rcview_user_checkBox_stations_info);
            bus = view.findViewById(R.id.rcview_user_checkBox_stations_bus);
            taxi = view.findViewById(R.id.rcview_user_checkBox_stations_taxi);
            mapButton = view.findViewById(R.id.rcview_user_button_stations_map);
            favButton = view.findViewById(R.id.favButton);

            /** Agregar a favoritos */
            favButton.setOnClickListener(fav -> addFavorite(getAdapterPosition()));

            // Llamada al mapa
            mapButton.setOnClickListener(map -> seeMap(getAdapterPosition()));


        }
    }


    public void addFavorite(int position){
        Stations station = stationsList.get(position);
        FavoriteStations favoriteStations = new FavoriteStations();
        favoriteStations.setId(station.getId());
        favoriteStations.setName(station.getName());
        favoriteStations.setLatitude(station.getLatitude());
        favoriteStations.setLongitude(station.getLongitude());
        favoriteStations.setHopen(station.getHopen());
        favoriteStations.setHclose(station.getHclose());
        favoriteStations.setPtoInfo(station.isPtoInfo());
        favoriteStations.setWifi(station.isWifi());
        favoriteStations.setTaxiStation(station.isTaxiStation());
        favoriteStations.setBusStation(station.isBusStation());

        // Comprobar si la estación ya está en favoritos
        final FavStationsDB database = Room.databaseBuilder(context, FavStationsDB.class,DATA_BASE_NAME)
                .allowMainThreadQueries().build();
        FavoriteStations existingFavorite = database.favoriteStationsDAO().getFavStationById(favoriteStations.getId());
        if (existingFavorite != null) {
            // Si la estación ya está en favoritos, se elimina de la base de datos
            database.favoriteStationsDAO().delFavStation(existingFavorite);
            isFavorite = false;
            notifyItemChanged(position);
            Log.i("TAG","La estación ha sido eliminada de favoritos");
            return;
        }

        //Añadir a la Base de Datos
        database.favoriteStationsDAO().addFavStation(favoriteStations);
        isFavorite = true;
        notifyItemChanged(position);

        Log.i("TAG","Dato almacenado "+favoriteStations.getName());
    }

    public void seeMap(int position){
        Stations station = stationsList.get(position);

        // Obtener la instancia de la clase singleton
        DataSingleton dataSingleton = DataSingleton.getInstance();

        // Establecer los valores de los atributos
        dataSingleton.setIdStation(station.getId());
        dataSingleton.setLongitude(station.getLongitude());
        dataSingleton.setLatitude(station.getLatitude());
        dataSingleton.setStationName(station.getName());

        // Iniciar la Activity MapStationsView
        Intent intent = new Intent(context, MapStationsView.class);
        intent.putExtra("VistaLLama","UserSeeStationsAdapter");
        intent.putExtra("RESULT_DATA", station.getId());
        context.startActivity(intent);
    }

}
