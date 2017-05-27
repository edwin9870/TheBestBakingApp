package com.edwin.android.thebestbakingapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.edwin.android.thebestbakingapp.ui.adapter.RecipeStepAdapter;
import com.edwin.android.thebestbakingapp.ui.fragments.StepFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeStepAdapter
        .RecipeStepOnClickHandler {


    public static final String RECIPE_TYPE = "RECIPE_TYPE";
    public static final String STEP_SELECTED = "STEP_SELECTED";

    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;
    private RecipeDTO mRecipe;
    private Unbinder mUnbinder;
    private boolean mTwopane;
    public static final String TAG = RecipeDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        mUnbinder = ButterKnife.bind(this);
        mRecipe = getIntent().getParcelableExtra(RECIPE_TYPE);
        setupBar();

        if (findViewById(R.id.master_detail_recipe) != null) {
            mTwopane = true;

            Log.d(TAG, "Checking if savedInstance is null");
            if (savedInstanceState == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                Log.d(TAG, "Adding StepFragment");
                StepFragment fragment = new StepFragment();

                fragment.setRecipe(mRecipe);
                fragment.setRecipeName(mRecipe.getName());

                fragmentManager.beginTransaction().add(R.id.step_detail, fragment)
                        .commit();

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void onClick(int position, int recipeItemViewType) {
        Log.d(TAG, "step position clicked: " + position);
        Log.d(TAG, "recipeItemViewType: " + recipeItemViewType);
        if (mTwopane) {
            StepFragment stepFragment = new StepFragment();
            stepFragment.setRecipe(mRecipe);
            stepFragment.setRecipeName(mRecipe.getName());
            stepFragment.setStepSelected(position);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_detail, stepFragment)
                    .commit();
        } else {
            Class<? extends AppCompatActivity> destinationActivity;
            Intent intent;

            destinationActivity = StepActivity.class;
            intent = new Intent(this, destinationActivity);

            Log.d(TAG, "mRecipe: " + mRecipe);
            Log.d(TAG, "step selected: "+ position);
            intent.putExtra(RECIPE_TYPE, mRecipe);
            intent.putExtra(STEP_SELECTED, position);
            startActivity(intent);

        }
    }

    private void setupBar() {
        mToolbar.setTitle(mRecipe.getName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
