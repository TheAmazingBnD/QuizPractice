package us.bndshop.geoquiz;

import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class OldEspressoTestJava extends AbsUITest {
    @Test
    public void click_true_assert_something() {
        onView(withId(R.id.trueButton)).perform(click());

        onView(withId(R.id.trueButton)).check(matches(isDisplayed()));

        onView(withId(R.id.nextButton)).perform(click());
    }

    @Test
    public void click_next_fiveTimes_make_assertion() {
        for(int i = 0; i < 5; i++) {
            onView(withId(R.id.nextButton)).perform(click());
        }
    }

}
