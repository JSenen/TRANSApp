package com.example.transapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.transapp.R;
import com.example.transapp.contract.SeeLinesContract;
import com.example.transapp.domain.Line;

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


    }
    @Override
    public int getItemCount() {
        return linesList.size();
    }


    public class SeeLinesHolder extends RecyclerView.ViewHolder {

        public TextView codeLine;
        public View parentView;

        public SeeLinesHolder(View view) {
            super(view);
            parentView = view;

            codeLine = view.findViewById(R.id.rcview_seeLines_item_line);
        }


        }

}
