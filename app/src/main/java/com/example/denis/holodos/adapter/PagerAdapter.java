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

public class PagerAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    @Override
    public int getCount() {
        return(3);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MessageHistoryFragment();
            case 1:
                return new ShoppingListFragment();
            case 2:
                return new FridgeFragment();
            default:
                return new MessageHistoryFragment();

        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "История операций";
            case 1:
                return "Список покупок";
            case 2:
                return "Холодос";
            default:
                return  "История операций";
        }
    }

    public FragmentManager getFm() {
        return fm;
    }
}
