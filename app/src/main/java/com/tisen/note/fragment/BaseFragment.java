package com.tisen.note.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tisen.note.R;
import com.tisen.note.model.Beauty;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by tisen on 2016/10/24.
 */
public abstract class BaseFragment extends Fragment {
    protected View view;
    protected Activity activity;
    protected SwipeRefreshLayout refreshLayout;
    protected ListView listView;
    protected long time;
    protected boolean isPrepared;
    protected boolean isVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, int layoutId) {
        view = inflater.inflate(layoutId, container, false);
        findViewAndSetListener();
        refreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        lazyLoad();
    }

    protected SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            reFresh();
        }
    };

    protected abstract void findViewAndSetListener();

    protected abstract void reFresh();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        if (System.currentTimeMillis() - time <= 1000 * 300) {
            return;
        }
        if (refreshLayout != null)
            refreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(true);
                    reFresh();
                }
            });
    }
}
