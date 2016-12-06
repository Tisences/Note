package com.tisen.note.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tisen.note.R;
import com.tisen.note.model.Gif;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by tisen on 2016/10/24.
 */
public class HomeActivity extends BaseActivity{
    public final static String TAG = "HomeActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
