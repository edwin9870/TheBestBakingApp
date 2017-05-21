package com.edwin.android.thebestbakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.StepsDTO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edwin Ramirez Ventur on 5/19/2017.
 */

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter
        .RecipeStepViewHolder> {


    public static final String TAG = RecipeStepAdapter.class.getSimpleName();
    private StepsDTO[] mSteps;
    private Context mContext;
    private RecipeStepOnClickHandler mClickHandler;

    public RecipeStepAdapter(Context context, RecipeStepOnClickHandler clickHandler) {
        this.mContext = context;
        this.mClickHandler = clickHandler;
    }

    public interface RecipeStepOnClickHandler {
        void onClick(StepsDTO step);
    }

    class RecipeStepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.recipe_step_description)
        TextView mRecipeStepDescriptionTextView;

        RecipeStepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            StepsDTO step = mSteps[adapterPosition];
            mClickHandler.onClick(step);
        }
    }


    @Override
    public RecipeStepViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int idLayoutForMovieItem = R.layout.item_recipe_step;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(idLayoutForMovieItem, viewGroup, false);
        return new RecipeStepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeStepViewHolder holder, int position) {
        StepsDTO recipe = mSteps[position];
        Log.d(TAG, "Recipe short description: " + recipe.getShortDescription());
        holder.mRecipeStepDescriptionTextView.setText(recipe.getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (null == mSteps) {
            return 0;
        }

        return mSteps.length;
    }

    public void setBackingPoster(StepsDTO[] steps) {
        this.mSteps = steps;
        notifyDataSetChanged();
    }
}
