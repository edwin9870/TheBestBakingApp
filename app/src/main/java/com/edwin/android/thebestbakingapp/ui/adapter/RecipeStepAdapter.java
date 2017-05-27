package com.edwin.android.thebestbakingapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.StepDTO;

import java.util.List;

/**
 * Created by Edwin Ramirez Ventur on 5/19/2017.
 */

public class RecipeStepAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final String TAG = RecipeStepAdapter.class.getSimpleName();
    public static final int VIEW_TYPE_STEP = 5452;
    public static final int VIEW_TYPE_INGREDIENT = 11557;

    private RecipeStepOnClickHandler mClickHandler;
    private List<Object> mItems;

    public RecipeStepAdapter(RecipeStepOnClickHandler clickHandler) {
        this.mClickHandler = clickHandler;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerView.ViewHolder viewHolder;

        View view = inflater.inflate(R.layout.item_recipe_step, viewGroup, false);
        viewHolder = new RecipeStepItemViewHolder(view, mClickHandler);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        configureViewHolder((RecipeStepItemViewHolder) holder, position);

    }

    private void configureViewHolder(RecipeStepItemViewHolder holder, int position) {
        String label;
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_STEP:
                label = ((StepDTO) mItems.get(position)).getShortDescription();
                break;
            case VIEW_TYPE_INGREDIENT:
                label = (String) mItems.get(position);
                break;
            default:
                throw new IllegalArgumentException("Invalid element type");
        }

        Log.d(TAG, "label: " + label);
        holder.mRecipeStepDescriptionTextView.setText(label);
    }


    @Override
    public int getItemCount() {
        if (null == mItems) {
            return 0;
        }

        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mItems.get(position) instanceof StepDTO) {
            return VIEW_TYPE_STEP;
        }else if(mItems.get(position) instanceof String) {
            return VIEW_TYPE_INGREDIENT;
        }else {
            throw new IllegalArgumentException("Invalid element type");
        }

    }

    public void setItems(List<Object> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    public interface RecipeStepOnClickHandler {
        void onClick(int position, int recipeItemViewType);
    }

}
