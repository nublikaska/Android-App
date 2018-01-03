package com.example.denis.holodos.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.denis.holodos.R;
import com.example.denis.holodos.adapter.MyAdapter;
import com.example.denis.holodos.beans.authorization.Authentication;

import javax.inject.Inject;
import javax.validation.Constraint;
import javax.ws.rs.core.Context;

public class MainActivity extends AppCompatActivity {

    private static int requestCodeMainActivity = 0;
    private SharedPreferences preferences;

    @Inject
    private Authentication authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(AuthorizationActivity.APP_PREFERENCES, android.content.Context.MODE_PRIVATE);
        if (preferences.contains("login") && (preferences.contains("password"))) {
           if (!(authentication.Login(preferences.getString("login", ""), preferences.getString("password", "")))) {
               Intent intent = new Intent(MainActivity.this, AuthorizationActivity.class);
           }
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestCodeMainActivity) {
            if (resultCode == RESULT_OK) {
                String thiefname = data.getStringExtra("CameraActivity");
                Toast toast = Toast.makeText(getApplicationContext(),
                        thiefname, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
