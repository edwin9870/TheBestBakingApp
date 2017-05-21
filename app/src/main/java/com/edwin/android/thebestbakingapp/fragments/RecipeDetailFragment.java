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
import com.edwin.android.thebestbakingapp.adapters.RecipeStepAdapter;
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.edwin.android.thebestbakingapp.entity.StepsDTO;
import com.edwin.android.thebestbakingapp.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edwin Ramirez Ventur on 5/20/2017.
 */

public class RecipeDetailFragment extends Fragment implements RecipeStepAdapter.RecipeStepOnClickHandler {

    @BindView(R.id.recycler_view_recipe_step)
    RecyclerView mRecyclerView;
    private RecipeStepAdapter mRecipeStepAdapter;
    RecipeDTO mRecipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

        Intent intentThatStartedThisActivity = getActivity().getIntent();
        mRecipe = intentThatStartedThisActivity.getParcelableExtra(Constants.Intent.RECIPE);

        Log.d(RecipeDetailFragment.class.getSimpleName(), "Recipe received: "+ mRecipe);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(false);
        mRecipeStepAdapter = new RecipeStepAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mRecipeStepAdapter);

        StepsDTO[] steps = new StepsDTO[mRecipe.getSteps().size()];
        mRecipe.getSteps().toArray(steps);
        mRecipeStepAdapter.setBackingPoster(steps);

        return view;
    }

    @Override
    public void onClick(StepsDTO step) {

    }
}
