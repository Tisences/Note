package com.tisen.note.utils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;


import com.tisen.note.bean.Music;

import java.util.ArrayList;

/**
 * Created by tisen on 2016/10/28.
 */
public class PlayerService extends Service implements MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener,MediaPlayer.OnErrorListener {

    private MediaPlayer mediaPlayer;
    private ArrayList<Music>musics;
    private int position;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnErrorListener(this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayerBinder();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    public class PlayerBinder extends Binder{
        public PlayerService getService(){
            return PlayerService.this;
        }
    }
}
