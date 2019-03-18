package us.bndshop.geoquiz

import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TetsingRobotTest : AbsUITest() {
    @Test
    fun clickTrue_AssertTrueVisible() {
        test {
            clickTrue()
            checkTextIsDisplayed("True")
            clickNext()
        }
    }

    @Test
    fun clickNextFiveTimes() {
        test {
            repeat(5) {
                clickNext()
            }
        }
    }
}