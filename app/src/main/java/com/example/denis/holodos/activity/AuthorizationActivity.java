package com.example.denis.holodos.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.example.denis.holodos.ParseTask.ParseTaskLogin;
import com.example.denis.holodos.R;
import com.example.denis.holodos.modules.User;
import com.example.denis.holodos.services.AuthorizationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.net.HttpURLConnection;

/**
 * Created by Denis on 03.01.2018.
 */

public class AuthorizationActivity extends finishedAsync {

    private Button btnLogin;
    private Button btnSinUp;
    private EditText textLogin;
    private EditText textPassword;

    public static final int requestCodeForRegistrationActivity = 0;

    private ParseTaskLogin parseTaskLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        textLogin = (EditText) findViewById(R.id.login);
        textPassword = (EditText) findViewById(R.id.password);

        btnSinUp = (Button)findViewById(R.id.signUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseTaskLogin = new ParseTaskLogin(AuthorizationActivity.this);
                parseTaskLogin.execute(textLogin.getText().toString(), textPassword.getText().toString());
            }
        });

        btnSinUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthorizationActivity.this, RegistrationActivity.class);
                startActivityForResult(intent, requestCodeForRegistrationActivity);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case requestCodeForRegistrationActivity:
                if (resultCode == RESULT_OK) {
                    textLogin.setText(data.getStringExtra("login"));
                    textPassword.setText(data.getStringExtra("password"));
                }
        }
    }

    @Override
    public void finishedAsyncTask(boolean isLogin, String login, String password) {
        super.finishedAsyncTask(isLogin, login, password);
        if (isLogin) {
            Intent mainActivity = new Intent(AuthorizationActivity.this, MainActivity.class);
            startActivity(mainActivity);
        }  else {
            Toast.makeText(getApplicationContext(), "неверный логин или пароль", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        System.runFinalization();
        System.exit(0);
    }
}
