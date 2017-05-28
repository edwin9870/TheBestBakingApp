package com.edwin.android.thebestbakingapp.loader;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.edwin.android.thebestbakingapp.ui.activities.MainActivity;
import com.edwin.android.thebestbakingapp.ui.adapter.BackingPosterAdapter;
import com.edwin.android.thebestbakingapp.ui.widgets.BakingWidgetProvider;
import com.edwin.android.thebestbakingapp.util.SimpleIdlingResource;
import com.google.gson.Gson;

/**
 * Created by Edwin Ramirez Ventura on 5/26/2017.
 */

public class FetchRecipeLoaderCallBack implements LoaderManager.LoaderCallbacks<RecipeDTO[]> {

    public static final String TAG = FetchRecipeLoaderCallBack.class.getSimpleName();
    private Context mContext;
    private BackingPosterAdapter mAdapter;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private SimpleIdlingResource mIdlingResource;

    public FetchRecipeLoaderCallBack(Context context, BackingPosterAdapter adapter, ProgressBar
            progressBar, RecyclerView recyclerView, SimpleIdlingResource idlingResource) {
        this.mContext = context;
        this.mAdapter = adapter;
        this.mProgressBar = progressBar;
        this.mRecyclerView = recyclerView;
        this.mIdlingResource = idlingResource;
    }

    @Override
    public Loader<RecipeDTO[]> onCreateLoader(int id, Bundle args) {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        if(mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
            Log.d(TAG, "Setting idle state to false");
        }
        return new FetchRecipeLoader(mContext);
    }

    @Override
    public void onLoadFinished(Loader<RecipeDTO[]> loader, RecipeDTO[] data) {
        Log.d(TAG, "Recipes: "+ data[0].toString());
        mAdapter.setBackingPoster(data);
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);

        if(mIdlingResource != null) {
            mIdlingResource.setIdleState(true);
            Log.d(TAG, "Setting idle state to true");
        }
    }

    @Override
    public void onLoaderReset(Loader<RecipeDTO[]> loader) {

    }
}
