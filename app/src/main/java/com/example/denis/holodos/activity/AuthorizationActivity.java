package com.example.denis.holodos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.denis.holodos.R;
import com.example.denis.holodos.modules.User;
import com.example.denis.holodos.services.AuthorizationService;

import java.io.IOException;

import javax.ejb.EJB;

/**
 * Created by Denis on 03.01.2018.
 */

public class AuthorizationActivity extends Activity {

    private Button btnLogin;
    private EditText textLogin;
    private EditText textPassword;

    private User user;

    @EJB
    AuthorizationService authorizationService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_authorization);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        textLogin = (EditText) findViewById(R.id.login);
        textPassword = (EditText) findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user = authorizationService.authenticationUser(textLogin.toString()) {

                }
                authorizationService.checkUser(textLogin.toString(), textPassword.toString());
            }
        });
    }
}
