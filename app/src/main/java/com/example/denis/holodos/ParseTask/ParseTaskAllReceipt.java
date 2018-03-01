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
import java.util.List;

import javax.security.auth.callback.Callback;

/**
 * Created by Denis on 01.03.2018.
 */

public class ParseTaskAllReceipt extends AsyncTask<String, Void, String> {
    private finishedAsync activity;

    public ParseTaskAllReceipt(finishedAsync activity) {
        this.activity = activity;
    }


    private ReceiptService receiptService = new ReceiptService();

    @Override
    protected String doInBackground(String... login/*Login*/) {
        return receiptService.getAllReceipts(login[0]);
    }

    @Override
    protected void onPostExecute(String JsonDocumentList) {

        //JsonDocumentClass = JsonDocumentClass.replaceAll("\\,\\Warchived\\W\\:false\\,\\WcreationDateTime\\W{2}\\[(.*?)\\]\\,\\WarchiveDateTime\\W\\:null", "");
        //JsonDocumentClass = JsonDocumentClass.replaceAll("\\,\\WdateTime\\W{2}\\[(.*?)\\]", "");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<Receipt> receipts = new ArrayList<Receipt>();

        try {
            JSONArray jsonArray = new JSONArray(JsonDocumentList);
            List<String> list = new ArrayList<String>();
            for (int i=0; i<jsonArray.length(); i++) {
                receipts.add(gson.fromJson((jsonArray.getString(i)), Receipt.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

            activity.finishedAsyncTask(receipts);
    }
}
