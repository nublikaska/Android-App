package com.example.denis.holodos.adapter.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.example.denis.holodos.ParseTask.ParseTaskAllReceipt;
import com.example.denis.holodos.R;
import com.example.denis.holodos.activity.MainActivity;
import com.example.denis.holodos.modules.receipts.Receipt;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Denis on 10.12.2017.
 */

public class MessageHistoryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static List<Receipt> operators = new ArrayList<>();
    private static MyAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean isSortMagazine = false;
    private boolean isSortItems = false;

     FloatingActionButton myFab;
    FloatingActionButton fabSortMagazine;
    FloatingActionButton fabSortItems;
    public MessageHistoryFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface onSomeEventListener {
        public void refreshAllReceipts();

        public void refreshBySortMagazine();

        public void refreshBySortItems();
    }

    private onSomeEventListener someEventListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            someEventListener = (onSomeEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_history_fragment, container, false);

        myFab = (FloatingActionButton) view.findViewById(R.id.fab);
        fabSortMagazine = (FloatingActionButton) view.findViewById(R.id.fab_sort_magazine);
        fabSortItems = (FloatingActionButton) view.findViewById(R.id.fab_sort_items);

        myFab.setColorNormal(R.color.colorPrimaryDark);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fabSortItems.setVisibility(View.VISIBLE);
                fabSortMagazine.setVisibility(View.VISIBLE);
            }
        });

        fabSortItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFab.setColorNormal(R.color.colorAccent);
                fabSortItems.setColorNormal(R.color.colorAccent);
                isSortItems = true;
            }
        });

        fabSortMagazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFab.setColorNormal(R.color.colorAccent);
                fabSortMagazine.setColorNormal(R.color.colorAccent);
                isSortMagazine = true;
                onRefresh();
            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        operators.clear();
        someEventListener.refreshAllReceipts();

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        adapter = new MyAdapter(operators);
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>0) {
                    myFab.hide();
                    fabSortItems.setVisibility(View.INVISIBLE);
                    fabSortMagazine.setVisibility(View.INVISIBLE);
                } else if (dy < 0) {
                    myFab.show();
                }
            }
        });

        return view;
    }

    public static void addOperator(Receipt str) {
        operators.add(0, str);
        adapter.notifyDataSetChanged();
    }

    public static void addAllOperators(List<Receipt> receipts) {
        operators.clear();
        for (int i=0; i<receipts.size(); i++) {
            operators.add(0, receipts.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                operators.clear();

                if (isSortMagazine) {
                    someEventListener.refreshBySortMagazine();
                }
                else if (isSortItems) {
                    someEventListener.refreshBySortItems();
                } else {
                    someEventListener.refreshAllReceipts();
                }
                // Отменяем анимацию обновления
                fabSortItems.setVisibility(View.INVISIBLE);
                fabSortMagazine.setVisibility(View.INVISIBLE);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    public onSomeEventListener getSomeEventListener() {
        return someEventListener;
    }

}
