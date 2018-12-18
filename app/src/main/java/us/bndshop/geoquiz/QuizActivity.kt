package us.bndshop.geoquiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import okhttp3.ResponseBody
import us.bndshop.geoquiz.api.ApiCall
import us.bndshop.geoquiz.api.ApiCallback
import us.bndshop.geoquiz.api.model.Question
import us.bndshop.geoquiz.api.model.QuestionsList

class QuizActivity : AppCompatActivity() {

    private val apiCall = ApiCall()
    private var questions = mutableListOf<Question>()
    private var answer = false
    private var correctAnswer = true
    private var question: Question? = null
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        layoutInflater.inflate(R.layout.activity_quiz, null)

        val positiveButton = findViewById<Button>(R.id.positiveButton)
        val negativeButton = findViewById<Button>(R.id.negativeButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val prevButton = findViewById<Button>(R.id.prevButton)

        getQuestions()

        positiveButton.setOnClickListener {
            answer = true
            //TODO add toast for incorrect or correct
        }
        negativeButton.setOnClickListener {
            answer = false
            //TODO add toast for incorrect or correct
        }
        nextButton.setOnClickListener {
            //            question = getQuestion(index++)
        }
        prevButton.setOnClickListener {
            //TODO Previous Logic
        }
    }

    private fun getNewQuestion(index: Int) {
        question = questions[index]
    }

    private fun setupQuestion() {
        val questionText = findViewById<TextView>(R.id.quizQuestion)
        val questionDifficulty = findViewById<TextView>(R.id.quizDifficultyValue)
        val questionCategory = findViewById<TextView>(R.id.quizCategoryValue)

        getNewQuestion(index)

        correctAnswer = question!!.correctAnswer
        questionText.text = question!!.question
        questionDifficulty.text = question!!.difficulty
        questionCategory.text = question!!.category
    }

    private fun getQuestions() {
        val fetchQuestionsList = apiCall.getQuestions()

        fetchQuestionsList.enqueue(
            apiCall.getCallback(
                "Quiz",
                "Get Questions",
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
