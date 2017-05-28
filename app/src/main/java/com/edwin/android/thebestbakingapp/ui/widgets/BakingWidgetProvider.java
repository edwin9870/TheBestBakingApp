package com.edwin.android.thebestbakingapp.ui.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.edwin.android.thebestbakingapp.R;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {

    public static final String TAG = BakingWidgetProvider.class.getSimpleName();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Log.d(TAG, "updateAppWidget");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget_provider);
        Intent intent = new Intent(context, ListGridService.class);
        views.setRemoteAdapter(R.id.list_ingredient_name, intent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

