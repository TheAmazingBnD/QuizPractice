package us.bndshop.geoquiz.api.model

import com.google.gson.annotations.SerializedName


data class Question(
    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,
    @SerializedName("correct_answer") val correctAnswer: Boolean,
    @SerializedName("incorrect_answer") val incorrectAnswer: List<Boolean>
)