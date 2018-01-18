package com.example.denis.holodos.ParseTask;
import android.os.AsyncTask;

import com.example.denis.holodos.activity.finishedAsync;
import com.example.denis.holodos.modules.Group;
import com.example.denis.holodos.modules.JsonService.JsonService;
import com.example.denis.holodos.modules.User;
import com.example.denis.holodos.services.AuthorizationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Denis on 07.01.2018.
 */

public class ParseTaskLogin extends AsyncTask<String, Void, String[]> {

    private finishedAsync activity;
    private User user;
    private Group group;

    public ParseTaskLogin(finishedAsync activity) {
        this.activity = activity;
    }

    private AuthorizationService authorizationService = new AuthorizationService();

    @Override
    protected String[] doInBackground(String... Login_Password) {
        return new String[]{
                authorizationService.authenticationUser(Login_Password[0]),
                Login_Password[1]
        };
    }

    @Override
    protected void onPostExecute(String[] User_Password) {
        super.onPostExecute(User_Password);

        if (!User_Password[0].equals("")) {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            User user = gson.fromJson(User_Password[0], User.class);

            if (authorizationService.checkUser(user, user.getLogin(), User_Password[1])) {
                activity.finishedAsyncTask(true, user.getLogin(), user.getPassword());
            } else {
                activity.finishedAsyncTask(false, null, null);
            }
        } else {
            activity.finishedAsyncTask(false, null, null);
        }
    }
}
