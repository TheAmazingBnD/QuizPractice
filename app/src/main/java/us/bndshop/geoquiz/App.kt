package us.bndshop.geoquiz

import android.app.Application
import us.bndshop.geoquiz.api.ApiService
import us.bndshop.geoquiz.api.RestAPIClient

class App:  Application() {

    private val TAG = App::class.java.simpleName
    private val apiClient = RestAPIClient(getURL())
    private lateinit var apiService: ApiService
    private var instance = this


    override fun onCreate() {
        super.onCreate()
        apiService = apiClient.getApiService()
    }

//    override fun onTerminate() {
//        super.onTerminate()
//    }
//
//    override fun getApplicationContext(): Context {
//        return super.getApplicationContext()
//    }

    fun getInstance(): App {
        return instance
    }

    fun getURL(): String {
        return "https://opentdb.com"
    }

    fun getApiClient(): RestAPIClient {
        return apiClient
    }

}