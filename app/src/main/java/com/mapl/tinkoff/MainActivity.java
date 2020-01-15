package com.mapl.tinkoff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<CardInfo> arrayList = new ArrayList();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        LoadInfo loadInfo = new LoadInfo();
        loadInfo.execute();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadInfo extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            JSONObject jsonObject = DataLoader.getJSON();
            if (jsonObject != null)
                arrayList = getJSONArray(jsonObject);
            else
                arrayList = new ArrayList<>();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(arrayList);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }

    private ArrayList<CardInfo> getJSONArray(JSONObject jsonObject) {
        ArrayList<CardInfo> arrayList = new ArrayList();

        arrayList.add(new CardInfo(100, 100, "текст"));

        return arrayList;
    }
}
