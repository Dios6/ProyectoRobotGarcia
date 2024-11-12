package com.example.proyectorobotgarcia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import android.media.MediaPlayer;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ProgressBar progressBar;
    private View myLayout;
    private EditText usuarioEditText;
    private Spinner spinner;



    public void onClickAcceder(View view){
        Intent intent = new Intent(this, AccesoActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textLoading);
        myLayout = findViewById(R.id.contenidoID);
        progressBar = findViewById(R.id.progresoBarra);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                //Actualizo la Interfaz del usuario desde el Thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        myLayout.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.GONE);
                    }
                });
            }
        });
        thread.start();

        usuarioEditText = findViewById(R.id.usuario);
        spinner = findViewById(R.id.spinnerRoles);
        String[] roles = {"Tester" , "Jugador"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        findViewById(R.id.boton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.clickeffect);
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                        Intent intent = new Intent(MainActivity.this, AccesoActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}