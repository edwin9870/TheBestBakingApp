package com.edwin.android.thebestbakingapp.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.edwin.android.thebestbakingapp.ui.activities.StepActivity;
import com.edwin.android.thebestbakingapp.ui.adapter.StepAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.edwin.android.thebestbakingapp.ui.activities.RecipeDetailActivity.RECIPE_TYPE;
import static com.edwin.android.thebestbakingapp.ui.activities.RecipeDetailActivity.STEP_SELECTED;

/**
 * Created by Edwin Ramirez Ventura on 5/21/2017.
 */

public class StepFragment extends Fragment implements StepAdapter.StepOnClickHandler {

    public static final String TAG = StepFragment.class.getSimpleName();
    public static final String NAVIGATION_ITEM = "navigation";
    @BindView(R.id.recycler_view_step)
    RecyclerView mRecyclerView;
    private StepAdapter mStepAdapter;
    private RecipeDTO mRecipe;
    private Integer mStepSelected;
    private String mRecipeName;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_step, container, false);
        mUnbinder = ButterKnife.bind(this, view);


        if (savedInstanceState != null) {
            Log.d(TAG, "Setting step using savedInstanceState");
            mRecipe = savedInstanceState.getParcelable(RECIPE_TYPE);
            mRecipeName = mRecipe.getName();
            mStepSelected = savedInstanceState.getInt(STEP_SELECTED);

            Log.d(TAG, "mRecipe in savedInstanceState: " + mRecipe);
        }

        if (mRecipe == null || mRecipeName == null) {
            Log.d(TAG, "mRecipe: " + mRecipe);
            Log.d(TAG, "mRecipeName: " + mRecipeName);
            Log.i(TAG, "This fragments has a null list of steps or a null recipe name");
            return view;
        }

        if (mStepSelected == null) {
            mStepSelected = 0;
        }

        Log.d(TAG, "recipe received: " + mRecipe);
        Log.d(TAG, "selected step: " + mStepSelected);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(false);
        mStepAdapter = new StepAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mStepAdapter);

        List<Object> items = new ArrayList<>();


        boolean isIngredientStep = mStepSelected == 0;
        int localStepSelected = mStepSelected;
        if(!isIngredientStep) {
            localStepSelected--;
        }

        Uri videoUri = Uri.parse(mRecipe.getSteps().get(localStepSelected).getVideoUrl());

        if (!isIngredientStep && videoUri != null && !videoUri.toString().isEmpty()) {
            items.add(videoUri);
        }

        if(isIngredientStep) {
         items.addAll(mRecipe.getIngredients());
        } else {
            items.add(mRecipe.getSteps().get(localStepSelected).getDescription());
        }

        boolean isTablet = getActivity().getResources().getBoolean(R.bool.is_tablet);
        if (!isTablet) {
            items.add(NAVIGATION_ITEM);
        }
        mStepAdapter.setBackingPoster(items);

        return view;
    }

    @Override
    public void onClick(boolean nextStep) {
        Log.d(TAG, "nextStep: " + nextStep);
        if (nextStep && mStepSelected < mRecipe.getSteps().size()) {
            mStepSelected++;
        } else if (!nextStep && mStepSelected > 0) {
            mStepSelected--;
        } else {
            Log.d(TAG, "There is not more page to go");
            Toast.makeText(getActivity(), getActivity().getString(R.string.no_more_page_to_go),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Class<StepActivity> destinationActivity = StepActivity.class;
        Intent intent = new Intent(getActivity(), destinationActivity);

        intent.putExtra(RECIPE_TYPE, mRecipe);
        intent.putExtra(STEP_SELECTED, mStepSelected);
        getActivity().finish();
        Log.d(TAG, "Going to the next activity");
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");

        if (mStepAdapter != null) {
            mStepAdapter.playVideo();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");

        if (mStepAdapter != null) {
            mStepAdapter.pauseVideo();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView called");
        mRecyclerView.setAdapter(null);
        mUnbinder.unbind();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECIPE_TYPE, mRecipe);
        outState.putInt(STEP_SELECTED, mStepSelected);
    }


    public void setRecipeName(String recipeName) {
        this.mRecipeName = recipeName;
    }

    public void setRecipe(RecipeDTO recipe) {
        this.mRecipe = recipe;
    }

    public void setStepSelected(int stepSelected) {
        this.mStepSelected = stepSelected;
    }
}
