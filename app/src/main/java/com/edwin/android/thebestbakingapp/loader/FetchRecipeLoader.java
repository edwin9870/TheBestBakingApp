package com.edwin.android.thebestbakingapp.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.edwin.android.thebestbakingapp.util.NetworkingUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Edwin Ramirez Ventura on 5/26/2017.
 */

public class FetchRecipeLoader extends AsyncTaskLoader<RecipeDTO[]> {

    public static final String TAG = FetchRecipeLoader.class.getSimpleName();
    private RecipeDTO[] mRecipes;

    public FetchRecipeLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mRecipes != null) {
            deliverResult(mRecipes);
        } else {
            forceLoad();
        }
    }

    @Override
    public RecipeDTO[] loadInBackground() {
        return NetworkingUtil.getRecipes(getContext());
    }

    @Override
    public void deliverResult(RecipeDTO[] recipes) {
        mRecipes = recipes;
        super.deliverResult(mRecipes);
    }
}
