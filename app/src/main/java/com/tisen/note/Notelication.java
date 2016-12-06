package com.tisen.note;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tisen.note.model.User;
import com.tisen.note.utils.BmobHelper;

import java.util.ArrayList;

import cn.bmob.v3.Bmob;
import io.vov.vitamio.Vitamio;

/**
 * Created by tisen on 2016/10/24.
 */
public class Notelication extends Application {

    public User user;
    public ArrayList<String>collects = new ArrayList<>();

    @Override
    public void onCreate() {
        Bmob.initialize(BmobHelper.getConfig(this));
        super.onCreate();
        Fresco.initialize(this);
        Vitamio.initialize(this);
        SDKInitializer.initialize(this);
    }
    public void initialize(){
        user = User.getCurrentUser(User.class);
        SharedPreferences preferences = getSharedPreferences("note", Context.MODE_PRIVATE);
    }
}
