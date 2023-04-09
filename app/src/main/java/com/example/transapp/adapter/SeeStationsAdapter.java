package com.example.transapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.transapp.R;
import com.example.transapp.domain.Line;
import com.example.transapp.domain.Stations;

import java.util.List;

public class SeeStationsAdapter extends RecyclerView.Adapter<SeeStationsAdapter.SeeStationsHolder> {

    private List<Stations> stationsList;
    private Context context;
    public SeeStationsAdapter(Context context, List<Stations> stationslist) {
        this.context = context;
        this.stationsList = stationslist;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public SeeStationsAdapter.SeeStationsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_see_stationes_item, parent, false);
        return new SeeStationsAdapter.SeeStationsHolder(view);
    }

    @Override
    public void onBindViewHolder(SeeStationsAdapter.SeeStationsHolder holder, int position) {
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

    public class SeeStationsHolder extends RecyclerView.ViewHolder {

        public TextView stationName,hopen,hclose;
        public CheckBox wifi,bus,taxi,info;
        public View parentView;

        public SeeStationsHolder(View view) {
            super(view);
            parentView = view;

            stationName = view.findViewById(R.id.rcview_seestations_stationName_item);
            hopen = view.findViewById(R.id.rcview_seestations_stationHOpen_item);
            hclose = view.findViewById(R.id.rcview_seestations_stationHclose_item);
            wifi = view.findViewById(R.id.rcview_checkBox_stations_wifi);
            info = view.findViewById(R.id.rcview_checkBox_stations_info);
            bus = view.findViewById(R.id.rcview_checkBox_stations_bus);
            taxi = view.findViewById(R.id.rcview_checkBox_stations_taxi);
        }


    }
}
