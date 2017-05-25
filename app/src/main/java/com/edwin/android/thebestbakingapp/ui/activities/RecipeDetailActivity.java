package com.edwin.android.thebestbakingapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.edwin.android.thebestbakingapp.ui.fragments.RecipeFragment;
import com.edwin.android.thebestbakingapp.ui.fragments.StepFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;
    private RecipeDTO mRecipe;
    private Unbinder mUnbinder;
    private boolean mTwopane;
    public static final String TAG =RecipeDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        mUnbinder = ButterKnife.bind(this);
        setupBar();

        if(findViewById(R.id.master_detail_recipe) != null) {
            mTwopane = true;

            if(savedInstanceState == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                Log.d(TAG, "Adding StepFragment");
                StepFragment fragment = new StepFragment();
                mRecipe = getIntent().getParcelableExtra(RecipeFragment.IntentKey.RECIPE_TYPE.name());
                fragment.setSteps(mRecipe.getSteps());
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


    private void setupBar() {
        Intent intentThatStartedThisActivity = getIntent();
        mRecipe = intentThatStartedThisActivity.getParcelableExtra(RecipeFragment.IntentKey.RECIPE_TYPE.name());
        mToolbar.setTitle(mRecipe.getName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
