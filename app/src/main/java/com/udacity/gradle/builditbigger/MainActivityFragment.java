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
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.krinotech.jokeprovider.Joker;
import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public final static String TAG = MainActivityFragment.class.getSimpleName();

    private FragmentMainBinding fragmentMainBinding;

    private InterstitialAd interstitialAd;

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
        interstitialAd = new InterstitialAd(getActivity());
        interstitialAd.setAdUnitId(fragmentMainBinding.adView.getAdUnitId());
        interstitialAd.loadAd(adRequest);
        return root;
    }

    public void hideProgressBar() {
        fragmentMainBinding.pbJoke.setVisibility(View.GONE);
        fragmentMainBinding.adView.setVisibility(View.GONE);
    }

    public void showAdProgress() {
        fragmentMainBinding.pbJoke.setVisibility(View.VISIBLE);
        fragmentMainBinding.adView.setVisibility(View.VISIBLE);
    }

    public InterstitialAd getInterstitialAd() {
        return interstitialAd;
    }
}
