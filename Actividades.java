package com.example.espeletiapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Actividades extends AppCompatActivity {

    private ImageView play1,play2,play3,play4,play5,play6;
    private ImageView btnatras;
    private MediaPlayer mediaPlayer1;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;
    private MediaPlayer mediaPlayer4;
    private MediaPlayer mediaPlayer5;
    private MediaPlayer mediaPlayer6;
    private Handler handler;
    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);

        play1 = findViewById(R.id.play1);
        play2 = findViewById(R.id.play2);
        play3 = findViewById(R.id.play3);
        play4 = findViewById(R.id.play4);
        play5 = findViewById(R.id.play5);
        play6 = findViewById(R.id.play6);
        btnatras = findViewById(R.id.btnatras);
        handler = new Handler();
        mediaPlayer1 = MediaPlayer.create(this, R.raw.avistamiento);
        mediaPlayer2 = MediaPlayer.create(this,R.raw.fotografias);
        mediaPlayer3 = MediaPlayer.create(this,R.raw.practicas);
        mediaPlayer4 = MediaPlayer.create(this,R.raw.senderismo);
        mediaPlayer5 = MediaPlayer.create(this,R.raw.caballo);
        mediaPlayer6 = MediaPlayer.create(this,R.raw.ciclo);

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer1.isPlaying()){
                    mediaPlayer1.pause();
                    play1.setBackgroundResource(R.drawable.sin);
                }else{
                    mediaPlayer1.start();
                    play1.setBackgroundResource(R.drawable.cons);
                }
            }
        });

        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer2.isPlaying()){
                    mediaPlayer2.pause();
                    play2.setBackgroundResource(R.drawable.sin);
                }else{
                    mediaPlayer2.start();
                    play2.setBackgroundResource(R.drawable.cons);
                    mediaPlayer1.pause();
                    play1.setBackgroundResource(R.drawable.sin);

                }
            }
        });

        play3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer3.isPlaying()){
                    mediaPlayer3.pause();
                    play3.setBackgroundResource(R.drawable.sin);
                }else{
                    mediaPlayer3.start();
                    play3.setBackgroundResource(R.drawable.cons);
                    mediaPlayer2.pause();
                    play2.setBackgroundResource(R.drawable.sin);
                }
            }
        });

        play4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer4.isPlaying()){
                    mediaPlayer4.pause();
                    play4.setBackgroundResource(R.drawable.sin);
                }else{
                    mediaPlayer4.start();
                    play4.setBackgroundResource(R.drawable.cons);
                    mediaPlayer3.pause();
                    play3.setBackgroundResource(R.drawable.sin);
                }
            }
        });

        play5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer5.isPlaying()){
                    mediaPlayer5.pause();
                    play5.setBackgroundResource(R.drawable.sin);
                }else{
                    mediaPlayer5.start();
                    play5.setBackgroundResource(R.drawable.cons);
                    mediaPlayer4.pause();
                    play4.setBackgroundResource(R.drawable.sin);
                }
            }
        });

        play6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer6.isPlaying()){
                    mediaPlayer6.pause();
                    play6.setBackgroundResource(R.drawable.sin);
                }else{
                    mediaPlayer6.start();
                    play6.setBackgroundResource(R.drawable.cons);
                    mediaPlayer5.pause();
                    play5.setBackgroundResource(R.drawable.sin);
                }
            }
        });

        btnatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Actividades.this,Menu.class);
                startActivity(intent);
                mediaPlayer1.pause();
                mediaPlayer2.pause();
                mediaPlayer3.pause();
                mediaPlayer4.pause();
                mediaPlayer5.pause();
                mediaPlayer6.pause();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(contador==0)
        {
            Intent intent = new Intent(Actividades.this,Menu.class);
            startActivity(intent);
            mediaPlayer1.pause();
            mediaPlayer2.pause();
            mediaPlayer3.pause();
            mediaPlayer4.pause();
            mediaPlayer5.pause();
            mediaPlayer6.pause();
        }
        else
        {
            super.onBackPressed();
        }

        new CountDownTimer(3000,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                contador = 0;
            }
        }.start();
    }
}