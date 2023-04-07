package com.example.transapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transapp.R;
import com.example.transapp.domain.Line;

import java.util.List;

public class ModLinesAdapter extends RecyclerView.Adapter<ModLinesAdapter.ModLinesHolder> {

    private List<Line> lineList;
    private Context context;

    public ModLinesAdapter(Context context, List<Line> lineList) {
        this.lineList = lineList;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public ModLinesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_logeg_mod_lines_item, parent, false);
        return new ModLinesAdapter.ModLinesHolder(view);
    }
    @Override
    public void onBindViewHolder(ModLinesHolder holder, int position) {
        holder.codeLine.setText(lineList.get(position).getCodeLine());
        holder.color.setText(lineList.get(position).getColor());

    }



    @Override
    public int getItemCount() {
        return lineList.size();
    }

    public class ModLinesHolder extends RecyclerView.ViewHolder {

        public TextView codeLine,color;
        public Button butStations;
        public View parentView;

        public ModLinesHolder(View view) {
            super(view);
            parentView = view;

            codeLine = view.findViewById(R.id.txtModLine_code);
            color = view.findViewById(R.id.txtModLine_color);

            //Listener boton estaciones de la linea
            butStations = view.findViewById(R.id.butModLines);
            butStations.setOnClickListener(view1 -> seeStations(getAdapterPosition()));
        }

        public void seeStations(int position){
//            Line line = lineList.get(position);
//            //Enviamos a la Activity y le pasamos el id de la linea
//            Intent intent = new Intent(contex, ACTIVITY QUE SEA.class);
//            intent.putExtra("id", line.getId());
//            context.startActivity(intent);

            //TODO Por terminar
        }


    }

}
