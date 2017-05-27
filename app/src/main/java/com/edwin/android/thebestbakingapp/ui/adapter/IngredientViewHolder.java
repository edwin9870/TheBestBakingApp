package com.edwin.android.thebestbakingapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.edwin.android.thebestbakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edwin Ramirez Ventura on 5/27/2017.
 */
class IngredientViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_ingredient_quantity)
    TextView textIngredientQuantity;
    @BindView(R.id.text_ingredient_measure)
    TextView textIngredientMeasure;
    @BindView(R.id.text_ingredient_name)
    TextView textIngredientName;

    IngredientViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
