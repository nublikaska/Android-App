package com.example.denis.holodos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.denis.holodos.ParseTask.ParseTaskRegistration;
import com.example.denis.holodos.R;

/**
 * Created by Denis on 16.01.2018.
 */

public class RegistrationActivity extends finishedAsync {

    private Button btnReg;
    private EditText login;
    private EditText password;
    private ParseTaskRegistration parseTaskRegistration;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnReg = (Button) findViewById(R.id.btnReg);
        login = (EditText)findViewById(R.id.login);
        password = (EditText)findViewById(R.id.password);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseTaskRegistration = new ParseTaskRegistration(RegistrationActivity.this);
                parseTaskRegistration.execute(login.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public void finishedAsyncTask(boolean isRegistration) {
        super.finishedAsyncTask(isRegistration);
        if (isRegistration) {
            Intent intent = new Intent();
            intent.putExtra("login", login.getText().toString());
            intent.putExtra("password", password.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
