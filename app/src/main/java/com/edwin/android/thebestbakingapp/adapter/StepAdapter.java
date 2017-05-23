package com.edwin.android.thebestbakingapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwin.android.thebestbakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.List;

/**
 * Created by Edwin Ramirez Ventur on 5/22/2017.
 */

public class StepAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int VIDEO_PLAYER_VIEW_TYPE = 5454;
    private static final int STEP_DESCRIPTION_VIEW_TYPE = 7771;
    private static final int NEXT_PREVIOUS_VIEW_TYPE = 548954;
    private static final String USER_AGENT = "The Best Baking App";

    public static final String TAG = StepAdapter.class.getSimpleName();

    private List<Object> mItems;
    private Context mContext;
    private StepOnClickHandler mClickHandler;
    private int mStepSelected;
    private SimpleExoPlayer mExoPlayer;


    public StepAdapter(Context context, StepOnClickHandler clickHandler) {
        this.mContext = context;
        this.mClickHandler = clickHandler;
    }

    public interface StepOnClickHandler {
        void onClick(boolean nextStep);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (viewType) {
            case VIDEO_PLAYER_VIEW_TYPE:
                view = inflater.inflate(R.layout.item_video_player, viewGroup, false);
                viewHolder = new StepVideoPlayerViewHolder(view);
                break;
            case STEP_DESCRIPTION_VIEW_TYPE:
                view = inflater.inflate(R.layout.item_step_description, viewGroup, false);
                viewHolder = new StepDescriptionViewHolder(view);
                break;
            case NEXT_PREVIOUS_VIEW_TYPE:
                view = inflater.inflate(R.layout.item_next_previous, viewGroup, false);
                viewHolder = new StepNextPreviousViewHolder(view);
                break;
            default:
                throw new IllegalArgumentException("Invalid viewType: " + viewType);
        }

        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return VIDEO_PLAYER_VIEW_TYPE;
            case 1:
                return STEP_DESCRIPTION_VIEW_TYPE;
            case 2:
                return NEXT_PREVIOUS_VIEW_TYPE;
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIDEO_PLAYER_VIEW_TYPE:
                configureViewHolder((StepVideoPlayerViewHolder) holder, position);
                break;
            case STEP_DESCRIPTION_VIEW_TYPE:
                configureViewHolder((StepDescriptionViewHolder) holder, position);
                break;
            case NEXT_PREVIOUS_VIEW_TYPE:
                configureViewHolder((StepNextPreviousViewHolder) holder, position);
                break;
            default:
                throw new IllegalArgumentException("Invalid item view type: " + holder.getItemViewType());
        }
    }

    private void configureViewHolder(StepVideoPlayerViewHolder holder, int position) {
        holder.mPlayerView.requestFocus();
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector, new
                DefaultLoadControl());
        holder.mPlayerView.setPlayer(mExoPlayer);
        mExoPlayer.setPlayWhenReady(true);
        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        String videoUrl = (String) mItems.get(position);

        Log.d(TAG, "Video url to play: "+ videoUrl);
        Log.d(TAG, "Position: "+ position);
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(videoUrl),
                new DefaultDataSourceFactory(mContext, USER_AGENT), extractorsFactory, null, null);
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);
    }

    private void configureViewHolder(StepDescriptionViewHolder holder, int position) {
        String stepDescription = (String) mItems.get(position);
        Log.d(TAG, "stepDescription: "+ stepDescription);
        Log.d(TAG, "position: "+ position);
        holder.mRecipeStepDescriptionTextView.setText(stepDescription);
    }

    private void configureViewHolder(StepNextPreviousViewHolder holder, int position) {
        holder.mNextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Next button clicked");
                mClickHandler.onClick(true);
            }
        });

        holder.mPreviousStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Previous button clicked");
                mClickHandler.onClick(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (null == mItems) {
            return 0;
        }

        return mItems.size();
    }

    public void setBackingPoster(List<Object> items, int stepSelected) {
        this.mItems = items;
        this.mStepSelected = stepSelected;
        notifyDataSetChanged();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        Log.d(TAG, "onDetachedFromRecyclerView called");
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }


}
