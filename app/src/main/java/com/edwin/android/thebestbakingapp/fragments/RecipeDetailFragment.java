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
import com.edwin.android.thebestbakingapp.adapter.RecipeStepAdapter;
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.edwin.android.thebestbakingapp.entity.StepDTO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.edwin.android.thebestbakingapp.fragments.RecipeDetailFragment.IntentKey
        .RECIPE_NAME;
import static com.edwin.android.thebestbakingapp.fragments.RecipeDetailFragment.IntentKey
        .STEP_SELECTED;

/**
 * Created by Edwin Ramirez Ventur on 5/20/2017.
 */

public class RecipeDetailFragment extends Fragment implements RecipeStepAdapter
        .RecipeStepOnClickHandler {


    public static final String TAG = RecipeDetailFragment.class.getSimpleName();
    private Unbinder mUnbinder;

    public enum IntentKey {
        STEP_LIST, RECIPE_NAME, STEP_SELECTED;
    }

    @BindView(R.id.recycler_view_recipe_step)
    RecyclerView mRecyclerView;
    private RecipeStepAdapter mRecipeStepAdapter;
    RecipeDTO mRecipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        Intent intentThatStartedThisActivity = getActivity().getIntent();
        mRecipe = intentThatStartedThisActivity.getParcelableExtra(RecipeFragment.IntentKey.RECIPE_TYPE.name());

        Log.d(RecipeDetailFragment.class.getSimpleName(), "Recipe received: " + mRecipe);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(false);
        mRecipeStepAdapter = new RecipeStepAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mRecipeStepAdapter);

        StepDTO[] steps = new StepDTO[mRecipe.getSteps().size()];
        mRecipe.getSteps().toArray(steps);
        mRecipeStepAdapter.setBackingPoster(steps);

        return view;
    }

    @Override
    public void onClick(int position) {
        Log.d(TAG, "step position clicked: " + position);
        Class<StepActivity> destinationActivity = StepActivity.class;
        Intent intent = new Intent(RecipeDetailFragment.this.getActivity(), destinationActivity);

        intent.putParcelableArrayListExtra(IntentKey.STEP_LIST.name(), new ArrayList<Parcelable>(mRecipe.getSteps()));
        intent.putExtra(RECIPE_NAME.name(), mRecipe.getName());
        intent.putExtra(STEP_SELECTED.name(), position);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
