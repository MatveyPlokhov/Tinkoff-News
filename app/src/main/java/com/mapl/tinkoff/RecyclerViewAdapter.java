package com.mapl.tinkoff;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        long milliseconds = arrayList.get(position).milliseconds;
        String text = arrayList.get(position).text;

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textInfo = itemView.findViewById(R.id.textInfo);
        }
    }
}
