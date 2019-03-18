package us.bndshop.geoquiz;

import android.support.annotation.StringRes;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class EspressoMatchersJava {
    public static void displayedInView(@StringRes int textResId) {
        onView(withId(textResId)).check(matches(isDisplayed()));
    }
    public static void textIsDisplayed(String text) {
        onView(withText(text)).check(matches(isDisplayed()));
    }
}
