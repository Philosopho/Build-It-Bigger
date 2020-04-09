package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.IdlingResource;

import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.krinotech.jokeandroidlib.MainJokeActivity;
import com.krinotech.jokeprovider.Joker;


public class MainActivity extends AppCompatActivity {
    private Joker joker;

    public String getJoke() {
        return joke;
    }

    private String joke;

    public JokeIdlingResource getIdlingResource() {
        return idlingResource;
    }

    private JokeIdlingResource idlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        joker = new Joker();

        idlingResource = new JokeIdlingResource();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new EndpointsAsyncTask(this, idlingResource).execute();
    }


    public void setJoke(String result) {
        this.joke = result;
    }


    public void hideProgressBar() {
        ((MainActivityFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment))
                .hideProgressBar();
    }

    public void showProgressBar() {
        ((MainActivityFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment))
                .showAdProgress();
    }
}
