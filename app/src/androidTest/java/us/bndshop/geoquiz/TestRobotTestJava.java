package us.bndshop.geoquiz;

import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TestRobotTestJava extends AbsUITest {
    @Test
    public void clickTrue_AssertTrueVisible() {
        TestingRobotJava.clickTrue();
        TestingRobotJava.checkTextIsDisplayed("True");
        TestingRobotJava.clickNext();
    }

    @Test
    public void clickNextFiveTimes() {
        for (int i = 0; i < 5; i++) {
            TestingRobotJava.clickNext();
        }
    }
}
