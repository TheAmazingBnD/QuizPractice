package us.bndshop.geoquiz;

import android.support.annotation.StringRes;

import static us.bndshop.geoquiz.EspressoInteractionsJava.clickButton;
import static us.bndshop.geoquiz.EspressoMatchersJava.displayedInView;
import static us.bndshop.geoquiz.EspressoMatchersJava.textIsDisplayed;

public class TestingRobotJava {
    public static void clickTrue() { clickButton(R.id.trueButton); }
    public static void clickFalse() { clickButton(R.id.falseButton); }
    public static void clickNext() { clickButton(R.id.nextButton); }
    public static void clickPrevious() { clickButton(R.id.prevButton); }
    public static void checkTextIsDisplayed(String toMatch) { textIsDisplayed(toMatch); }
    public static void checkDisplayed(@StringRes int toMatch) { displayedInView(toMatch); }
}
