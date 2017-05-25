package com.edwin.android.thebestbakingapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.edwin.android.thebestbakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.edwin.android.thebestbakingapp.ui.fragments.RecipeDetailFragment.IntentKey
        .RECIPE_NAME;

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
