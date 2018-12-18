package us.bndshop.geoquiz.api

import retrofit2.Call
import retrofit2.http.GET
import us.bndshop.geoquiz.api.model.QuestionsList

interface ApiService {

    // GET
    @GET("/api.php?amount=50&type=boolean")
    fun getQuestions(): Call<QuestionsList>

}
