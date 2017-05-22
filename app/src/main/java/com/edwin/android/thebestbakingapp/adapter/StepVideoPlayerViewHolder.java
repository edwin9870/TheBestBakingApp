package com.edwin.android.thebestbakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.edwin.android.thebestbakingapp.R;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edwin Ramirez Ventur on 5/22/2017.
 */

public class StepVideoPlayerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.playerView)
    SimpleExoPlayerView mPlayerView;

    StepVideoPlayerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
