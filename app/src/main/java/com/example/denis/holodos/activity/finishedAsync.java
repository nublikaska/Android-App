package com.example.denis.holodos.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.denis.holodos.modules.receipts.Receipt;

import java.util.List;
import java.util.Map;

/**
 * Created by Denis on 07.01.2018.
 */

public abstract class finishedAsync extends AppCompatActivity {

    private SharedPreferences preferences;
    private static final String APP_PREFERENCES = "APP_PREFERENCES";
    protected boolean isLogin;
    protected boolean isRegistration;
    protected boolean isReceipt;

    public void finishedAsyncTask(boolean isLogin, String login, String password) {
        this.isLogin = isLogin;
        if (isLogin) {
            preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("login", login);
            editor.putString("password", password);
            editor.apply();
        }
    }

    public void finishedAsyncTask(boolean isRegistration) {
        this.isRegistration = isRegistration;
    }

    public void finishedAsyncTask(Receipt receipt) {
        this.isReceipt = true;
    }

    public void finishedAsyncTask(List<Receipt> receipts) {
    }

    public void finishedAsyncTask() {}

    public void finishedAsyncTask(Map<String, Double> map) {};
}
