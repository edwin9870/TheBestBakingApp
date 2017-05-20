package com.edwin.android.thebestbakingapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.adapters.BackingPosterAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BackingPosterAdapter.BackingPosterOnClickHandler {

    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.recycler_view_baking)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;
    private BackingPosterAdapter mBackingPosterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(false);

        mBackingPosterAdapter = new BackingPosterAdapter(this, this);

        Log.i(TAG, "Calling the adapter");
        mRecyclerView.setAdapter(mBackingPosterAdapter);

        List<String> backingPoster = new ArrayList<>();
        backingPoster.add("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");
        backingPoster.add("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");
        backingPoster.add("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");
        backingPoster.add("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");
        backingPoster.add("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");
        backingPoster.add("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4");
//        backingPoster.add("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");

        mBackingPosterAdapter.setBackingPoster(backingPoster);


    }

    @Override
    public void onClick(String backingPoster) {
        Log.d(TAG, "Baking poster clicked!");

    }
}
