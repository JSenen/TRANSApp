package com.example.transapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.transapp.R;
import com.example.transapp.domain.Stations;

import java.util.List;

public class UserSeeStationsAdapter extends RecyclerView.Adapter<UserSeeStationsAdapter.UserSeeStationsHolder> {


    private Context context;
    private List<Stations> stationsList;


    public UserSeeStationsAdapter(Context context, List<Stations> stationsList) {
        this.context = context;
        this.stationsList = stationsList;
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
    }

    @Override
    public int getItemCount() {
        return stationsList.size();
    }

    public class UserSeeStationsHolder extends RecyclerView.ViewHolder {

        public TextView stationName, hopen, hclose;
        public CheckBox wifi, bus, taxi, info;
        public ImageButton mapButton;
        public View parentView;

        public UserSeeStationsHolder(View view) {
            super(view);
            parentView = view;

            stationName = view.findViewById(R.id.rcview_user_seestations_stationName_item);
            hopen = view.findViewById(R.id.rcview_user_seestations_stationHOpen_item);
            hclose = view.findViewById(R.id.rcview_user_seestations_stationHclose_item);
            wifi = view.findViewById(R.id.rcview_user_checkBox_stations_wifi);
            info = view.findViewById(R.id.rcview_user_checkBox_stations_info);
            bus = view.findViewById(R.id.rcview_user_checkBox_stations_bus);
            taxi = view.findViewById(R.id.rcview_user_checkBox_stations_taxi);
            mapButton = view.findViewById(R.id.rcview_user_button_stations_map);


        }
    }
}
