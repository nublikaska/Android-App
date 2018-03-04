package com.example.denis.holodos.ParseTask;

import android.os.AsyncTask;

import com.example.denis.holodos.activity.finishedAsync;
import com.example.denis.holodos.modules.receipts.Receipt;
import com.example.denis.holodos.services.ReceiptService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Denis on 02.03.2018.
 */

public class ParseTaskSortByMagazine extends AsyncTask<String, Void, String> {
    private finishedAsync activity;

    public ParseTaskSortByMagazine(finishedAsync activity) {
        this.activity = activity;
    }


    private ReceiptService receiptService = new ReceiptService();

    @Override
    protected String doInBackground(String... login/*Login*/) {
        return receiptService.getByOrganization(login[0]);
    }

    @Override
    protected void onPostExecute(String JsonDocumentMap) {

        //JsonDocumentClass = JsonDocumentClass.replaceAll("\\,\\Warchived\\W\\:false\\,\\WcreationDateTime\\W{2}\\[(.*?)\\]\\,\\WarchiveDateTime\\W\\:null", "");
        //JsonDocumentClass = JsonDocumentClass.replaceAll("\\,\\WdateTime\\W{2}\\[(.*?)\\]", "");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        HashMap<String, Double> map = new HashMap<>();
        map =  gson.fromJson(JsonDocumentMap, map.getClass());
        for (Map.Entry<String, Double> next :map.entrySet()) {
            System.out.println(next.getValue() + " " + next.getValue());
        }

        activity.finishedAsyncTask(map);
    }
}
