package com.example.collegenews

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

@SuppressLint("CustomSplashScreen")
class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

//        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this,signUp::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}