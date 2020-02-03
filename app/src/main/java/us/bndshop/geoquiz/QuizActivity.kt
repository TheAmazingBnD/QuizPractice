package us.bndshop.geoquiz

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import okhttp3.ResponseBody
import us.bndshop.geoquiz.api.model.Question
import us.bndshop.geoquiz.api.model.QuestionsList
import android.text.Html
import android.util.Log
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import us.bndshop.geoquiz.api.ApiService
import us.bndshop.geoquiz.api.RestAPIClient
import java.lang.Exception


class QuizActivity : AppCompatActivity() {

    private var questions = mutableListOf<Question>()
    private var answer = false
    private var correctAnswer = true
    private var question: Question? = null
    private var index = 0
    private var totalCorrect = 0
    private var totalIncorrect = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        layoutInflater.inflate(R.layout.activity_quiz, null)
        apiService = apiClient.getApiService()

        getQuestions()

        swipeRefresh.setOnRefreshListener { showRefreshDialog() }
        trueButton.setOnClickListener {
            answer = true
        }
        falseButton.setOnClickListener {
            answer = false
        }
        nextButton.setOnClickListener {
            compareQuestion()
            if (index < questions.size - 1) {
                getQuestion(index++)
                setupQuestion()
            } else {
                Toast.makeText(
                    applicationContext,
                    "No more questions in the list.",
                    Toast.LENGTH_LONG
                ).show()
                gradedQuizDialog()
                resetValues()
                getQuestions()
            }
        }
        prevButton.setOnClickListener {
            if (index > 0) {
                getQuestion(index--)
                setupQuestion()
            } else {
                Toast.makeText(applicationContext, "First question in the list.", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putInt("Correct", totalCorrect)
        outState?.putInt("Incorrect", totalIncorrect)
        Log.d(TAG, "onSavedInstanceState() called")
    }

    private fun showRefreshDialog() {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.setTitle("Are you sure?")
            .setMessage("Are you sure you would like to reload for new questions?")
            .setPositiveButton("Confirm") { _, _ ->
                swipeRefresh.isRefreshing = true
                resetValues()
                getQuestions()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
                swipeRefresh.isRefreshing = false
            }
            .create()
            .show()
    }

    private fun resetValues() {
        questions = mutableListOf()
        answer = false
        correctAnswer = true
        question = null
        index = 0
        totalCorrect = 0
        totalIncorrect = 0
    }

    private fun compareQuestion() {
        if (answer == correctAnswer) {
            totalCorrect += 1
            Toast.makeText(
                applicationContext,
                "Correct! \nTotal Correct: $totalCorrect",
                Toast.LENGTH_SHORT
            ).show()

        } else {
            totalIncorrect += 1
            Toast.makeText(
                applicationContext,
                "Incorrect! \nTotal Incorrect: $totalIncorrect",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    private fun gradedQuizDialog() {
        val grade = (totalCorrect * 100) / NUMBER_OF_QUESTIONS

        val alertDialog = AlertDialog.Builder(this)

        alertDialog.setTitle("Graded Quiz")
            .setMessage("Grade: $grade % \nCorrect: $totalCorrect \nIncorrect: $totalIncorrect")
            .create()
            .show()
    }

    private fun getQuestion(index: Int) {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
            question = questions[index]
        } else {
            question = questions[index]
        }
    }

    private fun setupQuestion() {
        val regex = "[\\p{P}\\p{S}]\n"

        getQuestion(index)

        correctAnswer = question!!.correctAnswer
        quizQuestionNumber.text = (index + 1).toString()
        quizQuestion.text = Html.fromHtml(question!!.question, 0)
        quizDifficultyValue.text = question!!.difficulty
        quizCategoryValue.text = question!!.category
    }

    private fun getQuestions() {
        val fetchQuestionsList = apiService.getQuestions()

        fetchQuestionsList.enqueue(
            object : Callback<QuestionsList> {
                override fun onResponse(
                    call: Call<QuestionsList>,
                    response: Response<QuestionsList>
                ) {
                    if (response.isSuccessful) {
                        onFetchQuestionsSuccess(response.body())
                    }
                }

                override fun onFailure(call: Call<QuestionsList>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error, item not found", Toast.LENGTH_LONG)
                        .show()
                }
            }
        )

//        GlobalScope.launch {
//            try {
//                val questionsList = apiService.getQuestions()
//                if (questionsList.isSuccessful) {
//                    onFetchQuestionsSuccess(questionList = questionsList.body())
//                } else {
//                    Toast.makeText(
//                        applicationContext,
//                        "Error, questions not found",
//                        Toast.LENGTH_LONG
//                    )
//                        .show()
//                }
//            } catch (e: HttpException) {
//                Toast.makeText(applicationContext, e.message(), Toast.LENGTH_LONG)
//                    .show()
//            } catch (e: Throwable) {
//                Toast.makeText(applicationContext, "Error, questions not found", Toast.LENGTH_LONG)
//                    .show()
//            }
//        }
    }

    private fun onFetchQuestionsSuccess(questionList: QuestionsList?) {
        questionList?.results?.forEach {
            questions.add(it)
        }
        setupQuestion()
    }


    companion object {
        private val TAG = QuizActivity::class.java.simpleName
        private const val NUMBER_OF_QUESTIONS = 50
        private val apiClient = RestAPIClient(getURL())
        private lateinit var apiService: ApiService
        private var instance = App()

        fun getInstance(): App {
            return instance
        }

        fun getURL(): String {
            return "https://opentdb.com"
        }

        fun getApiClient(): RestAPIClient {
            return apiClient
        }

        fun getApiService(): ApiService {
            return apiService
        }
    }

}
