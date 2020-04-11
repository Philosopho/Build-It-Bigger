package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.krinotech.jokeandroidlib.MainJokeActivity;


public class MainActivity extends AppCompatActivity {
    private JokeIdlingResource idlingResource;
    private MainActivityFragment mainActivityFragment;
    private boolean showAd = false;

    public InterstitialAd getPublisherInterstitialAd() {
        return publisherInterstitialAd;
    }

    private InterstitialAd publisherInterstitialAd;

    private FragmentManager fragmentManager;

    private String joke;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idlingResource = new JokeIdlingResource();

        publisherInterstitialAd = new InterstitialAd(this);

        publisherInterstitialAd.setAdUnitId("/6499/example/interstitial");

        AdRequest adRequestInterstitial = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        publisherInterstitialAd.loadAd(adRequestInterstitial);

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
        new EndpointsAsyncTask(MainActivity.this, idlingResource).execute();
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

    public void setUpIntentAndLaunch(String joke) {
        Log.d("MainActivity", "setUpIntentAndLaunch: ");
        Intent intent = new Intent(this, MainJokeActivity.class);
        intent.putExtra(MainJokeActivity.EXTRA_JOKE, joke);

        showAd = true;
        setUpAd(intent);
    }

    private void launchJokeActivity(Intent intent) {
        Log.d("MainActivity", "launchJokeActivity: ");
        startActivity(intent);
    }


    private void setUpAd(final Intent intent) {
        Log.d("MainActivity", "setUpAd: ");
        publisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                hideProgressBar();
                if(showAd) {
                    getPublisherInterstitialAd().show();
                    showAd = false;
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                hideProgressBar();
                loadAd();
                launchJokeActivity(intent);
            }

            @Override
            public void onAdLeftApplication() {
                loadAd();
                launchJokeActivity(intent);
            }

            @Override
            public void onAdClicked() {
                loadAd();
                launchJokeActivity(intent);
            }

            @Override
            public void onAdClosed() {
                loadAd();
                launchJokeActivity(intent);
            }
        });
    }

    private void loadAd() {
        getPublisherInterstitialAd().loadAd(new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build());
        showAd = false;
    }

}
