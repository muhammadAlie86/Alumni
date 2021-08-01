package com.alie.alumni.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.alie.alumni.MainActivity
import com.alie.alumni.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_SRCEEN  : Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable { run {
            val intent = Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        },SPLASH_SRCEEN)
    }
}

