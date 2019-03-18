package us.bndshop.geoquiz

import android.app.Application
import android.content.Context
import us.bndshop.geoquiz.api.ApiService
import us.bndshop.geoquiz.api.RestAPIClient

class App  : Application() {

    private val TAG = App::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    override fun getApplicationContext(): Context {
        return super.getApplicationContext()
    }


}