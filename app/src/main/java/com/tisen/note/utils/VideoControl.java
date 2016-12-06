package com.tisen.note.utils;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tisen.note.R;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by tisen on 2016/11/9.
 */
public class VideoControl extends MediaController {

    private GestureDetector gestureDetector;
    private ImageView back;
    private VideoView videoView;
    private Activity activity;
    private Context context;

    private int controllerWidth = 0;


    private View volumeBrightnessLayout;
    private ImageView tipImage;
    private TextView tipText;
    private static final int HIDEFRAM = 0;
    private int maxVolume;
    private int volume;
    private float maxBrightness;
    private float brightness;
    private AudioManager audioManager;

    private int windowWidth;
    private int windowHeight;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            long pos;
            switch (msg.what){
                case HIDEFRAM:volumeBrightnessLayout.setVisibility(GONE);
                    break;
            }
        }
    };

    public VideoControl(Context context, Activity activity, VideoView videoView) {
        super(context);
        this.context = context;
        this.activity = activity;
        this.videoView = videoView;
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        controllerWidth = manager.getDefaultDisplay().getWidth();
        gestureDetector = new GestureDetector(context,new VideoGestureListener());
    }

    private View.OnClickListener backListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(activity!=null)
                activity.finish();
        }
    };

    @Override
    protected View makeControllerView() {
        View v = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(getResources().getIdentifier("view_video_control", "layout", getContext().getPackageName()), this);
        v.setMinimumHeight(controllerWidth);
        back = (ImageView) v.findViewById(getResources().getIdentifier("mediacontroller_back", "id", context.getPackageName()));
        volumeBrightnessLayout = v.findViewById(getResources().getIdentifier("volume_brightness","id",context.getPackageName()));
        tipImage = (ImageView) v.findViewById(getResources().getIdentifier("volume_brightness_image","id",context.getPackageName()));
        tipText = (TextView) v.findViewById(getResources().getIdentifier("volume_brightness_text","id",context.getPackageName()));
        back.setOnClickListener(backListener);
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        Display display = activity.getWindowManager().getDefaultDisplay();
        windowWidth = display.getWidth();
        windowHeight = display.getHeight();
        return v;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("onTouchEvent",event.toString());
        if(gestureDetector.onTouchEvent(event))
            return true;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:endGesture();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void endGesture() {
        volume = -1;
        brightness = -1f;
        handler.removeMessages(HIDEFRAM);
        handler.sendEmptyMessageDelayed(HIDEFRAM,1);
    }

    private class VideoGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d("VideoGestureListener","onSingleTapUp");
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d("VideoGestureListener","onSingleTapConfirmed");
            toggleMediaControlVisiblity();
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("VideoGestureListener","onDown");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("VideoGestureListener","onScroll");

            float oldX = e1.getX(),oldY = e1.getY();
            int y = (int) e2.getRawY();
            int x = (int) e2.getRawX();
            if(oldX>windowWidth/2){

            }else if(oldX<windowWidth/2){

            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }


        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("VideoGestureListener","onDoubleTap");
            playOrPause();
            return true;
        }
    }

    private void toggleMediaControlVisiblity(){
        if(isShowing()){
            hide();
        }else {
            show();

        }
    }
    private void onVolimeSlide(float percent){

    }

    @Override
    public void hide() {
        super.hide();
        videoView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void show() {
        super.show();
        videoView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    private void playOrPause(){
        if(videoView!=null){
            if(videoView.isPlaying()){
                videoView.pause();
            }else {
                videoView.start();
            }
        }
    }
}
