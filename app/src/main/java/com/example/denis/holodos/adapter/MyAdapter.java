package com.example.denis.holodos.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.denis.holodos.adapter.fragments.FridgeFragment;
import com.example.denis.holodos.adapter.fragments.MessageHistoryFragment;
import com.example.denis.holodos.adapter.fragments.ShoppingListFragment;

/**
 * Created by Denis on 09.12.2017.
 */

public class MyAdapter extends FragmentPagerAdapter {

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return(3);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new FridgeFragment();
            case 2:
                return new MessageHistoryFragment();
            default:
                return new ShoppingListFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1:
                return "Холодос";
            case 2:
                return "История операций";
            default:
                return "Список покупок";
        }
    }
}
