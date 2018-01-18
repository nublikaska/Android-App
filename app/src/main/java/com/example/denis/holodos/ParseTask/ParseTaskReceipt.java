package com.example.denis.holodos.ParseTask;

import android.os.AsyncTask;

import com.example.denis.holodos.activity.finishedAsync;
import com.example.denis.holodos.modules.Group;
import com.example.denis.holodos.modules.JsonService.JsonService;
import com.example.denis.holodos.modules.User;
import com.example.denis.holodos.modules.receipts.Document;
import com.example.denis.holodos.modules.receipts.Receipt;
import com.example.denis.holodos.services.AuthorizationService;
import com.example.denis.holodos.services.ReceiptService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Denis on 16.01.2018.
 */

public class ParseTaskReceipt extends AsyncTask<String, Void, String> {

    private finishedAsync activity;

    public ParseTaskReceipt(finishedAsync activity) {
        this.activity = activity;
    }

    private ReceiptService receiptService = new ReceiptService();

    @Override
    protected String doInBackground(String... LFFF/*Login, fiscalDriveId, fiscalDocumentNumber, fiscalId*/) {
        return receiptService.addReceipt(LFFF[0], LFFF[1], LFFF[2], LFFF[3]);
    }

    @Override
    protected void onPostExecute(String JsonDocumentClass) {

        //JsonDocumentClass = JsonDocumentClass.replaceAll("\\,\\Warchived\\W\\:false\\,\\WcreationDateTime\\W{2}\\[(.*?)\\]\\,\\WarchiveDateTime\\W\\:null", "");
        //JsonDocumentClass = JsonDocumentClass.replaceAll("\\,\\WdateTime\\W{2}\\[(.*?)\\]", "");

        Receipt receipt;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        receipt = gson.fromJson(JsonDocumentClass, Receipt.class);

        activity.finishedAsyncTask(receipt);
    }


}
