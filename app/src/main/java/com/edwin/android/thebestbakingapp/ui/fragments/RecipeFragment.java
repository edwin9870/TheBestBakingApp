package com.edwin.android.thebestbakingapp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.edwin.android.thebestbakingapp.loader.FetchRecipeLoaderCallBack;
import com.edwin.android.thebestbakingapp.ui.activities.RecipeDetailActivity;
import com.edwin.android.thebestbakingapp.ui.adapter.BackingPosterAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Edwin Ramirez Ventur on 5/20/2017.
 */

public class RecipeFragment extends Fragment implements BackingPosterAdapter
        .BackingPosterOnClickHandler {

    public static final String TAG = RecipeFragment.class.getSimpleName();
    public static final int FETCH_BAKING_RECIPES_LOADER = 5454;
    @BindView(R.id.progress_bar_loading_indicator)
    ProgressBar progressBarLoadingIndicator;
    @BindView(R.id.recycler_view_baking)
    RecyclerView mRecyclerView;
    private BackingPosterAdapter mBackingPosterAdapter;
    private Unbinder mUnbinder;


    public enum IntentKey {
        RECIPE_TYPE;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_baking_recipe, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        int backingColumnNumber = getResources().getInteger(R.integer.backing_column);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),
                backingColumnNumber);
        Log.d(TAG, "$gridLayoutManager: " + gridLayoutManager);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(false);

        mBackingPosterAdapter = new BackingPosterAdapter(getActivity(), this);

        Log.i(TAG, "Calling the adapter");
        mRecyclerView.setAdapter(mBackingPosterAdapter);

        setupLoader();

        return view;
    }

    private void setupLoader() {
        LoaderManager loaderManager = getActivity().getSupportLoaderManager();
        FetchRecipeLoaderCallBack loaderCallback = new FetchRecipeLoaderCallBack(getActivity(),
                mBackingPosterAdapter, progressBarLoadingIndicator, mRecyclerView);

        loaderManager.initLoader(FETCH_BAKING_RECIPES_LOADER, new Bundle(), loaderCallback);
    }

    @Override
    public void onClick(RecipeDTO recipe) {
        Log.d(TAG, "Recipe name clicked: " + recipe.getName());
        Class<RecipeDetailActivity> destinationActivity = RecipeDetailActivity.class;
        Intent intent = new Intent(RecipeFragment.this.getActivity(), destinationActivity);

        intent.putExtra(IntentKey.RECIPE_TYPE.name(), recipe);
        startActivity(intent);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
