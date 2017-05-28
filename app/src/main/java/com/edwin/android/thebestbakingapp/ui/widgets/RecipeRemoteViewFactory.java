package com.edwin.android.thebestbakingapp.ui.widgets;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.edwin.android.thebestbakingapp.R;
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
        return mRecipes.length;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.d(TAG, "getViewAt: "+ position);
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.item_ingredient_widget);

        views.setTextViewText(R.id.text_widget_recipe_name, mRecipes[position].getName());
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
