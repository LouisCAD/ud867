package com.udacity.gradle.builditbigger;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class NonEmptyJokeTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Tests that the AsyncTask returns a non empty string.
     * Testing if the joke is funny is not feasible at the moment though.
     */
    @Test
    public void nonEmptyJokeTest() {
        ViewInteraction appCompatButton = onView(allOf(withId(R.id.tellJokeButton), isDisplayed()));
        appCompatButton.perform(click());
        ViewInteraction textView = onView(allOf(withId(R.id.contentText), isDisplayed()));
        textView.check(matches(new NonEmptyTextViewMatcher()));
    }
}
