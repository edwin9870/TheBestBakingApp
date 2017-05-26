package com.edwin.android.thebestbakingapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.StepDTO;
import com.edwin.android.thebestbakingapp.ui.fragments.StepFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.edwin.android.thebestbakingapp.ui.fragments.RecipeDetailFragment.IntentKey
        .RECIPE_NAME;
import static com.edwin.android.thebestbakingapp.ui.fragments.RecipeDetailFragment.IntentKey
        .STEP_LIST;
import static com.edwin.android.thebestbakingapp.ui.fragments.RecipeDetailFragment.IntentKey
        .STEP_SELECTED;

public class StepActivity extends AppCompatActivity {

    public static final String TAG = StepActivity.class.getSimpleName();
    @BindView(R.id.toolbar_recipe_step)
    Toolbar mToolbarRecipeStep;
    private Unbinder mUnbinder;
    private List<StepDTO> mSteps;
    private String mRecipeName;
    private int mStepSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mUnbinder = ButterKnife.bind(this);


        Log.d(TAG, "Configuring setup bar");
        setupBar();



        FragmentManager fragmentManager = getSupportFragmentManager();
        StepFragment stepFragment = new StepFragment();

        Log.d(TAG, "Checking if savedInstanceState is null");
        if (savedInstanceState != null) {
            Log.d(TAG, "Settings variables using savedInstanceState: " + savedInstanceState);

            mSteps = savedInstanceState.getParcelableArrayList(STEP_LIST.name());
            mRecipeName = savedInstanceState.getString(RECIPE_NAME.name());
            mStepSelected = savedInstanceState.getInt(STEP_SELECTED.name());

            Log.d(TAG, "mSteps in savedInstanceState: "+mSteps);
        } else {
            Log.d(TAG, "Settings variables using intent");
            mSteps = getIntent().getParcelableArrayListExtra(STEP_LIST
                    .name());
            mRecipeName = getIntent().getStringExtra(RECIPE_NAME.name());

            if (getIntent().hasExtra(STEP_SELECTED.name())) {
                mStepSelected = getIntent().getIntExtra(STEP_SELECTED.name(), 0);
                stepFragment.setStepSelected(mStepSelected);
            }

        }


        stepFragment.setRecipeName(mRecipeName);
        stepFragment.setSteps(mSteps);

        if(savedInstanceState == null) {
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
        outState.putParcelableArrayList(STEP_LIST.name(), new ArrayList<Parcelable>(mSteps));
        outState.putString(RECIPE_NAME.name(), mRecipeName);
        outState.putInt(STEP_SELECTED.name(), mStepSelected);
    }



    private void setupBar() {
        Intent intentThatStartedThisActivity = getIntent();
        String recipeName = intentThatStartedThisActivity.getStringExtra(RECIPE_NAME.name());
        mToolbarRecipeStep.setTitle(recipeName);
        setSupportActionBar(mToolbarRecipeStep);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
