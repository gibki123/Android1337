package com.example.android.test;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setTextColor(Color.GREEN);
        textView.setText("Wow!");
        textView.setTextSize(60);

        setContentView(textView);
    }
}
