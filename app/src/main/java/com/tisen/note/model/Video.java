package com.tisen.note.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by tisen on 2016/10/24.
 */
public class Video extends BmobObject{
    private String title;
    private int duration;
    private long size;
    private BmobFile video;
    private BmobFile avatar;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public BmobFile getVideo() {
        return video;
    }

    public void setVideo(BmobFile video) {
        this.video = video;
    }

    public BmobFile getAvatar() {
        return avatar;
    }

    public void setAvatar(BmobFile avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Video{" +
                "title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", size='" + size + '\'' +
                ", video=" + video +
                ", avatar=" + avatar +
                '}';
    }
}
