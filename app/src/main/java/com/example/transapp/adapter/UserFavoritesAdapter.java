package com.example.transapp.adapter;

import static com.example.transapp.database.Constants.DATA_BASE_NAME;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.transapp.R;
import com.example.transapp.database.FavStationsDB;
import com.example.transapp.database.FavoriteStationsDAO;
import com.example.transapp.domain.FavoriteStations;
import com.example.transapp.domain.Stations;

import java.util.List;

public class UserFavoritesAdapter extends RecyclerView.Adapter<UserFavoritesAdapter.UserFavoritesHolder> {
    private Context context;
    private List<FavoriteStations> stationsList;
    private FavoriteStationsDAO favoriteStationsDAO;


    public UserFavoritesAdapter(Context context, List<FavoriteStations> stationsList) {
        this.context = context;
        this.stationsList = stationsList;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public UserFavoritesAdapter.UserFavoritesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_user_favorites_item, parent, false);
        return new UserFavoritesAdapter.UserFavoritesHolder(view);
    }

    @Override
    public void onBindViewHolder(UserFavoritesAdapter.UserFavoritesHolder holder, int position) {
        holder.stationName.setText(stationsList.get(position).getName());
        holder.hopen.setText((stationsList.get(position).getHopen()));
        holder.hclose.setText(stationsList.get(position).getHclose());
        holder.wifi.setChecked(stationsList.get(position).isWifi());
        holder.bus.setChecked(stationsList.get(position).isBusStation());
        holder.taxi.setChecked(stationsList.get(position).isTaxiStation());


    }

    @Override
    public int getItemCount() {
        return stationsList.size();
    }

    public class UserFavoritesHolder extends RecyclerView.ViewHolder {

        public TextView stationName, hopen, hclose;
        public CheckBox wifi, bus, taxi, info;
        public ImageButton mapButton, delButton;
        public View parentView;

        public UserFavoritesHolder(View view) {
            super(view);
            parentView = view;

            stationName = view.findViewById(R.id.rcview_userfavorites_stationName_item);
            hopen = view.findViewById(R.id.rcview_userfavoritesseestations_stationHOpen_item);
            hclose = view.findViewById(R.id.rcview_userfavoritesseestations_stationHclose_item);
            wifi = view.findViewById(R.id.rcview_userfavorites_checkBox_stations_wifi);
            info = view.findViewById(R.id.rcview_userfavoritescheckBox_stations_info);
            bus = view.findViewById(R.id.rcview_userfavoritescheckBox_stations_bus);
            taxi = view.findViewById(R.id.rcview_userfavoritescheckBox_stations_taxi);
            mapButton = view.findViewById(R.id.rcview_userfavoritesbutton_stations_map);
            delButton = view.findViewById(R.id.delfavButton);

            //BorrarFavorito

            delButton.setOnClickListener(del -> delFavorite(getAdapterPosition()));




            }
        }
    public void delFavorite(int position){
        //Creamos dialogo de alerta con opciones
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("¿Desea eliminar de favoritos?")
                .setTitle("ELIMINAR")
                .setPositiveButton("Si", (dialog, id) -> {
                    //Al pulsar en OK eliminamos vehiculo de la base de datos
                    final FavStationsDB db = Room.databaseBuilder(context, FavStationsDB.class, DATA_BASE_NAME)
                            .allowMainThreadQueries().build();
                    FavoriteStations station = stationsList.get(position);
                    db.favoriteStationsDAO().delFavStation(station);

                    stationsList.remove(position);
                    //Notificamos el cambio
                    notifyItemRemoved(position);
                })
                .setNegativeButton(("Cancelar"), (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
