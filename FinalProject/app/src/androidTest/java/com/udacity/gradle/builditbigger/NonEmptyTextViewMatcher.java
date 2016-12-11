package com.udacity.gradle.builditbigger;

import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class NonEmptyTextViewMatcher extends TypeSafeMatcher<View> {
    @Override
    protected boolean matchesSafely(View item) {
        return item instanceof TextView && ((TextView) item).getText().length() > 0;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a non empty text view");
    }
}
