package com.mustafayusef.holidaymaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class Lottie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)
        Handler().postDelayed({



                val intent= Intent(this, mainNavigation::class.java)
                startActivity(intent)
                finish()

        },3000)
    }
}
