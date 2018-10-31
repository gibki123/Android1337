package com.example.android.music;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer Happysad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Happysad = MediaPlayer.create(this,R.raw.happysad) ;

        Button PlayButton = (Button) findViewById(R.id.StartButton);

        Button StopButton = (Button) findViewById(R.id.StopButton);

        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Happysad.start();
            Happysad.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Toast.makeText(MainActivity.this, "Yayy it goes well", Toast.LENGTH_SHORT).show();
                }
            });
            }
        });

        StopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Happysad.pause();
            }
        });
    }
}
