package us.bndshop.geoquiz;

import android.support.annotation.StringRes;
import androidx.annotation.IdRes;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class EspressoInteractionsJava {
    public static void clickButton(@IdRes int textResId) {
        onView(withId(textResId)).perform(click());
    }
}
