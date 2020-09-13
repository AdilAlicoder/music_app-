package com.india.audio_music;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static android.os.Environment.DIRECTORY_PICTURES;

public class music_play extends AppCompatActivity {
    SeekBar seekProg;
    Intent intent;
    Button download,fav;
    MediaPlayer mediaPlayer;
    int i=0;
    TextView titlepass;
    sqlitedatabase_use sqlitedatabaseUse;
    DownloadManager downloadManager;
    Button pause;
    Runnable runnable;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        seekProg=findViewById(R.id.seekProg);
        fav=findViewById(R.id.fav);
        intent=getIntent();
        titlepass=findViewById(R.id.titlepass);
        sqlitedatabaseUse=new sqlitedatabase_use(this);
        handler=new Handler();
        download=findViewById(R.id.download);
        final String key=intent.getStringExtra("key");
        final String title=intent.getStringExtra("title");
        titlepass.setText(title);
        pause=findViewById(R.id.pause);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result=sqlitedatabaseUse.insertdata(title,key);
                if(result==true){
                    //Toast.makeText(getApplicationContext(),"Data insert",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Data not insert",Toast.LENGTH_LONG).show();
                }
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                Uri downloadUri = Uri.parse(key);
                DownloadManager.Request request = new DownloadManager.Request(downloadUri);
                request.allowScanningByMediaScanner();
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setTitle("music download");
                dm.enqueue(request);
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==0) {
                    pause.setText("▶");
                    i=1;
                    mediaPlayer.pause();
                }
                else{
                    pause.setText("▌▌");
                    mediaPlayer.start();
                    i=0;
                }

            }
        });
         mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource(key);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                    updateseekbar();
                }

                private void updateseekbar() {
                    seekProg.setMax(mediaPlayer.getDuration());
                    int postion=mediaPlayer.getCurrentPosition();
                    seekProg.setProgress(postion);
                    runnable=new Runnable(){

                        @Override
                        public void run() {
                            updateseekbar();
                        }
                    };
                    handler.postDelayed(runnable,1000);
                }
            });
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        seekProg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        super.onBackPressed();
    }
}