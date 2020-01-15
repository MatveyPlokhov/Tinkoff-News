package com.mapl.tinkoff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements Postman {
    private ArrayList<CardInfo> arrayList = new ArrayList();
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        swipeContainer = findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadInfo loadInfo = new LoadInfo();
                loadInfo.execute();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadInfo extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            DataLoader dataLoader = new DataLoader("https://api.tinkoff.ru/v1/news");
            JSONObject jsonObject = dataLoader.getJSON();
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
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(arrayList, MainActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(recyclerViewAdapter);
            swipeContainer.setRefreshing(false);
        }
    }

    private ArrayList<CardInfo> getJSONArray(JSONObject jsonObject) {
        ArrayList<CardInfo> arrayList = new ArrayList();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("payload");

            for (int i = 0; i < jsonArray.length(); i++) {
                arrayList.add(new CardInfo(
                        jsonArray.getJSONObject(i).getInt("id"),
                        jsonArray.getJSONObject(i).getJSONObject("publicationDate").getLong("milliseconds"),
                        jsonArray.getJSONObject(i).getString("text")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Collections.sort(arrayList, new Comparator<CardInfo>() {
            @Override
            public int compare(CardInfo o1, CardInfo o2) {
                return o1.getMilliseconds() > o2.getMilliseconds() ? -1 : 1;
            }
        });

        return arrayList;
    }

    @Override
    public void getInfo(String id, String date, String text) {
        Intent intent = new Intent(MainActivity.this, InformingActivity.class);
        intent.putExtra("ID", id);
        intent.putExtra("DATE", date);
        intent.putExtra("TEXT", text);
        startActivity(intent);
    }
}
