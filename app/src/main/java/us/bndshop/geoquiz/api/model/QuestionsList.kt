package us.bndshop.geoquiz.api.model

import com.google.gson.annotations.SerializedName

data class QuestionsList(
    @SerializedName("request_code")val requestCode: Int,
    val results: Collection<Question>
    )