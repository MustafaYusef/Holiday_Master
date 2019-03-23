package com.mustafayusef.holidaymaster

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.splash.*

class SplashScreen : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        imageSplash.startAnimation(AnimationUtils.loadAnimation(this@SplashScreen,R.anim.splash_in))

        Handler().postDelayed({
            imageSplash.startAnimation(AnimationUtils.loadAnimation(this@SplashScreen,R.anim.splash_out))
            Handler().postDelayed({
                imageSplash.visibility = View.GONE
                val intent=Intent(this@SplashScreen,Lottie::class.java)
                startActivity(intent)
                finish()
            },100)
        },2500)


    }
}
