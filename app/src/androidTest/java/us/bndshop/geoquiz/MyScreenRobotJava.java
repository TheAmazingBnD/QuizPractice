package us.bndshop.geoquiz;

import android.support.annotation.StringRes;

import static us.bndshop.geoquiz.EspressoInteractionsJava.clickButton;
import static us.bndshop.geoquiz.EspressoMatchersJava.displayedInView;
import static us.bndshop.geoquiz.EspressoMatchersJava.textIsDisplayed;

public class MyScreenRobotJava {
    public MyScreenRobotJava clickTrue() { clickButton(R.id.trueButton); return this; }
    public MyScreenRobotJava clickFalse() { clickButton(R.id.falseButton); return this; }
    public MyScreenRobotJava clickNext() { clickButton(R.id.nextButton); return this; }
    public MyScreenRobotJava clickPrevious() { clickButton(R.id.prevButton); return this; }
    public MyScreenRobotJava checkTextIsDisplayed(String toMatch) { textIsDisplayed(toMatch); return this; }
    public MyScreenRobotJava checkDisplayed(@StringRes int toMatch) { displayedInView(toMatch); return this; }
}
