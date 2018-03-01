package com.example.denis.holodos.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.denis.holodos.R;
import com.example.denis.holodos.adapter.ReceiptAdapter;
import com.example.denis.holodos.adapter.fragments.MessageHistoryFragment;
import com.example.denis.holodos.modules.receipts.Receipt;

/**
 * Created by Denis on 01.03.2018.
 */

public class ReceiptActivity extends AppCompatActivity {

    private ListView itemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        itemList = (ListView) findViewById(R.id.itemsList);
        Receipt receipt = (Receipt) getIntent().getSerializableExtra("Receipt");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Чек на сумму " + String.valueOf(receipt.getTotalSum()/100) + " руб.");
        ReceiptAdapter receiptAdapter = new ReceiptAdapter(this, R.layout.items_item, receipt.getItems());
        itemList.setAdapter(receiptAdapter);
    }
}
