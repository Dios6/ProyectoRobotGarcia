package com.example.proyectorobotgarcia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccesoActivity extends AppCompatActivity {
    private TextView textView;
    private ProgressBar progressBar;
    private View myLayout;
    private EditText usuarioEditText;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        findViewById(R.id.botonRev).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MediaPlayer mediaPlayer = MediaPlayer.create(AccesoActivity.this, R.raw.clickeffect);
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                        Intent intent = new Intent(AccesoActivity.this, AccesoRevisar.class);
                        startActivity(intent);
                    }
                });
            }
        });

        findViewById(R.id.botonRob).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MediaPlayer mediaPlayer = MediaPlayer.create(AccesoActivity.this, R.raw.clickeffect);
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                        Intent intent = new Intent(AccesoActivity.this, AccesoVentana.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}

