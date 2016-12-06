package com.tisen.note.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;

/**
 * Created by tisen on 2016/10/24.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String>tabs;
    private ArrayList<Fragment>fragments;
    private Context context;


    public ViewPagerAdapter(FragmentManager fm, ArrayList<String> tabs, ArrayList<Fragment> fragments, Context context) {
        super(fm);
        this.tabs = tabs;
        this.fragments = fragments;
        this.context = context;
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
