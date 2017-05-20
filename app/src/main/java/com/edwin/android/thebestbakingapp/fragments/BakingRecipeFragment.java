package com.edwin.android.thebestbakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.adapters.BackingPosterAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edwin Ramirez Ventur on 5/20/2017.
 */

public class BakingRecipeFragment extends Fragment implements BackingPosterAdapter
        .BackingPosterOnClickHandler {

    public static final String TAG = BakingRecipeFragment.class.getSimpleName();
    @BindView(R.id.recycler_view_baking)
    RecyclerView mRecyclerView;
    private BackingPosterAdapter mBackingPosterAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_baking_recipe, container, false);
        ButterKnife.bind(this, view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        Log.d(TAG, "$gridLayoutManager: " + gridLayoutManager);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(false);

        mBackingPosterAdapter = new BackingPosterAdapter(getActivity(), this);

        Log.i(TAG, "Calling the adapter");
        mRecyclerView.setAdapter(mBackingPosterAdapter);

        List<String> backingPoster = new ArrayList<>();
        backingPoster.add("https://d17h27t6h515a5.cloudfront" +
                ".net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");
        backingPoster.add("https://d17h27t6h515a5.cloudfront" +
                ".net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");
        backingPoster.add("https://d17h27t6h515a5.cloudfront" +
                ".net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");
        backingPoster.add("https://d17h27t6h515a5.cloudfront" +
                ".net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");
        backingPoster.add("https://d17h27t6h515a5.cloudfront" +
                ".net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");
        backingPoster.add("https://d17h27t6h515a5.cloudfront" +
                ".net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");

        mBackingPosterAdapter.setBackingPoster(backingPoster);

        return view;
    }

    @Override
    public void onClick(String movie) {
        Log.d(TAG, "Baking poster clicked!");
    }
}
