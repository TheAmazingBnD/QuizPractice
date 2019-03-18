package us.bndshop.geoquiz

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OldEspressoTest: AbsUITest() {

    @Test
    fun click_true_assert_something() {
        onView(withId(R.id.trueButton)).perform(click())
        onView(withId(R.id.trueButton)).check(matches(isDisplayed()))
        onView(withId(R.id.nextButton)).perform(click())
    }

    @Test
    fun click_next_fiveTimes_make_assertion() {
        repeat(5) {
            onView(withId(R.id.nextButton)).perform(click())
        }
    }

}