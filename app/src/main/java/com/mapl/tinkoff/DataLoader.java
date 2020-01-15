package com.mapl.tinkoff;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataLoader {
    private static final String API_URL = "https://api.tinkoff.ru/v1/news";

    public static JSONObject getJSON() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder rawData = new StringBuilder(1024);

            String tempVariable;

            while ((tempVariable = reader.readLine()) != null) {
                rawData.append(tempVariable).append("\n");
            }

            reader.close();
            connection.disconnect();

            JSONObject jsonObject = new JSONObject(rawData.toString());

            if (jsonObject.getString("resultCode").equals("OK")) {
                return jsonObject;
            } else {
                return null;
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
