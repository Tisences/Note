package com.tisen.note.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.tisen.note.R;
import com.tisen.note.adapter.ViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by tisen on 2016/10/24.
 */
public class HomeFragment extends Fragment {
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    private ArrayList<Fragment>fragments = new ArrayList<>();
    private ArrayList<String>tabs = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);

        findViewAndSetListener();

        return view;
    }

    private void findViewAndSetListener() {
        tabLayout = (TabLayout) view.findViewById(R.id.home_fragment_tab);
        viewPager = (ViewPager) view.findViewById(R.id.home_fragment_viewPager);
        tabs.add("beauty");
        tabs.add("gif");
        tabs.add("funny");

        fragments.add(new BeautyFragment());
        fragments.add(new GifFragment());
        fragments.add(new FunnyFragment());

        adapter = new ViewPagerAdapter(getChildFragmentManager(),tabs,fragments,getContext());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

}
