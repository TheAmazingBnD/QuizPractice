package us.bndshop.geoquiz.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import us.bndshop.geoquiz.App
import us.bndshop.geoquiz.api.model.QuestionsList

class ApiCall {

    private val app = App()

    private fun getApiService(): ApiService {
        return app.getInstance().getApiClient().getApiService()
    }

    fun getQuestions(): Call<QuestionsList> {
        return getApiService().getQuestions()
    }

    /**
     * @param tag         The log tag
     * @param callType    A string that describes the type of call for logging purposes
     * @param apiCallback An interface for handling all the API response states
     * @param <T>         The type of the API response body
     * @return A callback that can be enqueued on a Retrofit call
    </T> */
    fun <T> getCallback(
        apiCallback: ApiCallback<T>
    ): Callback<T> {
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                apiCallback.onAnyStateFirst()

                if (response.isSuccessful) {
//                    Logger.logV(tag, "$callType call successful")
                    apiCallback.onSuccess(response.body()!!)
                } else {
//                    Logger.logE(tag, callType + " call unsuccessful with code " + response.code())
                    apiCallback.onError(response.errorBody()!!, response.code())
                }

                apiCallback.onAnyStateLast()
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                apiCallback.onAnyStateFirst()

                if (call.isCanceled) {
//                    Logger.logV(tag, "$callType call canceled")
                    apiCallback.onCancel()
                } else {
//                    Logger.logE(tag, "$callType call failed", t)
                    apiCallback.onFail()
                }

                apiCallback.onAnyStateLast()
            }
        }
    }

}
