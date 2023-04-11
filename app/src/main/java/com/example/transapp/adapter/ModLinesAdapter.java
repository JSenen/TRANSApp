package com.example.transapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transapp.R;
import com.example.transapp.domain.DataSingleton;
import com.example.transapp.domain.Line;
import com.example.transapp.domain.LineDataSingleton;
import com.example.transapp.domain.Stations;
import com.example.transapp.presenter.DeleteLinePresenter;
import com.example.transapp.view.EditLineView;
import com.example.transapp.view.EditStationView;
import com.example.transapp.view.SeeStationesActivityView;

import java.util.List;

public class ModLinesAdapter extends RecyclerView.Adapter<ModLinesAdapter.ModLinesHolder> {

    private List<Line> lineList;
    private Context context;
    private DeleteLinePresenter presenter;
    private String token;

    public ModLinesAdapter(Context context, List<Line> lineList, String token) {
        this.lineList = lineList;
        this.context = context;
        this.token = token;
        this.presenter = new DeleteLinePresenter(this);
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
        holder.hopen.setText(lineList.get(position).getFirstTime());
        holder.hclose.setText(lineList.get(position).getLastTime());

    }



    @Override
    public int getItemCount() {
        return lineList.size();
    }

    public class ModLinesHolder extends RecyclerView.ViewHolder {

        public TextView codeLine,color,hopen,hclose;
        public ImageButton butStations, butDeleteLine, butEditLine;
        public View parentView;

        public ModLinesHolder(View view) {
            super(view);
            parentView = view;

            codeLine = view.findViewById(R.id.txtModLine_code);
            color = view.findViewById(R.id.txtModLine_color);
            hopen = view.findViewById(R.id.textView_lines_hopen);
            hclose = view.findViewById(R.id.textView_lines_hclose);

            //Listener boton estaciones de la linea
            butStations = view.findViewById(R.id.butSeeStationsLine);
            butStations.setOnClickListener(view1 -> seeStations(getAdapterPosition()));

            //Borrar linea
            butDeleteLine = view.findViewById(R.id.butDeleteLine);
            butDeleteLine.setOnClickListener(del -> deleteLine(getAdapterPosition()));

            //Editar Linea
            butEditLine = view.findViewById(R.id.butEditLine);
            butEditLine.setOnClickListener(edt -> editLine(getAdapterPosition()));
        }

        public void seeStations(int position){
            Line line = lineList.get(position);
            //Enviamos a la Activity y le pasamos el id de la linea
            Intent intent = new Intent(context, SeeStationesActivityView.class);
            intent.putExtra("idLine", line.getId());
            context.startActivity(intent);

        }

        public void deleteLine(int position){
            //Recuperamos posicion
            Line lines = lineList.get(position);

            String lineCode = lines.getCodeLine();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("¿Quiere eliminar "+lineCode+" ?")
                    .setTitle("Eliminar Linea")
                    .setPositiveButton("si", (dialog, id) -> {
                        Line line = lineList.get(position);
                        presenter.deleteLine(token,line.getId());

                        lineList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton("no", (dialog, id) -> { });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        public void editLine(int position){

            Line line = lineList.get(position);

            //Recuperamos datos a pasar a la nueva Activity para modificar
            LineDataSingleton linedataSingleton = LineDataSingleton.getInstance();
            linedataSingleton.setId(line.getId());
            linedataSingleton.setCodeLine(line.getCodeLine());
            linedataSingleton.setFirstTime(line.getFirstTime());
            linedataSingleton.setLastTime(line.getLastTime());
            linedataSingleton.setStopTime(line.getStopTime());
            linedataSingleton.setColor(line.getColor());

            //Pasamos a la activity modificar
            Intent intent = new Intent(context, EditLineView.class);
            context.startActivity(intent);

        }


    }

}
