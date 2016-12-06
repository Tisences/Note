package com.tisen.note.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by tisen on 2016/10/24.
 */
public class Gif extends BmobObject {
    private String userId;
    private String content;
    private BmobFile firstImage;
    private BmobFile gif;
    private int width;
    private int height;


    @Override
    public String toString() {
        return "Gif{" +
                "userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", firstImage=" + firstImage +
                ", gif=" + gif +
                ", width=" + width +
                ", height=" + height +
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

    public BmobFile getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(BmobFile firstImage) {
        this.firstImage = firstImage;
    }

    public BmobFile getGif() {
        return gif;
    }

    public void setGif(BmobFile gif) {
        this.gif = gif;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
