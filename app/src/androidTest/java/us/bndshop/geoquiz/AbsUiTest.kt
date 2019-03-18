package us.bndshop.geoquiz

import android.support.test.espresso.IdlingRegistry
import android.support.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Rule
import org.junit.runner.RunWith
import us.bndshop.geoquiz.QuizActivity.Companion.getApiClient

private const val COMPUTATION_IDLER_NAME = "Computation"
private const val IO_IDLER_NAME = "IO"

@RunWith(AndroidJUnit4::class)
abstract class AbsUITest {

    @get:Rule
    open val activityRule = ActivityTestRule(QuizActivity::class.java)

    open fun setup() {
        activityRule.activity.startActivity(null)
        val okHttpResource = OkHttpIdlingResource(getApiClient().getOkHttpClient())
        IdlingRegistry.getInstance().register(okHttpResource)

        RxJavaPlugins.setInitComputationSchedulerHandler(Rx2Idler.create(COMPUTATION_IDLER_NAME))
        RxJavaPlugins.setInitIoSchedulerHandler(Rx2Idler.create(IO_IDLER_NAME))
    }
}