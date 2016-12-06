package com.tisen.note.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by tisen on 2016/10/24.
 */
public class Beauty extends BmobObject {


    private String userId;
    private String content;
    private BmobFile image;
    private int width;
    private int height;


    @Override
    public String toString() {
        return "Beauty{" +
                "userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", image=" + image +
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

    public BmobFile getImage() {
        return image;
    }

    public void setImage(BmobFile image) {
        this.image = image;
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
