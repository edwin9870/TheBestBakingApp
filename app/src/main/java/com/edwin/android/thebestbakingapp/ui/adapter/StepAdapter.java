package com.edwin.android.thebestbakingapp.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.IngredientDTO;
import com.edwin.android.thebestbakingapp.ui.fragments.StepFragment;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
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
    public static final int INGREDIENT_VIEW_TYPE = 54111;

    public static final String TAG = StepAdapter.class.getSimpleName();

    private List<Object> mItems;
    private Activity mActivity;
    private StepOnClickHandler mClickHandler;
    private SimpleExoPlayer mExoPlayer;


    public StepAdapter(Activity activity, StepOnClickHandler clickHandler) {
        this.mActivity = activity;
        this.mClickHandler = clickHandler;
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
                viewHolder = new StepVideoPlayerViewHolder(view, mActivity);
                break;
            case STEP_DESCRIPTION_VIEW_TYPE:
                view = inflater.inflate(R.layout.item_step_description, viewGroup, false);
                viewHolder = new StepDescriptionViewHolder(view);
                Log.d(TAG, "Description view type inflated");
                break;
            case NEXT_PREVIOUS_VIEW_TYPE:
                view = inflater.inflate(R.layout.item_navigation_step, viewGroup, false);
                viewHolder = new StepNavigationViewHolder(view);
                break;
            case INGREDIENT_VIEW_TYPE:
                view = inflater.inflate(R.layout.item_ingredient, viewGroup, false);
                viewHolder = new IngredientViewHolder(view);
                break;
            default:
                throw new IllegalArgumentException("Invalid viewType: " + viewType);
        }

        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position) instanceof Uri) {
            return VIDEO_PLAYER_VIEW_TYPE;
        } else if (mItems.get(position) instanceof IngredientDTO) {
            return INGREDIENT_VIEW_TYPE;
        } else if (mItems.get(position) instanceof String && mItems.get(position).equals
                (StepFragment.NAVIGATION_ITEM)) {
            return NEXT_PREVIOUS_VIEW_TYPE;
        } else if (mItems.get(position) instanceof String) {
            return STEP_DESCRIPTION_VIEW_TYPE;
        } else {
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
                configureViewHolder((StepNavigationViewHolder) holder, position);
                break;
            case INGREDIENT_VIEW_TYPE:
                configureViewHolder((IngredientViewHolder) holder, position);
                break;
            default:
                throw new IllegalArgumentException("Invalid item view type: " + holder
                        .getItemViewType());
        }
    }

    @Override
    public int getItemCount() {
        if (null == mItems) {
            return 0;
        }

        Log.d(TAG, "items size: " + mItems.size());
        return mItems.size();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        Log.d(TAG, "onDetachedFromRecyclerView called");
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    public void pauseVideo() {
        if (mExoPlayer == null) {
            return;
        }
        mExoPlayer.setPlayWhenReady(false);
    }

    public void playVideo() {
        if (mExoPlayer == null) {
            return;
        }
        mExoPlayer.setPlayWhenReady(true);
    }

    public void setBackingPoster(List<Object> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    public interface StepOnClickHandler {
        void onClick(boolean nextStep);
    }

    private void configureViewHolder(StepVideoPlayerViewHolder holder, int position) {
        holder.mPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        holder.mPlayerView.requestFocus();
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(mActivity, trackSelector, new
                DefaultLoadControl());
        holder.mPlayerView.setPlayer(mExoPlayer);
        mExoPlayer.setPlayWhenReady(true);
        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        Uri videoUrl = (Uri) mItems.get(position);

        Log.d(TAG, "Video url to play: " + videoUrl);
        Log.d(TAG, "Position: " + position);
        String userAg = mActivity.getString(R.string.player_user_agent);
        MediaSource mediaSource = new ExtractorMediaSource(videoUrl,
                new DefaultDataSourceFactory(mActivity, userAg), extractorsFactory, null, null);
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);
    }

    private void configureViewHolder(StepDescriptionViewHolder holder, int position) {
        String stepDescription = (String) mItems.get(position);
        Log.d(TAG, "stepDescription: " + stepDescription);
        Log.d(TAG, "position: " + position);
        holder.mRecipeStepDescriptionTextView.setText(stepDescription);
    }

    private void configureViewHolder(StepNavigationViewHolder holder, int position) {
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

    private void configureViewHolder(IngredientViewHolder holder, int position) {
        IngredientDTO ingredient = (IngredientDTO) mItems.get(position);
        Log.d(TAG, "ingredient: " + ingredient);
        Log.d(TAG, "position: " + position);
        holder.textIngredientName.setText(ingredient.getIngredient());
        holder.textIngredientMeasure.setText(ingredient.getMeasure());
        holder.textIngredientQuantity.setText(String.valueOf(ingredient.getQuantity()));
    }


}
