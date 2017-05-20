package com.edwin.android.thebestbakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edwin.android.thebestbakingapp.R;
import com.edwin.android.thebestbakingapp.entity.RecipeDTO;
import com.edwin.android.thebestbakingapp.util.ImageUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Edwin Ramirez Ventur on 5/19/2017.
 */

public class BackingPosterAdapter extends RecyclerView.Adapter<BackingPosterAdapter
        .BackingPosterViewHolder> {


    public static final String TAG = BackingPosterAdapter.class.getSimpleName();
    private RecipeDTO[] mBackingPoster;
    private Context mContext;
    private BackingPosterOnClickHandler mClickHandler;

    public BackingPosterAdapter(Context context, BackingPosterOnClickHandler clickHandler) {
        this.mContext = context;
        this.mClickHandler = clickHandler;
    }

    public interface BackingPosterOnClickHandler {
        void onClick(RecipeDTO movie);
    }

    class BackingPosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image_baking_poster)
        ImageView mBakingPosterImageView;
        @BindView(R.id.recipe_name_text_view)
        TextView mRecipeNameTextView;

        BackingPosterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            RecipeDTO movie = mBackingPoster[adapterPosition];
            mClickHandler.onClick(movie);
        }
    }


    @Override
    public BackingPosterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int idLayoutForMovieItem = R.layout.item_baking_list;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(idLayoutForMovieItem, viewGroup, false);
        return new BackingPosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BackingPosterViewHolder holder, int position) {
        RecipeDTO imageUrl = mBackingPoster[position];
        Log.d(TAG, "imageUrl: " + imageUrl);
        Picasso picasso = Picasso.with(mContext);
        picasso.load(ImageUtil.getImagePosterResource()).fit().into(holder.mBakingPosterImageView);
        holder.mRecipeNameTextView.setText(imageUrl.getName());
    }

    @Override
    public int getItemCount() {
        if (null == mBackingPoster) {
            return 0;
        }

        return mBackingPoster.length;
    }

    public void setBackingPoster(RecipeDTO[] backingPoster) {
        this.mBackingPoster = backingPoster;
        notifyDataSetChanged();
    }
}
