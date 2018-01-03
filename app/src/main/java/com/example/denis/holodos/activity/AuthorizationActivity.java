package com.example.denis.holodos.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.denis.holodos.R;
import com.example.denis.holodos.beans.authorization.Authentication;
import com.example.denis.holodos.modules.User;
import com.example.denis.holodos.services.AuthorizationService;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.constraints.Null;

/**
 * Created by Denis on 03.01.2018.
 */

public class AuthorizationActivity extends Activity {

    private Button btnLogin;
    private EditText textLogin;
    private EditText textPassword;

    private SharedPreferences preferences;
    public static final String APP_PREFERENCES = "APP_PREFERENCES";

    @Inject
    private Authentication authentication;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable final PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
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
