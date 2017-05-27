package com.edwin.android.thebestbakingapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.edwin.android.thebestbakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.edwin.android.thebestbakingapp.ui.adapter.RecipeStepAdapter.VIEW_TYPE_STEP;

/**
 * Created by Edwin Ramirez Ventura on 5/27/2017.
 */
class RecipeStepItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final RecipeStepAdapter.RecipeStepOnClickHandler mClickHandler;
    @BindView(R.id.text_recipe_item_description)
    TextView mRecipeStepDescriptionTextView;

    RecipeStepItemViewHolder(View itemView, RecipeStepAdapter.RecipeStepOnClickHandler clickHandler) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        this.mClickHandler = clickHandler;
    }

    @Override
    public void onClick(View v) {
        mClickHandler.onClick(getAdapterPosition(), VIEW_TYPE_STEP);
    }
}
