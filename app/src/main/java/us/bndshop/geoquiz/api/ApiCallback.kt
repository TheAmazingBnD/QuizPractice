package us.bndshop.geoquiz.api

import okhttp3.ResponseBody

open class ApiCallback<T> : ApiResponse<T> {

    override fun onSuccess(t: T) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFail() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(errorBody: ResponseBody, code: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAnyStateFirst() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAnyStateLast() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCancel() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}