package com.example.denis.holodos.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.denis.holodos.ParseTask.ParseTaskAllReceipt;
import com.example.denis.holodos.ParseTask.ParseTaskLogin;
import com.example.denis.holodos.ParseTask.ParseTaskReceipt;
import com.example.denis.holodos.ParseTask.ParseTaskSortByMagazine;
import com.example.denis.holodos.R;
import com.example.denis.holodos.adapter.PagerAdapter;
import com.example.denis.holodos.adapter.fragments.MessageHistoryFragment;
import com.example.denis.holodos.modules.receipts.Receipt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends finishedAsync implements MessageHistoryFragment.onSomeEventListener {

    public static final int requestCodeMainActivity = 1;
    private SharedPreferences preferences;
    private ParseTaskLogin parseTaskLogin;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

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

//        pager = (ViewPager) findViewById(R.id.pager);
//        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
//        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
//        tabs.setupWithViewPager(pager);

        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
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
//                Receipt receipt = (Receipt) data.getSerializableExtra("receipt");
//                break;
                    String receipt = data.getStringExtra("receiptStr");
                    String[] str = receipt.split("&");
                            Map m = new HashMap<String, String>();

                            for (String s : str) {
                                String[] split = s.split("=");
                                switch (s.split("=")[0]) {
                                    case "fn":
                                        m.put("fn", split[1]);
                                        break;
                                    case "i":
                                        m.put("i", split[1]);
                                        break;
                                    case "fp":
                                        m.put("fp", split[1]);
                                        break;
                                }
                            }
                            SharedPreferences preferences = getSharedPreferences(MainActivity.APP_PREFERENCES, android.content.Context.MODE_PRIVATE);
                            String login = preferences.getString("login", "");
                            ParseTaskReceipt parseTaskReceipt = new ParseTaskReceipt(MainActivity.this);
                            parseTaskReceipt.execute(login, (String) m.get("fn"), (String) m.get("i"), (String) m.get("fp"));
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

    @Override
    public void finishedAsyncTask(List<Receipt> receipts) {
        super.finishedAsyncTask(receipts);
        MessageHistoryFragment.addAllOperators(receipts);
    }

    @Override
    public void finishedAsyncTask(Receipt receipt) {
        super.finishedAsyncTask(receipt);
        refreshAllReceipts();
    }

    @Override
    public void finishedAsyncTask(HashMap<String, Double> map) {
        super.finishedAsyncTask(map);
        List<Receipt> receipts = new ArrayList<>();

        System.out.println("dsdddddddddddddddddddddddddddddddddd");
        for (Map.Entry<String, Double> next : map.entrySet()) {
            Receipt receipt = new Receipt();
            receipt.setTotalSum(next.getValue());
            receipt.setUser(next.getKey());
            receipts.add(receipt);
        }

        for (Receipt r : receipts) {
            System.out.println(r.getUser() + " " + r.getTotalSum());
        }

        MessageHistoryFragment.addAllOperators(receipts);
    }

    @Override
    public void refreshAllReceipts() {
        ParseTaskAllReceipt parseTaskAllReceipt = new ParseTaskAllReceipt(MainActivity.this);
        parseTaskAllReceipt.execute(getSharedPreferences(APP_PREFERENCES, android.content.Context.MODE_PRIVATE).getString("login", ""));
    }

    @Override
    public void refreshBySortMagazine() {
        ParseTaskSortByMagazine parseTaskSortByMagazine = new ParseTaskSortByMagazine(MainActivity.this);
        parseTaskSortByMagazine.execute(getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getString("login", "") );
    }

    @Override
    public void refreshBySortItems() {

    }
}
