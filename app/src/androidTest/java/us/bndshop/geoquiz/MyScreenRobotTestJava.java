package us.bndshop.geoquiz;

import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MyScreenRobotTestJava extends AbsUITest {
    MyScreenRobotJava myScreenRobotJava = new MyScreenRobotJava();
    @Test
    public void clickTrue_AssertTrueVisible() {
        MyScreenRobotJava myScreenRobotJava = new MyScreenRobotJava();
        myScreenRobotJava.clickTrue().checkTextIsDisplayed("True").clickNext();
    }

    @Test
    public void clickNextFiveTimes() {
        for (int i = 0; i < 5; i++) {
            myScreenRobotJava.clickNext();
        }
    }
}
