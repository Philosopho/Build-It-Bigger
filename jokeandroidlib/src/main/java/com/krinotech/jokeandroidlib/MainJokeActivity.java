package com.krinotech.jokeandroidlib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.krinotech.jokeandroidlib.databinding.ActivityJokeMainBinding;

public class MainJokeActivity extends AppCompatActivity {
    public static final String EXTRA_JOKE = "com.krinotech.jokeandroidlib.EXTRA_JOKE";
    private ActivityJokeMainBinding activityJokeMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityJokeMainBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_joke_main);

        Intent intent = getIntent();

        String joke = "";
        if(intent != null) {
            joke = intent.getStringExtra(EXTRA_JOKE);
        }

        activityJokeMainBinding.tvJoke.setText(joke);
    }
}
