package com.edwin.android.thebestbakingapp.ui.adapter;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

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

        Point size = getDeviceSize(activity);

        boolean isTablet = activity.getResources().getBoolean(R.bool.is_tablet);
        if (!isTablet && activity.getResources().getConfiguration().orientation == Configuration
                .ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "Device is in landscape");

            setFullScreen(itemView, (AppCompatActivity) activity);


            Log.d(TAG, "Setting video player to match all the screen");
            ViewGroup.LayoutParams layoutParams = mPlayerView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            Log.d(TAG, "Device height: "+size.y);
            layoutParams.height = size.y;
            mPlayerView.setLayoutParams(layoutParams);

        }

        if(isTablet) {
            ViewGroup.LayoutParams layoutParams = mPlayerView.getLayoutParams();
            layoutParams.height = (int)(size.y*0.6);
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

    private void setFullScreen(View itemView, AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getSupportActionBar().hide();

            itemView.getRootView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }
}
