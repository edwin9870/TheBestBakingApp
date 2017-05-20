package com.edwin.android.thebestbakingapp.rest;

import com.edwin.android.thebestbakingapp.entity.RecipeDTO;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Edwin Ramirez Ventur on 5/20/2017.
 */

public interface RecipeAPI {

    @GET
    Call<RecipeDTO> getRecipes();
}
