package com.edwin.android.thebestbakingapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.activities.StepActivity;
import com.edwin.android.thebestbakingapp.adapter.StepAdapter;
import com.edwin.android.thebestbakingapp.entity.StepDTO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.edwin.android.thebestbakingapp.fragments.RecipeDetailFragment.IntentKey.RECIPE_NAME;
import static com.edwin.android.thebestbakingapp.fragments.RecipeDetailFragment.IntentKey.STEP_LIST;
import static com.edwin.android.thebestbakingapp.fragments.RecipeDetailFragment.IntentKey
        .STEP_SELECTED;

/**
 * Created by Edwin Ramirez Ventur on 5/21/2017.
 */

public class StepFragment extends Fragment implements StepAdapter.StepOnClickHandler {

    public static final String TAG = StepFragment.class.getSimpleName();
    @BindView(R.id.recycler_view_step)
    RecyclerView mRecyclerView;
    private StepAdapter mStepAdapter;
    private List<StepDTO> mSteps;
    private int mStepSelected;
    private String mRecipeName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_step, container, false);
        ButterKnife.bind(this, view);

        Intent intentThatStartedThisActivity = getActivity().getIntent();
        mSteps = intentThatStartedThisActivity.getParcelableArrayListExtra(STEP_LIST
                .name());
        mStepSelected = intentThatStartedThisActivity.getIntExtra(STEP_SELECTED.name(), -1);
        mRecipeName = intentThatStartedThisActivity.getStringExtra(RECIPE_NAME.name());


        Log.d(TAG, "Steps received: " + mSteps);
        Log.d(TAG, "selected step: " + mStepSelected);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(false);
        mStepAdapter = new StepAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mStepAdapter);

        List<Object> items = new ArrayList<>();

        items.add(mSteps.get(mStepSelected).getVideoUrl());
        items.add(mSteps.get(mStepSelected).getDescription());
        items.add("navigation");
        mStepAdapter.setBackingPoster(items, mStepSelected);


        return view;
    }

    @Override
    public void onClick(boolean nextStep) {
        Log.d(TAG, "nextStep: " + nextStep);
        if(nextStep) {
            mStepSelected++;
        }else{
            mStepSelected--;
        }
        Class<StepActivity> destinationActivity = StepActivity.class;
        Intent intent = new Intent(getActivity(), destinationActivity);

        intent.putParcelableArrayListExtra(RecipeDetailFragment.IntentKey.STEP_LIST.name(), new ArrayList<Parcelable>(mSteps));
        intent.putExtra(RECIPE_NAME.name(), mRecipeName);
        intent.putExtra(STEP_SELECTED.name(), mStepSelected);
        getActivity().finish();
        startActivity(intent);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mRecyclerView.setAdapter(null);
        Log.d(TAG, "onDetach called");
    }
}
