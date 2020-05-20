package com.example.my_memories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_questions.*

class Questions : AppCompatActivity() {
    companion object {
        var count = 0
        val questionsList: ArrayList<QuestionAnswer> = ArrayList();
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        questionsList.add(QuestionAnswer("How is your Day","ITS AWESOME"));
        questionsList.add(QuestionAnswer("How was your Day","IT WAS AWESOME"));
        effect(soon)
        Handler().postDelayed({
            effect(know_you_well)
        }, 3500)
        Handler().postDelayed({
            effect(answer_questions)
            effect(next)
        }, 7000)
        next.setOnClickListener{
            soon.visibility=View.INVISIBLE
            know_you_well.visibility=View.INVISIBLE
            answer_questions.visibility=View.INVISIBLE
            next.visibility=View.INVISIBLE
            questionAnswerList();
        }
        submit.setOnClickListener{
                submitAnswer()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.skip -> {
                val intent = Intent(this, Categories::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun submitAnswer(){
        var answer = findViewById<EditText>(R.id.answer).text.toString().toUpperCase().compareTo(questionsList[count].ans)
        if(answer==0){
            Toast.makeText(this, R.string.correct, Toast.LENGTH_LONG).show()
            if(count<1){
                count+=1
                changeQuestion()
            }
            else{
                val intent = Intent(this, Categories::class.java)
                startActivity(intent)
            }
        }
        else{
            Toast.makeText(this, R.string.incorrect, Toast.LENGTH_LONG).show()
        }
    }
    private fun effect(v: View){
        v.visibility = View.VISIBLE
        val animationSlideDown = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        v.startAnimation(animationSlideDown)
    }
    private fun effectSliding(v:View){
        v.visibility = View.VISIBLE
        val animationSlideDown = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
        v.startAnimation(animationSlideDown)
    }
    private fun questionAnswerList(){
        question.text= questionsList[count].question
        question.visibility=View.VISIBLE
        effectSliding(question)
        answer.visibility=View.VISIBLE
        submit.visibility=View.VISIBLE
    }
    private fun changeQuestion(){
        question.text = questionsList[count].question
        effectSliding(question);
        answer.text=null
    }
}