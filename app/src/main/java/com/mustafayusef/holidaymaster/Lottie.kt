package com.mustafayusef.holidaymaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class Lottie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)


        Handler().postDelayed({

            Handler().postDelayed({

                val intent = Intent(this@Lottie,MainActivity::class.java)
                startActivity(intent)


                finish()
            },100)
        },2500)



    }
}
