package com.sibaamap.boundserviceexample;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;

public class MusicBoundService extends Service {

    private MyBinder myBinder = new MyBinder();
    private MediaPlayer mMediaPlayer;


    public class MyBinder extends Binder{
        MusicBoundService getMusicBoundService(){
            return MusicBoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MusicBoundService","onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MusicBoundService","onBind");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("MusicBoundService","onUnBind");
        return super.onUnbind(intent);


    }

    @Override
    public void onDestroy() {
        Log.e("MusicBoundService","onDestroy");
        super.onDestroy();
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void startMusic() {
        if(mMediaPlayer == null){
            mMediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.nhac);
        }
        mMediaPlayer.start();
    }
}
