package com.tisen.note.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;

import com.tisen.note.TestActivity;
import com.tisen.note.model.Video;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by tisen on 2016/11/10.
 */
public class VideoUpload {

    private Context context;
    private TestActivity.Video video;

    private Video uploadVideo;

    private BmobFile mVideo;
    private BmobFile mAvatar;
    private boolean videoComplete;
    private boolean avatarComplete;
    private OnVideoUploadListener complete;

    public VideoUpload(Context context, OnVideoUploadListener complete) {
        this.context = context;
        this.complete = complete;
    }

    public void setVideo(TestActivity.Video video) {
        this.video = video;
        uploadVideo = new Video();
        uploadVideo.setSize(video.getSize());
        uploadVideo.setDuration(video.getDuration());
        uploadVideo.setTitle(video.getTitle());
    }

    public void upLoad(){
        videoComplete = false;
        avatarComplete = false;
        mVideo = new BmobFile(new File(video.getPath()));
        mAvatar = new BmobFile(new File(saveBitmap(video.getAvatar(),"avatar").getPath()));

        mVideo.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    videoComplete = true;
                    handler.sendEmptyMessage(1);
                }
            }

            @Override
            public void onProgress(Integer value) {
                Log.d("onProgress",uploadVideo.getTitle()+value+"%");
                complete.onProgress(value);
            }
        });
        mAvatar.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    avatarComplete = true;
                    handler.sendEmptyMessage(1);
                }
            }
        });
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(videoComplete&&avatarComplete){
                uploadVideo.setVideo(mVideo);
                uploadVideo.setAvatar(mAvatar);
                uploadVideo.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        complete.onComplete(s);
                    }
                });
            }
        }
    };
    public interface OnVideoUploadListener{
        void onComplete(String s);
        void onProgress(Integer value);
    }
    private Uri saveBitmap(Bitmap bm, String temp) {
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/" + temp);
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }
        File img = new File(tmpDir.getAbsolutePath() + "/avator.png");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.PNG, 50, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
