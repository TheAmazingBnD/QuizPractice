package us.bndshop.geoquiz

import android.support.test.espresso.IdlingResource
import okhttp3.OkHttpClient

class OkHttpIdlingResource(private val client: OkHttpClient) : IdlingResource {

    @Volatile
    private var callback: IdlingResource.ResourceCallback? = null

    init {
        client.dispatcher().setIdleCallback {
            callback?.apply {
                onTransitionToIdle()
            }
        }
    }

    override fun getName(): String = NAME

    override fun isIdleNow(): Boolean {
        val idle = client.dispatcher().runningCallsCount() == 0
        callback?.apply {
            if (idle) {
                onTransitionToIdle()
            }
        }
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.callback = callback
    }

    companion object {
        const val NAME = "Client"
    }
}