package com.example.transapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.transapp.R;
import com.example.transapp.contract.SeeLinesContract;
import com.example.transapp.domain.Line;
import com.example.transapp.view.UserSeeStationsView;

import java.util.List;

public class SeeLinesAdapter extends RecyclerView.Adapter<SeeLinesAdapter.SeeLinesHolder>{

    private List<Line> linesList;
    private Context context;
    public SeeLinesAdapter(Context context, List<Line> lines) {
        this.context = context;
        this.linesList = lines;


    }

    public Context getContext() {
        return context;
    }

    @Override
    public SeeLinesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_see_lines_item, parent, false);
        return new SeeLinesHolder(view);
    }

    @Override
    public void onBindViewHolder(SeeLinesHolder holder, int position) {
        holder.codeLine.setText(linesList.get(position).getCodeLine());
        holder.color.setText(linesList.get(position).getColor());
        holder.hopen.setText(linesList.get(position).getFirstTime());
        holder.hclose.setText(linesList.get(position).getLastTime());


    }
    @Override
    public int getItemCount() {
        return linesList.size();
    }


    public class SeeLinesHolder extends RecyclerView.ViewHolder {

        public TextView codeLine, color, hopen, hclose;
        public ImageButton butSeeStations;
        public View parentView;

        public SeeLinesHolder(View view) {
            super(view);
            parentView = view;

            codeLine = view.findViewById(R.id.txt_user_codeline);
            color = view.findViewById(R.id.txt_user_colorline);
            hopen = view.findViewById(R.id.txt_user_hopen);
            hclose = view.findViewById(R.id.txt_user_hclose);
            butSeeStations = view.findViewById(R.id.butUserSeeStations);

            //Boton ver estaciones
            butSeeStations.setOnClickListener(see -> seeStationsUser(getAdapterPosition()));

        }

        }

        public void seeStationsUser(int position){
        //Enviamos a Activity estaciones. Recuperamos idLinea
            Line line = linesList.get(position);
            Intent intent = new Intent(context, UserSeeStationsView.class);
            intent.putExtra("idLine",line.getId());
            context.startActivity(intent);
        }

}
