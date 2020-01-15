package com.mapl.tinkoff;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<CardInfo> arrayList;

    public RecyclerViewAdapter(ArrayList<CardInfo> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        long id = arrayList.get(position).id;
        String dateString = new SimpleDateFormat("MM/dd/yyyy")
                .format(new Date(arrayList.get(position).milliseconds));
        String text = arrayList.get(position).text;
        holder.textInfo.setText(text);
        holder.textDate.setText(dateString);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textInfo;
        TextView textDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textInfo = itemView.findViewById(R.id.textInfo);
            textDate = itemView.findViewById(R.id.textDate);
        }
    }
}
