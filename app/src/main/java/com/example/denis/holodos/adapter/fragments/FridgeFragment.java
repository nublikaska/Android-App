package com.example.denis.holodos.adapter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.denis.holodos.R;
import com.example.denis.holodos.modules.receipts.Receipt;

/**
 * Created by Denis on 10.12.2017.
 */

public class FridgeFragment extends Fragment {


    public FridgeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fridge_fragment, container, false);

        return view;
    }

    void addReceipt(Receipt receipt) {

    }
}
