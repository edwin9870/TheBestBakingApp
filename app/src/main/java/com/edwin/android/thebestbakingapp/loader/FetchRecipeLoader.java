package com.edwin.android.thebestbakingapp.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
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
        OkHttpClient client = new OkHttpClient();

        String urlToGetData = getContext().getString(R.string.baking_url_data);
        Request request = new Request.Builder()
                .url(urlToGetData)
                .build();

        mRecipes = null;
        try {
            Response response = client.newCall(request).execute();

            String responseJson = response.body().string();
            Gson gson = new Gson();
            mRecipes = gson.fromJson(responseJson, RecipeDTO[].class);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        return mRecipes;
    }

    @Override
    public void deliverResult(RecipeDTO[] recipes) {
        mRecipes = recipes;
        super.deliverResult(mRecipes);
    }
}
