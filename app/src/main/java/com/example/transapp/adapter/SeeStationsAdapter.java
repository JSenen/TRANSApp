package com.example.transapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.transapp.R;
import com.example.transapp.domain.DataSingleton;
import com.example.transapp.domain.Stations;
import com.example.transapp.view.MapStationsView;

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
        public ImageButton mapButton, modStationButton, deleteStationButton;
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


            //Boton ver en mapa. Establecemos click listener y cogemos posicion
            //en el recycler para saber que estacion es
            mapButton = view.findViewById(R.id.rcview_button_stations_map);
            mapButton.setOnClickListener(view1 -> seeMapStation(getAdapterPosition()));

            //Boton modificar
            modStationButton = view.findViewById(R.id.rcview_button_stations_edit);

            //Boton eliminar estacion
            deleteStationButton = view.findViewById(R.id.rcview_button_stations_delete);
        }

        //Metodo boton ir a mapa localizacion estacion
        private void seeMapStation(int position) {
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
            context.startActivity(intent);
        }


    }
}
