package com.udacity.gradle.builditbigger;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.krinotech.jokeprovider.Joker;
import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public final static String TAG = MainActivityFragment.class.getSimpleName();

    private FragmentMainBinding fragmentMainBinding;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View root = fragmentMainBinding.getRoot();

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        fragmentMainBinding.adView.loadAd(adRequest);
        return root;
    }

    public void hideProgressBar() {
        fragmentMainBinding.pbJoke.setVisibility(View.GONE);
    }

    public void showAdProgress() {
        fragmentMainBinding.pbJoke.setVisibility(View.VISIBLE);
    }
}
