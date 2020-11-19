package com.javierfspano.deturno;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.javierfspano.deturno.ui.locationinput.LocationInputActivity;
import com.javierfspano.deturno.ui.main.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkTexts() {
        Matcher tabItemParentMatcher = withParent(withId(R.id.tabLayout));
        onView(tabItemParentMatcher).check(matches(hasChildCount(2)));
        onView(EspressoUtils.nthChildOf(tabItemParentMatcher,0)).check(matches(withText(R.string.list_tab_title)));
        onView(EspressoUtils.nthChildOf(tabItemParentMatcher,1)).check(matches(withText(R.string.map_tab_title)));
    }
}
