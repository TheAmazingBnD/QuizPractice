package us.bndshop.geoquiz.api

import okhttp3.ResponseBody


interface ApiResponse<T> {
    abstract fun onAnyStateFirst()

    abstract fun onSuccess(t: T)

    abstract fun onError(errorBody: ResponseBody, code: Int)

    abstract fun onCancel()

    abstract fun onFail()

    abstract fun onAnyStateLast()
}