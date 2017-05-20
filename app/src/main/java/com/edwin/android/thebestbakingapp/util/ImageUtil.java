package com.edwin.android.thebestbakingapp.util;

import android.content.ClipData;

import com.edwin.android.thebestbakingapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Edwin Ramirez Ventur on 5/19/2017.
 */

public final class ImageUtil {
    private final static Random randomGenerator = new Random();

    public static int getImagePosterResource() {
        List<Integer> bakerPoster = new ArrayList<>();
        bakerPoster.add(R.drawable.cake);
        bakerPoster.add(R.drawable.appetizing);
        bakerPoster.add(R.drawable.food_with_apples);

        int index = randomGenerator.nextInt(bakerPoster.size());
        return bakerPoster.get(index);

    }
}
