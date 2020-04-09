package com.udacity.gradle.builditbigger.paid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.udacity.gradle.builditbigger.R;
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
        return fragmentMainBinding.getRoot();
    }

    public void hideProgressBar() {
        fragmentMainBinding.pbJoke.setVisibility(View.GONE);
    }

    public void showProgressBar() {
        fragmentMainBinding.pbJoke.setVisibility(View.VISIBLE);
    }
}
