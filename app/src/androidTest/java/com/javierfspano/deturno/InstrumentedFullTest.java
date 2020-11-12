package com.javierfspano.deturno;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.javierfspano.deturno.ui.locationinput.LocationInputActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class InstrumentedFullTest {

    @Rule
    public ActivityTestRule<LocationInputActivity> activityActivityTestRule = new ActivityTestRule<>(LocationInputActivity.class);

    @Test
    public void checkTexts() {
        onView(withId(R.id.welcome_title_text_view)).check(matches(withText(R.string.welcome_title)));
        onView(withId(R.id.welcome_message_text_view)).check(matches(withText(R.string.welcome_message)));
        onView(withId(R.id.welcome_location_text_input_label)).check(matches(withText(R.string.welcome_text_input_label)));
        onView(withId(R.id.welcome_location_button_label)).check(matches(withText(R.string.welcome_button_label)));
        onView(withId(R.id.use_my_location_button)).check(matches(withText(R.string.welcome_button_text)));
    }
}
