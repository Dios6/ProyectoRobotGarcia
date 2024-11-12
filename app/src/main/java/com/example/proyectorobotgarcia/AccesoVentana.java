package com.example.proyectorobotgarcia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AccesoVentana extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana);

        findViewById(R.id.botonVol).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MediaPlayer mediaPlayer = MediaPlayer.create(AccesoVentana.this, R.raw.clickeffect);
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                        Intent intent = new Intent(AccesoVentana.this, AccesoActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

        findViewById(R.id.botonVid).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MediaPlayer mediaPlayer = MediaPlayer.create(AccesoVentana.this, R.raw.clickeffect);
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                        Intent intent = new Intent(AccesoVentana.this, AccesoVideos.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
