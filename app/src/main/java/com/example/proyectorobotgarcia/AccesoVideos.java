package com.example.proyectorobotgarcia;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.media.MediaPlayer;
import android.view.View;


public class AccesoVideos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);


        VideoView videoView = findViewById(R.id.videoView);
        String videoPath = "android.resource://"+ getPackageName() + "/" + R.raw.arduinobot;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();

        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String videoUrl = "https://www.youtube.com/embed/pDYHxBmSJXM";
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(videoUrl);

        findViewById(R.id.botonReg).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MediaPlayer mediaPlayer = MediaPlayer.create(AccesoVideos.this, R.raw.clickeffect);
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                        Intent intent = new Intent(AccesoVideos.this, AccesoActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

        findViewById(R.id.botonVol).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MediaPlayer mediaPlayer = MediaPlayer.create(AccesoVideos.this, R.raw.clickeffect);
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                        Intent intent = new Intent(AccesoVideos.this, AccesoVentana.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
