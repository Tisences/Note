package com.tisen.note.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.tisen.note.utils.BmobHelper;

import cn.bmob.v3.Bmob;

/**
 * Created by tisen on 2016/10/24.
 */
public class BaseActivity extends AppCompatActivity {
    public static String TAG = "";

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        Bmob.initialize(BmobHelper.getConfig(this));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getLocalClassName();
    }
}
