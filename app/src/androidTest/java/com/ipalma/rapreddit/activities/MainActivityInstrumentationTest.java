package com.ipalma.rapreddit.activities;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import com.ipalma.rapreddit.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentationTest {

    @Rule
    public IntentsTestRule<MainActivity> activityTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    public void validateEmptyTextViewMessage() {
        onView(withId(R.id.empty_view_text)).check(matches(withText("No items to show")));
    }

    @Test
    public void validateRecyclerViewItemClickStartsDetailsActivity() {
        onView(withId(R.id.recyclerView))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        // perform click on the first item of the list
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // check that the new activity was created after we clicked an item
        intended(hasComponent(RedditDetailsActivity.class.getName()));
    }

}