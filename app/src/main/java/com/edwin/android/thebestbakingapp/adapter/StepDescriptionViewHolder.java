package com.edwin.android.thebestbakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.edwin.android.thebestbakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edwin Ramirez Ventur on 5/22/2017.
 */

public class StepDescriptionViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.recipe_step_description_text_view)
    TextView mRecipeStepDescriptionTextView;

    StepDescriptionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
