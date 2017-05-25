package com.edwin.android.thebestbakingapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.StepDTO;
import com.edwin.android.thebestbakingapp.ui.fragments.StepFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.edwin.android.thebestbakingapp.ui.fragments.RecipeDetailFragment.IntentKey
        .RECIPE_NAME;
import static com.edwin.android.thebestbakingapp.ui.fragments.RecipeDetailFragment.IntentKey
        .STEP_LIST;
import static com.edwin.android.thebestbakingapp.ui.fragments.RecipeDetailFragment.IntentKey.STEP_SELECTED;

public class StepActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_recipe_step)
    Toolbar mToolbarRecipeStep;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mUnbinder = ButterKnife.bind(this);
        setupBar();

        if(savedInstanceState == null) {

            FragmentManager fragmentManager = getSupportFragmentManager();

            StepFragment stepFragment = new StepFragment();

            List<StepDTO> mSteps = getIntent().getParcelableArrayListExtra(STEP_LIST
                    .name());
            String mRecipeName = getIntent().getStringExtra(RECIPE_NAME.name());

            if(getIntent().hasExtra(STEP_SELECTED.name())) {
                stepFragment.setStepSelected(getIntent().getIntExtra(STEP_SELECTED.name(), 0));
            }

            stepFragment.setRecipeName(mRecipeName);
            stepFragment.setSteps(mSteps);
            fragmentManager.beginTransaction()
                    .add(R.id.recipe_step_fragment, stepFragment)
                    .commit();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    private void setupBar() {
        Intent intentThatStartedThisActivity = getIntent();
        String recipeName = intentThatStartedThisActivity.getStringExtra(RECIPE_NAME.name());
        mToolbarRecipeStep.setTitle(recipeName);
        setSupportActionBar(mToolbarRecipeStep);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
