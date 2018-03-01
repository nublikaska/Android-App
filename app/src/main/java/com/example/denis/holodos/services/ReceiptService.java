package com.example.denis.holodos.services;

import com.example.denis.holodos.App;
import com.example.denis.holodos.modules.receipts.Receipt;
import com.example.denis.holodos.modules.receipts.Ticket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Denis on 16.01.2018.
 */

public class ReceiptService {

    private HttpURLConnection urlConnection;
    private BufferedReader reader;
    private StringBuilder resultJson;
    private String resultJsonStr;
    private URL url;
    JSONObject jsonObject;


    public String addReceipt(String login, String fiscalDriveId, String fiscalDocumentNumber, String fiscalId) {
        try {
            String ACCEPT = "application/json";
            String CONTENT_TYPE = "application/json";

            url = new URL(App.BaseUrl + "/service/api/users/" + login + "/receipts");

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Accept", ACCEPT);
            urlConnection.setRequestProperty("Content-Type", CONTENT_TYPE);

            urlConnection.setDoOutput(true);
            jsonObject = new JSONObject();
            jsonObject.put("fiscalDriveId", fiscalDriveId);
            jsonObject.put("fiscalDocumentNumber", fiscalDocumentNumber);
            jsonObject.put("fiscalId", fiscalId);

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

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "error";
        } catch (JSONException e) {
            e.printStackTrace();
            return "error";
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            resultJsonStr = resultJson.toString();
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Receipt receipt = gson.fromJson(resultJsonStr, Receipt.class);
        } catch (NullPointerException e) {
            return "error";
        }



        return resultJsonStr;
    }

    public String getAllReceipts(String login) {
        try {
            URL url = new URL(App.BaseUrl + "/service/api/users/" + login + "/receipts");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJsonStr = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return resultJsonStr;
    }

    public String getByOrganization(String login) {
        String ACCEPT = "application/json";
        String CONTENT_TYPE = "application/json";
        try {
            URL url = new URL(App.BaseUrl + "/service/api/users/" + login + "/statistics/organization" + "?from=2017-12-03&to=2018-03-02");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept", ACCEPT);
            urlConnection.setRequestProperty("Content-Type", CONTENT_TYPE);
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJsonStr = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return resultJsonStr;
    }
}
