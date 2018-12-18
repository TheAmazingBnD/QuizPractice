package us.bndshop.geoquiz.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestAPIClient(var apiURL: String) {
    private val TAG = RestAPIClient::class.java.simpleName

//    private var pinningMode: PinningMode
    private var apiService: ApiService
    private var retrofit: Retrofit

    init {
//        Logger.log(TAG, "Initializing REST API with $apiUrl")

//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
//            .addInterceptor(HeaderInterceptor())
//            .addInterceptor(LoggingInterceptor())
//            .addInterceptor(httpLoggingInterceptor)
            .build()

//        pinningMode = if (BuildConfig.DEBUG) PinningMode.DISABLED else PinningMode.ENABLED_EVERYWHERE


        retrofit = Retrofit.Builder()
            .baseUrl(apiURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

//    fun getPinningMode(): PinningMode {
//        return pinningMode
//    }

    fun getApiService(): ApiService {
        return apiService
    }

    fun getRetrofitInstance(): Retrofit {
        return retrofit
    }

    enum class PinningMode {
        DISABLED,
        ENABLED_EVERYWHERE
    }
}