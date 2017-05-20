package com.edwin.android.thebestbakingapp.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Edwin Ramirez Ventur on 5/20/2017.
 */

public class RecipeAppClient {

    public static final String BASE_URL = "http://go.udacity.com/android-baking-app-json";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            Gson gson = new GsonBuilder()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
