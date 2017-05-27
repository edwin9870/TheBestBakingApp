package com.edwin.android.thebestbakingapp.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.edwin.android.thebestbakingapp.entity.StepDTO;
import com.edwin.android.thebestbakingapp.ui.activities.RecipeDetailActivity;
import com.edwin.android.thebestbakingapp.ui.adapter.RecipeStepAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.edwin.android.thebestbakingapp.ui.activities.RecipeDetailActivity.RECIPE_TYPE;

/**
 * Created by Edwin Ramirez Ventur on 5/20/2017.
 */

public class RecipeDetailFragment extends Fragment implements RecipeStepAdapter
        .RecipeStepOnClickHandler {


    public static final String TAG = RecipeDetailFragment.class.getSimpleName();
    private Unbinder mUnbinder;
    private RecipeStepAdapter.RecipeStepOnClickHandler mActivityCallBack;

    @BindView(R.id.recycler_view_recipe_step)
    RecyclerView mRecyclerView;
    private RecipeStepAdapter mRecipeStepAdapter;
    RecipeDTO mRecipe;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivityCallBack = (RecipeStepAdapter.RecipeStepOnClickHandler) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        Intent intentThatStartedThisActivity = getActivity().getIntent();
        mRecipe = intentThatStartedThisActivity.getParcelableExtra(RECIPE_TYPE);

        Log.d(RecipeDetailFragment.class.getSimpleName(), "Recipe received: " + mRecipe);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(false);
        mRecipeStepAdapter = new RecipeStepAdapter(this);
        mRecyclerView.setAdapter(mRecipeStepAdapter);

        StepDTO[] steps = new StepDTO[mRecipe.getSteps().size()];
        mRecipe.getSteps().toArray(steps);

        List<Object> items = new ArrayList<>();
        String ingredientLabel = getActivity().getString(R.string.ingredient_label);
        items.add(ingredientLabel);
        items.addAll(mRecipe.getSteps());

        mRecipeStepAdapter.setItems(items);

        return view;
    }

    @Override
    public void onClick(int position, int recipeItemViewType) {
        mActivityCallBack.onClick(position, recipeItemViewType);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
