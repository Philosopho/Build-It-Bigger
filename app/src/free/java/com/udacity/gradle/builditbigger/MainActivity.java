package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;


public class MainActivity extends AppCompatActivity {
    private JokeIdlingResource idlingResource;
    private MainActivityFragment mainActivityFragment;

    private FragmentManager fragmentManager;

    private String joke;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idlingResource = new JokeIdlingResource();

        mainActivityFragment = new MainActivityFragment();

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.fragment, mainActivityFragment, MainActivityFragment.TAG)
                .commit();
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
        mainActivityFragment.hideProgressBar();
    }

    public void showProgressBar() {
        mainActivityFragment.showProgressBar();
    }

    public String getJoke() {
        return joke;
    }

    public JokeIdlingResource getIdlingResource() {
        return idlingResource;
    }
}
