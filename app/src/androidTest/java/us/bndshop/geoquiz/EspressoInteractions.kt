package us.bndshop.geoquiz

import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId

fun clickButton(@StringRes textResId: Int) = onView(withId(textResId)).perform(click())