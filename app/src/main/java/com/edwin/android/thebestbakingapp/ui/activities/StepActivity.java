package com.edwin.android.thebestbakingapp.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.edwin.android.thebestbakingapp.ui.fragments.StepFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.edwin.android.thebestbakingapp.ui.activities.RecipeDetailActivity.RECIPE_TYPE;
import static com.edwin.android.thebestbakingapp.ui.activities.RecipeDetailActivity.STEP_SELECTED;

public class StepActivity extends AppCompatActivity {

    public static final String TAG = StepActivity.class.getSimpleName();
    @BindView(R.id.toolbar_recipe_step)
    Toolbar mToolbarRecipeStep;
    private Unbinder mUnbinder;
    private RecipeDTO recipe;
    private String mRecipeName;
    private int mStepSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mUnbinder = ButterKnife.bind(this);


        Log.d(TAG, "Configuring setup bar");

        FragmentManager fragmentManager = getSupportFragmentManager();
        StepFragment stepFragment = new StepFragment();

        Log.d(TAG, "Checking if savedInstanceState is null");
        if (savedInstanceState != null) {
            Log.d(TAG, "Settings variables using savedInstanceState: " + savedInstanceState);

            recipe = savedInstanceState.getParcelable(RecipeDetailActivity.RECIPE_TYPE);
            mRecipeName = recipe.getName();
            mStepSelected = savedInstanceState.getInt(STEP_SELECTED);

            Log.d(TAG, "recipe in savedInstanceState: " + recipe);
        } else {
            Log.d(TAG, "Settings variables using intent");
            recipe = getIntent().getParcelableExtra(RecipeDetailActivity.RECIPE_TYPE);
            mRecipeName = recipe.getName();

            if (getIntent().hasExtra(STEP_SELECTED)) {
                mStepSelected = getIntent().getIntExtra(STEP_SELECTED, 0);
            }

        }
        setupBar();


        stepFragment.setRecipeName(mRecipeName);
        stepFragment.setRecipe(recipe);
        stepFragment.setStepSelected(mStepSelected);

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.recipe_step_fragment, stepFragment)
                    .commit();
            Log.d(TAG, "fragmentManager.beginTransaction() called");
        }
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy called");
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECIPE_TYPE, recipe);
        outState.putInt(STEP_SELECTED, mStepSelected);
    }


    private void setupBar() {
        mToolbarRecipeStep.setTitle(recipe.getName());
        setSupportActionBar(mToolbarRecipeStep);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
