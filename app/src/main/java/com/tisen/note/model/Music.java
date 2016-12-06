package com.tisen.note.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by tisen on 2016/10/24.
 */
public class Music extends BmobObject{
    private String userId;
    private String content;
    private BmobFile avatar;
    private BmobFile mp3;


    @Override
    public String toString() {
        return "Music{" +
                "userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", avatar=" + avatar +
                ", mp3=" + mp3 +
                "} " + super.toString();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BmobFile getAvatar() {
        return avatar;
    }

    public void setAvatar(BmobFile avatar) {
        this.avatar = avatar;
    }

    public BmobFile getMp3() {
        return mp3;
    }

    public void setMp3(BmobFile mp3) {
        this.mp3 = mp3;
    }
}
