package com.tisen.note.model;

import java.util.ArrayList;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by tisen on 2016/10/24.
 */
public class User extends BmobUser {
    private BmobFile avatar;
    private ArrayList<String>collects;

    public BmobFile getAvatar() {
        return avatar;
    }

    public void setAvatar(BmobFile avatar) {
        this.avatar = avatar;
    }

    public ArrayList<String> getCollects() {
        return collects;
    }

    public void setCollects(ArrayList<String> collects) {
        this.collects = collects;
    }
}
