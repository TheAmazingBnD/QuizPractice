package us.bndshop.geoquiz

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import okhttp3.ResponseBody
import us.bndshop.geoquiz.api.ApiCall
import us.bndshop.geoquiz.api.ApiCallback
import us.bndshop.geoquiz.api.model.Question
import us.bndshop.geoquiz.api.model.QuestionsList
import android.support.v4.widget.SwipeRefreshLayout
import android.text.Html
import kotlinx.android.synthetic.main.activity_quiz.*


class QuizActivity : AppCompatActivity() {

    private val apiCall = ApiCall()
    private var questions = mutableListOf<Question>()
    private var answer = false
    private var correctAnswer = true
    private var question: Question? = null
    private var index = 0
    private var totalCorrect = 0
    private var totalIncorrect = 0

    companion object {
        private const val NUMBER_OF_QUESTIONS = 50
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        layoutInflater.inflate(R.layout.activity_quiz, null)

        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        val positiveButton = findViewById<Button>(R.id.positiveButton)
        val negativeButton = findViewById<Button>(R.id.negativeButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val prevButton = findViewById<Button>(R.id.prevButton)

        getQuestions()

        swipeRefreshLayout.setOnRefreshListener { showRefreshDialog() }
        positiveButton.setOnClickListener {
            answer = true
        }
        negativeButton.setOnClickListener {
            answer = false
        }
        nextButton.setOnClickListener {
            compareQuestion()
            if (index < questions.size - 1) {
                getQuestion(index++)
                setupQuestion()
            } else {
                Toast.makeText(applicationContext, "No more questions in the list.", Toast.LENGTH_LONG).show()
                gradedQuizDialog()
            }
        }
        prevButton.setOnClickListener {
            if (index > 0) {
                getQuestion(index--)
                setupQuestion()
            } else {
                Toast.makeText(applicationContext, "First question in the list.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showRefreshDialog() {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.setTitle("Are you sure?")
            .setMessage("Are you sure you would like to reload for new questions?")
            .setPositiveButton("Confirm") { _, _ ->
                getQuestions()
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
        questions = mutableListOf<Question>()
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
            Toast.makeText(applicationContext, "Correct! \nTotal Correct: $totalCorrect", Toast.LENGTH_SHORT).show()

        } else {
            totalIncorrect += 1
            Toast.makeText(applicationContext, "Incorrect! \nTotal Inorrect: $totalIncorrect", Toast.LENGTH_SHORT)
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
        val questionText = findViewById<TextView>(R.id.quizQuestion)
        val questionDifficulty = findViewById<TextView>(R.id.quizDifficultyValue)
        val questionCategory = findViewById<TextView>(R.id.quizCategoryValue)
        val regex = "[\\p{P}\\p{S}]\n"

        getQuestion(index)

        correctAnswer = question!!.correctAnswer
        questionText.text = Html.fromHtml(question!!.question, 0)
        questionDifficulty.text = question!!.difficulty
        questionCategory.text = question!!.category
    }

    private fun getQuestions() {
        val fetchQuestionsList = apiCall.getQuestions()

        fetchQuestionsList.enqueue(
            apiCall.getCallback(
                object : ApiCallback<QuestionsList>() {
                    override fun onSuccess(t: QuestionsList) {
                        super.onSuccess(t)
                        onFetchQuestionsSuccess(t)
                    }

                    override fun onError(errorBody: ResponseBody, code: Int) {
                        Toast.makeText(applicationContext, "Error, item not found", Toast.LENGTH_LONG).show()
                    }
                })
        )
    }

    private fun onFetchQuestionsSuccess(questionList: QuestionsList) {
        questionList.results.forEach {
            questions.add(it)
        }
        setupQuestion()
    }

}
