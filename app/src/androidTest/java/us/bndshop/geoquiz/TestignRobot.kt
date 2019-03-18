package us.bndshop.geoquiz

import android.support.annotation.StringRes

class TestignRobot {
    fun clickTrue() = clickButton(R.id.trueButton)
    fun clickFalse() = clickButton(R.id.falseButton)
    fun clickNext() = clickButton(R.id.nextButton)
    fun clickPrevious() = clickButton(R.id.prevButton)
    fun checkTextIsDisplayed(toMatch: String) = textIsDisplayed(toMatch)
    fun checkDisplayed(@StringRes toMatch: Int) = displayedInView(toMatch)
}

fun test(f: TestignRobot.() -> Unit) = TestignRobot().apply(f)
