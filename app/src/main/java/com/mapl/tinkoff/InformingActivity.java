package com.mapl.tinkoff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class InformingActivity extends AppCompatActivity {
    String id, date, text, content;
    TextView contentTextView, dateTextView, textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informing);
        initIntent();
        initView();
        LoadInfo loadInfo = new LoadInfo();
        loadInfo.execute();
    }

    private void initIntent() {
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        date = intent.getStringExtra("DATE");
        text = intent.getStringExtra("TEXT");
    }

    private void initView() {
        textView = findViewById(R.id.textView);
        contentTextView = findViewById(R.id.contentTextView);
        dateTextView = findViewById(R.id.dateTextView);
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadInfo extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            try {
                DataLoader dataLoader = new DataLoader("https://api.tinkoff.ru/v1/news_content?id=" + id);
                JSONObject jsonObject = dataLoader.getJSON();
                content = jsonObject.getJSONObject("payload").getString("content");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textView.setText(Html.fromHtml(text));
            contentTextView.setText(Html.fromHtml(content));
            dateTextView.setText(date);
        }
    }
}
