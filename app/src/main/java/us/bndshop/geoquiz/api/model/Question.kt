package us.bndshop.geoquiz.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(
    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,
    @SerializedName("correct_answer") val correctAnswer: Boolean,
    @SerializedName("incorrect_answer") val incorrectAnswer: List<Boolean>
) : Parcelable