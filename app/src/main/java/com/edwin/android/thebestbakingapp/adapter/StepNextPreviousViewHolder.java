package com.edwin.android.thebestbakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.edwin.android.thebestbakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edwin Ramirez Ventur on 5/22/2017.
 */

public class StepNextPreviousViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.previous_step_button)
    Button mPreviousStepButton;
    @BindView(R.id.next_step_button)
    Button mNextStepButton;

    StepNextPreviousViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
