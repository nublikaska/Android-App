package com.example.denis.holodos.ParseTask;

import android.os.AsyncTask;

import com.example.denis.holodos.activity.finishedAsync;
import com.example.denis.holodos.modules.Group;
import com.example.denis.holodos.modules.User;
import com.example.denis.holodos.services.AuthorizationService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Denis on 16.01.2018.
 */

public class ParseTaskRegistration extends AsyncTask<String, Void, Boolean> {

    private finishedAsync activity;

    public ParseTaskRegistration(finishedAsync activity) {
        this.activity = activity;
    }

    private AuthorizationService authorizationService = new AuthorizationService();

    @Override
    protected Boolean doInBackground(String... Login_Password) {
        return authorizationService.addUser(Login_Password[0], Login_Password[1]);
    }

    @Override
    protected void onPostExecute(Boolean IsRegistration) {
        super.onPostExecute(IsRegistration);
        activity.finishedAsyncTask(IsRegistration);
    }
}
