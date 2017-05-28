package com.edwin.android.thebestbakingapp.ui.widgets;

import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.IngredientDTO;
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.edwin.android.thebestbakingapp.util.NetworkingUtil;

/**
 * Created by Edwin Ramirez Ventura on 5/27/2017.
 */

public class RecipeRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    public static final String TAG = RecipeRemoteViewFactory.class.getSimpleName();
    private Context mContext;
    private RecipeDTO[] mRecipes;

    public RecipeRemoteViewFactory(Context applicationContext) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        mRecipes = NetworkingUtil.getRecipes(mContext);

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        Log.d(TAG, "Count called");
        if(mRecipes == null) {
            return 0;
        }

        Log.d(TAG, "mRecipes size: "+ mRecipes.length);
        return mRecipes.length;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.d(TAG, "getViewAt: "+ position);
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.item_recipe_widget);

        views.setTextViewText(R.id.text_widget_recipe_name, mRecipes[position].getName());

        RemoteViews ingredientView;
        for(IngredientDTO ingredient : mRecipes[position].getIngredients()) {
            ingredientView = new RemoteViews(mContext.getPackageName(), R.layout.item_ingredient_widget);
            ingredientView.setTextViewText(R.id.text_widget_ingredient_name, ingredient.getIngredient());
            ingredientView.setTextViewText(R.id.text_widget_ingredient_quantity, String.valueOf(ingredient.getQuantity()));
            ingredientView.setTextViewText(R.id.text_widget_ingredient_measure, ingredient.getMeasure());

            views.addView(R.id.list_ingredients, ingredientView);
        }


        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}
