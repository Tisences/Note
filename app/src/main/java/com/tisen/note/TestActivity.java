package com.tisen.note;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tisen.note.activity.BaseActivity;
import com.tisen.note.activity.VideoActivity;
import com.tisen.note.adapter.VideoAdapter;
import com.tisen.note.model.Video;
import com.tisen.note.utils.VideoUpload;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tisen on 2016/11/4.
 */
public class TestActivity extends BaseActivity {

    private Button upload;
    private VideoUpload videoUpload;
    private List<Video> videos = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        Intent intent = getIntent();
        String path = intent.getDataString();
        if (path == null || path.isEmpty()) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            videoUpload = new VideoUpload(this, complete);
            upload = (Button) findViewById(R.id.upload);
            upload.setOnClickListener(upLoadOnClickListener);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            videos = getVideos();
            recyclerView.setAdapter(new VideoAdapter(this, videos));
        } else {
            Intent intent1 = new Intent();
            intent1.setClass(this, VideoActivity.class);
            intent1.putExtra("path", path);
            startActivity(intent1);
            finish();
        }
    }

    private VideoUpload.OnVideoUploadListener complete = new VideoUpload.OnVideoUploadListener() {
        @Override
        public void onComplete(String s) {
            uploadQueen();
        }

        @Override
        public void onProgress(Integer value) {

        }
    };

    private View.OnClickListener upLoadOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            uploadQueen();
        }
    };

    private void uploadQueen() {
        if (videos.size() > 0) {
            videoUpload.setVideo(videos.get(0));
            videos.remove(0);
            videoUpload.upLoad();
        }
    }

    public List<Video> getVideos() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        List<Video> videos = new ArrayList<>();
        Video video = null;
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                , null, null, null, MediaStore.Video.DEFAULT_SORT_ORDER);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                video = new Video();
                video.id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                video.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                video.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                video.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
                video.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                video.avatar = MediaStore.Video.Thumbnails.getThumbnail(resolver, video.id, MediaStore.Video.Thumbnails.MINI_KIND, options);
                Log.d("video", video.toString());
                videos.add(video);
                cursor.moveToNext();
            }
        }
        return videos;
    }

    public class Video implements Serializable {
        int id;
        String title;
        String path;
        Bitmap avatar;
        long size;
        int duration;

        public Bitmap getAvatar() {
            return avatar;
        }

        public void setAvatar(Bitmap avatar) {
            this.avatar = avatar;
        }

        public String getTitle() {
            return title;
        }

        public String getPath() {
            return path;
        }

        public int getId() {
            return id;
        }

        public long getSize() {
            return size;
        }

        public int getDuration() {
            return duration;
        }

        @Override
        public String toString() {
            return "Video{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", path='" + path + '\'' +
                    ", avatar=" + avatar +
                    ", size=" + size +
                    ", duration=" + duration +
                    '}';
        }
    }

    public void getImage() {
        List<Image> images = new ArrayList<>();
        Image image = null;
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                , null, null, null, MediaStore.Images.Media.DEFAULT_SORT_ORDER);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                image = new Image();
                image.id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                image.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE));
                image.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                image.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));
                image.width = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH));
                image.height = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT));
                if (image.path.endsWith(".gif"))
                    Log.d("image", image.toString());
                cursor.moveToNext();
            }
        }
    }

    public class Image {
        int id;
        String title;
        String path;
        long size;
        int width;
        int height;

        @Override
        public String toString() {
            return "Image{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", path='" + path + '\'' +
                    ", size=" + size +
                    ", width=" + width +
                    ", height=" + height +
                    '}';
        }
    }
}
