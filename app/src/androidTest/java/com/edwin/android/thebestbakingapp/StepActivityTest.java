package com.edwin.android.thebestbakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.edwin.android.thebestbakingapp.ui.activities.RecipeDetailActivity;
import com.edwin.android.thebestbakingapp.ui.activities.StepActivity;
import com.edwin.android.thebestbakingapp.util.NetworkingUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.edwin.android.thebestbakingapp.ui.activities.RecipeDetailActivity.RECIPE_TYPE;
import static com.edwin.android.thebestbakingapp.ui.activities.RecipeDetailActivity.STEP_SELECTED;

/**
 * Created by Edwin Ramirez Ventura on 5/28/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StepActivityTest {

    @Rule
    public ActivityTestRule<StepActivity> mActivityTestRule =
            new ActivityTestRule<StepActivity>(StepActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent intent = new Intent(targetContext, RecipeDetailActivity.class);
                    intent.putExtra(RECIPE_TYPE, NetworkingUtil.getRecipes(targetContext)[0]);
                    intent.putExtra(STEP_SELECTED, 1);
                    return intent;
                }
            };

    @Test
    public void clickNavigationButton() {
        onView(withId(R.id.button_next_step)).perform(click());
        onView(withId(R.id.button_previous_step)).perform(click());
    }
}
