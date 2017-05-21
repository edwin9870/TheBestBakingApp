package com.edwin.android.thebestbakingapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.StepDTO;
import com.edwin.android.thebestbakingapp.util.Constants;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edwin Ramirez Ventur on 5/21/2017.
 */

public class StepFragment extends Fragment {

    public static final String TAG = StepFragment.class.getSimpleName();
    @BindView(R.id.playerView)
    SimpleExoPlayerView simpleExoPlayerView;
    private StepDTO mStep;
    private String mUserAgent;
    private SimpleExoPlayer mExoPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_step, container, false);
        ButterKnife.bind(this, view);

        Intent intentThatStartedThisActivity = getActivity().getIntent();
        mStep = intentThatStartedThisActivity.getParcelableExtra(Constants.Intent.STEP_TYPE);

        Log.d(TAG, "Step received: " + mStep);

        simpleExoPlayerView.requestFocus();

        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, new DefaultLoadControl());
        simpleExoPlayerView.setPlayer(mExoPlayer);
        mExoPlayer.setPlayWhenReady(true);
        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        mUserAgent = "ClassicalMusicQuiz";
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(mStep.getVideoUrl()),
                new DefaultDataSourceFactory(
                        getActivity(), mUserAgent), extractorsFactory, null, null);
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);

        return view;
    }

}
