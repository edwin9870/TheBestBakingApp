package com.edwin.android.thebestbakingapp.util;

import android.content.Context;
import android.util.Log;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Edwin Ramirez Ventura on 5/27/2017.
 */

public class NetworkingUtil {

    public static final String TAG = NetworkingUtil.class.getSimpleName();

    public static RecipeDTO[] getRecipes(Context context) {
        OkHttpClient client = new OkHttpClient();

        String urlToGetData = context.getString(R.string.baking_url_data);
        Request request = new Request.Builder().url(urlToGetData)
                .build();

        RecipeDTO[] recipes = null;
        try {
            Response response = client.newCall(request).execute();

            String responseJson = response.body().string();
            Gson gson = new Gson();
            recipes = gson.fromJson(responseJson, RecipeDTO[].class);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return recipes;
    }
}
