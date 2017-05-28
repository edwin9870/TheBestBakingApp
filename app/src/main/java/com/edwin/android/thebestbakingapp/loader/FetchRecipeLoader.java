package com.edwin.android.thebestbakingapp.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.edwin.android.thebestbakingapp.util.NetworkingUtil;

import static com.edwin.android.thebestbakingapp.ui.widgets.BakingWidgetProvider
        .updateRecipeDataWidget;

/**
 * Created by Edwin Ramirez Ventura on 5/26/2017.
 */

public class FetchRecipeLoader extends AsyncTaskLoader<RecipeDTO[]> {

    public static final String TAG = FetchRecipeLoader.class.getSimpleName();
    private RecipeDTO[] mRecipes;
    private Context mContext;

    public FetchRecipeLoader(Context context) {
        super(context);
        mContext = context;
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
        RecipeDTO[] recipes = NetworkingUtil.getRecipes(getContext());
        updateRecipeDataWidget(recipes, mContext);
        return recipes;
    }

    @Override
    public void deliverResult(RecipeDTO[] recipes) {
        mRecipes = recipes;
        super.deliverResult(mRecipes);
    }


}
