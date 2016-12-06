package com.tisen.note.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tisen.note.R;
import com.tisen.note.adapter.BeautyAdapter;
import com.tisen.note.adapter.GifAdapter;
import com.tisen.note.model.Beauty;
import com.tisen.note.model.Gif;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by tisen on 2016/10/24.
 */
public class GifFragment extends BaseFragment {
    private GifAdapter adapter;
    private ArrayList<Gif> gifs = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, R.layout.view_refresh_recyclerview);
        return view;
    }

    protected void findViewAndSetListener() {
        layoutManager=new LinearLayoutManager(getActivity());
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(onRefreshListener);
        recyclerView = (RecyclerView) view.findViewById(R.id.refresh_recyclerView);
        adapter = new GifAdapter(gifs,getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        isPrepared = true;
    }


    @Override
    protected void reFresh() {
        down.sendEmptyMessage(1);
    }
    private Handler down = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            BmobQuery<Gif> query = new BmobQuery<>();
            query.order("-createdAt");
            query.findObjects(new FindListener<Gif>() {
                @Override
                public void done(List<Gif> list, BmobException e) {
                    refreshLayout.setRefreshing(false);
                    time = System.currentTimeMillis();
                    if(e==null&&!list.isEmpty()){
                        gifs = (ArrayList<Gif>) list;
                        adapter.reFresh(list);
                    }
                }
            });
        }
    };
}
