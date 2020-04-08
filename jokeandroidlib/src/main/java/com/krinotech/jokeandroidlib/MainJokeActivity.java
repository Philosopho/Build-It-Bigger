package com.krinotech.jokeandroidlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.krinotech.jokeandroidlib.databinding.ActivityMainBinding;

public class MainJokeActivity extends AppCompatActivity {
    public static final String EXTRA_JOKE = "com.krinotech.jokeandroidlib.EXTRA_JOKE";
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        String joke = "";
        if(intent != null) {
            joke = intent.getStringExtra(EXTRA_JOKE);
        }


    }
}
