package com.tisen.note.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tisen.note.R;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by tisen on 2016/11/8.
 */
public class VideoActivity extends BaseActivity implements MediaPlayer.OnCompletionListener{

    private VideoView videoPlayer;
    private MediaController mediaController;
    private com.tisen.note.utils.VideoControl videoControl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_video);
        videoPlayer = (VideoView) findViewById(R.id.video_player);
        videoControl = new com.tisen.note.utils.VideoControl(this,this,videoPlayer);
        videoPlayer.setMediaController(videoControl);
        videoControl.show(5000);
        videoControl.requestFocus();
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        videoPlayer.setVideoPath(path);
        videoPlayer.setOnCompletionListener(this);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if(videoPlayer!=null)
            videoPlayer.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE,0);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStart() {
        super.onStart();
        videoPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        videoPlayer.pause();
        videoPlayer = null;
        super.onDestroy();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        finish();
    }
}
