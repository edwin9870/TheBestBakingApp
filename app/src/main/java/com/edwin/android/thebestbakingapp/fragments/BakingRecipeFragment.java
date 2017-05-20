package com.edwin.android.thebestbakingapp.fragments;

import android.os.AsyncTask;
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
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

        new AsyncTask<Void, Void, RecipeDTO[]>() {

            @Override
            protected RecipeDTO[] doInBackground(Void... params) {
                OkHttpClient client = new OkHttpClient();

                String urlToGetData = getString(R.string.baking_url_data);
                Request request = new Request.Builder()
                        .url(urlToGetData)
                        .build();

                RecipeDTO[] recipeDTO = null;
                try {
                    Response response = client.newCall(request).execute();

                    String responseJson = response.body().string();
                    Gson gson = new Gson();
                    recipeDTO = gson.fromJson(responseJson, RecipeDTO[].class);
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }

                return recipeDTO;
            }

            @Override
            protected void onPostExecute(RecipeDTO[] recipes) {
                Log.d(TAG, "recipes size: " + recipes.length);
                mBackingPosterAdapter.setBackingPoster(recipes);
            }
        }.execute();


        return view;
    }

    @Override
    public void onClick(RecipeDTO recipe) {
        Log.d(TAG, "Recipe name clicked: "+ recipe.getName());
    }
}
