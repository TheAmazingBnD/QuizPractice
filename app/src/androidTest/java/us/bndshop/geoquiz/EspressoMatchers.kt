package us.bndshop.geoquiz

import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import androidx.annotation.IdRes

fun displayedInView(@IdRes textResId: Int) = onView(withId(textResId)).check(matches(isDisplayed()))
fun textIsDisplayed(text: String) = onView(withText(text)).check(matches(isDisplayed()))
