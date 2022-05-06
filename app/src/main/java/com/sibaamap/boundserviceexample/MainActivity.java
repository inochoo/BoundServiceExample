package com.sibaamap.boundserviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnStartService,btnStopService;

    private MusicBoundService mMusicBoundService;

    private boolean isServiceConnected;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicBoundService.MyBinder myBinder = (MusicBoundService.MyBinder) service;
            mMusicBoundService = myBinder.getMusicBoundService();
            mMusicBoundService.startMusic();
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceConnected = false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = findViewById(R.id.btn_start_service);
        btnStopService = findViewById(R.id.btn_stop_service);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStartService();
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStopService();
            }
        });



    }

    private void onClickStopService() {
        if(isServiceConnected){
            unbindService(mServiceConnection);
            isServiceConnected = false;
        }

    }

    private void onClickStartService() {

        Intent intent = new Intent(this, MusicBoundService.class);
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);


    }
}