package com.edwin.android.thebestbakingapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edwin.android.thebestbakingapp.R;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edwin Ramirez Ventur on 5/22/2017.
 */

public class StepVideoPlayerViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = StepVideoPlayerViewHolder.class.getSimpleName();
    @BindView(R.id.playerView)
    SimpleExoPlayerView mPlayerView;

    StepVideoPlayerViewHolder(View itemView, Activity activity) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "Device is in landscape");
            Log.d(TAG, "Setting video player to match all the screen");
            ViewGroup.LayoutParams layoutParams = mPlayerView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            Point size = getDeviceSize(activity);
            layoutParams.height =  size.y;
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mPlayerView.setLayoutParams(layoutParams);

        }

    }

    @NonNull
    private Point getDeviceSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

}
