package com.edwin.android.thebestbakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.edwin.android.thebestbakingapp.ui.activities.RecipeDetailActivity;
import com.edwin.android.thebestbakingapp.util.NetworkingUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.edwin.android.thebestbakingapp.ui.activities.RecipeDetailActivity.RECIPE_TYPE;

/**
 * Created by Edwin Ramirez Ventura on 5/28/2017.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeDetailActivityTest {

    @Rule
    public ActivityTestRule<RecipeDetailActivity> mActivityTestRule =
            new ActivityTestRule<RecipeDetailActivity>(RecipeDetailActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent intent = new Intent(targetContext, RecipeDetailActivity.class);
                    intent.putExtra(RECIPE_TYPE, NetworkingUtil.getRecipes(targetContext)[0]);
                    return intent;
                }
            };

    @Test
    public void checkItemExist() {
        onView(withId(R.id.recycler_view_recipe_step)).perform(RecyclerViewActions
                .scrollToPosition(1)).perform(click());
    }
}
