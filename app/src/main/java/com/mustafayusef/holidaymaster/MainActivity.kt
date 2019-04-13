package com.mustafayusef.holidaymaster

import android.content.Intent

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.splash.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
        member.startAnimation(AnimationUtils.loadAnimation(this@MainActivity,R.anim.left_to_right))
        userBrows.startAnimation(AnimationUtils.loadAnimation(this@MainActivity,R.anim.right_to_left))
        //userBrows.startAnimation(AnimationUtils.loadAnimation(this@MainActivity,R.anim.shake))
//        member.animate()
//            .translationXBy(-700f)
//            .duration = 2000
//        userBrows.animate()
//            .translationXBy(700f)
//            .duration = 2000
       // member.animate().scaleX(0.2f).scaleY(0.2f).duration=2000
//        member.animate()
//            .translationXBy(500f)
//            .duration = 3000
//
//        userBrows.animate()
//            .translationXBy(-500f)
//            .duration = 3000


//        Handler().postDelayed({
//
//            Handler().postDelayed({
//
//
//
//            },100)
//        },2500)


    }

    fun goToLogin (view: View) {
        member.animate()
            .translationXBy(-750f)
            .duration = 1200
        Handler().postDelayed({

            Handler().postDelayed({

                val intent=Intent(this@MainActivity,LoginMember::class.java)
                startActivity(intent)
                finish()
            },10)
        },1000)
    }
    fun goToSearch (view:View){
        userBrows.animate()
            .translationXBy(750f)
            .duration = 1200
        Handler().postDelayed({

            Handler().postDelayed({

                val intent=Intent(this@MainActivity,dashboard::class.java)
                startActivity(intent)
                finish()
            },10)
        },1000)

    }


}
