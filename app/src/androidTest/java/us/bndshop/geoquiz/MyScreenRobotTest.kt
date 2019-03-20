package us.bndshop.geoquiz

import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyScreenRobotTest : AbsUITest() {
    @Test
    fun clickTrue_AssertTrueVisible() {
        myScreenRobotBuilder {
            clickTrue()
            checkTextIsDisplayed("True")
            clickNext()
        }
    }

    @Test
    fun clickNextFiveTimes() {
        myScreenRobotBuilder {
            repeat(5) {
                clickNext()
            }
        }
    }
}