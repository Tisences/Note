package com.tisen.note.fragment;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tisen.note.R;

/**
 * Created by tisen on 2016/10/24.
 */
public class FunnyFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, int layoutId) {
        super.onCreateView(inflater,container, R.layout.view_refresh_recyclerview);
        return view;
    }

    @Override
    protected void findViewAndSetListener() {

    }

    @Override
    protected void reFresh() {

    }
}
