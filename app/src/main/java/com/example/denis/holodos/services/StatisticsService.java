package com.example.denis.holodos.services;

import com.example.denis.holodos.App;
import com.example.denis.holodos.modules.receipts.Receipt;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * Created by Аменд Мария on 02.03.2018.
 */

public class StatisticsService {

    private HttpURLConnection urlConnection;
    private BufferedReader reader;
    private StringBuilder resultJson;
    private String resultJsonStr;
    private URL url;
    private JSONObject jsonObject;

    public HashMap<String, String> getByPeriodAndGriupByOrganization(String login, String from, String to) throws IOException {
        try {
            String ACCEPT = "application/json";

            url = new URL(App.BaseUrl + "/service/api/users/"
                    + login
                    + "/statistics/organization?from="
                    + from
                    + "&to="
                    + to);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept", ACCEPT);

            urlConnection.setDoOutput(true);

            OutputStreamWriter wr= new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(jsonObject.toString());

            wr.flush();
            wr.close();

            reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            resultJson = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                resultJson.append(inputLine);
            }
            reader.close();

        } catch (IOException e) {
            throw e;
        }

        try {
            resultJsonStr = resultJson.toString();
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            HashMap<String, String> map = new HashMap<>();
            return gson.fromJson(resultJsonStr, map.getClass());
        } catch (NullPointerException e) {
            throw e;
        }
    }
}
