package com.example.denis.holodos.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.denis.holodos.R;
import com.example.denis.holodos.implementsService.Authentication;

/**
 * Created by Denis on 03.01.2018.
 */

public class AuthorizationActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText textLogin;
    private EditText textPassword;

    private SharedPreferences preferences;
    public static final String APP_PREFERENCES = "APP_PREFERENCES";

    private Authentication authentication = new Authentication();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        textLogin = (EditText) findViewById(R.id.login);
        textPassword = (EditText) findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(authentication.Login(textLogin.toString(), textPassword.toString())) {
                    preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("login", textLogin.toString());
                    editor.putString("password", textPassword.toString());
                    editor.apply();

                    Intent intent = new Intent(AuthorizationActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "неверный логин или пароль", Toast.LENGTH_SHORT);
                }
            }
        });
    }


}
