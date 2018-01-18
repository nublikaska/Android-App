package com.example.denis.holodos.services;

import com.example.denis.holodos.modules.receipts.Ticket;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
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
    private URL url;
    JSONObject jsonObject;


    public String addReceipt(String login, String fiscalDriveId, String fiscalDocumentNumber, String fiscalId) {
        try {
            String ACCEPT = "application/json";
            String CONTENT_TYPE = "application/json";

            url = new URL("http://192.168.42.62:8080/service/api/users/" + login + "/receipts");

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
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson.toString();
    }
}
