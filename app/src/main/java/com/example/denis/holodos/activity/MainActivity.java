package com.example.denis.holodos.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.denis.holodos.ParseTask.ParseTaskLogin;
import com.example.denis.holodos.R;
import com.example.denis.holodos.adapter.MyAdapter;
import com.example.denis.holodos.modules.receipts.Receipt;

public class MainActivity extends finishedAsync {

    public static final int requestCodeMainActivity = 1;
    private SharedPreferences preferences;
    private ParseTaskLogin parseTaskLogin;

    public static final String APP_PREFERENCES = "APP_PREFERENCES";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(APP_PREFERENCES, android.content.Context.MODE_PRIVATE);
        if (preferences.contains("login") && (preferences.contains("password"))) {
            parseTaskLogin = new ParseTaskLogin(MainActivity.this);
            parseTaskLogin.execute(preferences.getString("login", ""), preferences.getString("password", ""));
        } else {
            Intent authorizationActivity = new Intent(MainActivity.this, AuthorizationActivity.class);
            startActivity(authorizationActivity);
        }

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_camera:
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivityForResult(intent, requestCodeMainActivity);
                return true;
            case R.id.exit:
                Intent authorizationActivity = new Intent(MainActivity.this, AuthorizationActivity.class);
                preferences.edit().clear().commit();
                startActivity(authorizationActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case requestCodeMainActivity:
                if (resultCode == RESULT_OK) {
                Receipt receipt = (Receipt) data.getSerializableExtra("receipt");

                break;
            }
            break;
        }
    }

    @Override
    public void finishedAsyncTask(boolean isLogin, String login, String password) {
        super.finishedAsyncTask(isLogin, login, password);
        if (!isLogin) {
            Intent authorizationActivity = new Intent(MainActivity.this, AuthorizationActivity.class);
            startActivity(authorizationActivity);
        }
    }
}
