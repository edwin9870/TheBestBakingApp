package com.edwin.android.thebestbakingapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.StepDTO;
import com.edwin.android.thebestbakingapp.util.Constants;

import butterknife.ButterKnife;

/**
 * Created by Edwin Ramirez Ventur on 5/21/2017.
 */

public class StepFragment extends Fragment {

    public static final String TAG = StepFragment.class.getSimpleName();
    private StepDTO step;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_step, container, false);
        ButterKnife.bind(this, view);

        Intent intentThatStartedThisActivity = getActivity().getIntent();
        step = intentThatStartedThisActivity.getParcelableExtra(Constants.Intent.STEP_TYPE);

        Log.d(TAG, "Step received: "+ step);

        return view;
    }
}
