package com.edwin.android.thebestbakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_recipe_step)
    Toolbar mToolbarRecipeStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        ButterKnife.bind(this);

        setupBar();
    }

    private void setupBar() {
        Intent intentThatStartedThisActivity = getIntent();
        String recipeName = intentThatStartedThisActivity.getStringExtra(Constants.Intent.RECIPE_NAME);
        mToolbarRecipeStep.setTitle(recipeName);
        setSupportActionBar(mToolbarRecipeStep);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
