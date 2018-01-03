package com.example.denis.holodos.adapter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.denis.holodos.R;

/**
 * Created by Denis on 10.12.2017.
 */

public class MessageHistoryFragment extends Fragment {

    public MessageHistoryFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_history_fragment, container, false);
        return view;
    }
}
