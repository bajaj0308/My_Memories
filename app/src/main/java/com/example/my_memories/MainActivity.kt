package com.example.my_memories

import android.content.Intent
import android.media.effect.Effect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        effect(wel)
        Handler().postDelayed({
            effect(relive)
        }, 3500)
        Handler().postDelayed({
            effect(forward)
            effect(start)
        }, 7000)
        start.setOnClickListener{
            val intent = Intent(this, Questions::class.java)
            startActivity(intent)
        }
    }
    private fun effect(v: View) {
        v.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        v.startAnimation(animation)
    }
}