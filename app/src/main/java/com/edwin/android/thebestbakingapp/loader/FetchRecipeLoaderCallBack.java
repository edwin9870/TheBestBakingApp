package com.edwin.android.thebestbakingapp.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.edwin.android.thebestbakingapp.ui.adapter.BackingPosterAdapter;

/**
 * Created by Edwin Ramirez Ventura on 5/26/2017.
 */

public class FetchRecipeLoaderCallBack implements LoaderManager.LoaderCallbacks<RecipeDTO[]> {

    public static final String TAG = FetchRecipeLoaderCallBack.class.getSimpleName();
    private Context mContext;
    private BackingPosterAdapter mAdapter;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;

    public FetchRecipeLoaderCallBack(Context context, BackingPosterAdapter adapter, ProgressBar
            progressBar, RecyclerView recyclerView) {
        this.mContext = context;
        this.mAdapter = adapter;
        this.mProgressBar = progressBar;
        this.mRecyclerView = recyclerView;
    }

    @Override
    public Loader<RecipeDTO[]> onCreateLoader(int id, Bundle args) {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        return new FetchRecipeLoader(mContext);
    }

    @Override
    public void onLoadFinished(Loader<RecipeDTO[]> loader, RecipeDTO[] data) {
        Log.d(TAG, "Recipes: "+ data[0].toString());
        mAdapter.setBackingPoster(data);
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<RecipeDTO[]> loader) {

    }
}
