package com.tisen.note.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tisen.note.R;
import com.tisen.note.adapter.BeautyAdapter;
import com.tisen.note.model.Beauty;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by tisen on 2016/10/24.
 */
public class BeautyFragment extends BaseFragment  {
    private BeautyAdapter adapter;
    private ArrayList<Beauty>beauties = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("BeautyFragment","onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("BeautyFragment","onCreateView");
        super.onCreateView(inflater, container,R.layout.view_refresh_listview);
        return view;
    }

    protected void findViewAndSetListener() {
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(onRefreshListener);
        listView = (ListView) view.findViewById(R.id.refresh_listView);
        adapter = new BeautyAdapter(beauties,getActivity());
        listView.setAdapter(adapter);
        isPrepared = true;
    }


    @Override
    protected void reFresh() {
        down.sendEmptyMessage(1);
    }
    private Handler down = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            BmobQuery<Beauty>query = new BmobQuery<>();
            query.order("-createdAt");
            query.findObjects(new FindListener<Beauty>() {
                @Override
                public void done(List<Beauty> list, BmobException e) {
                    refreshLayout.setRefreshing(false);
                    time = System.currentTimeMillis();
                    if(e==null&&!list.isEmpty()){
                        beauties = (ArrayList<Beauty>) list;
                        adapter.reFresh(list);
                    }
                }
            });
        }
    };
}
