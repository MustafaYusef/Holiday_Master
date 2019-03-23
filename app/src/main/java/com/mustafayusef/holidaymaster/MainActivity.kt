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

        member.animate()
            .translationXBy(-700f)
            .duration = 2000
        userBrows.animate()
            .translationXBy(700f)
            .duration = 2000
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
        setanim()

    }
    fun setanim(){
//                member.animate()
//            .translationXBy(700f)
//            .duration = 2000
//        userBrows.animate()
//            .translationXBy(-700f)
//            .duration = 2000
    }
    fun goToLogin (view: View) {
        member.animate()
            .translationXBy(-700f)
            .duration = 1000
        Handler().postDelayed({

            Handler().postDelayed({

                val intent=Intent(this@MainActivity,LoginMember::class.java)
                startActivity(intent)
                finish()
            },100)
        },2500)
    }
    fun goToSearch (view:View){
        userBrows.animate()
            .translationXBy(700f)
            .duration = 1000
        Handler().postDelayed({

            Handler().postDelayed({

                val intent=Intent(this@MainActivity,searchActivity::class.java)
                startActivity(intent)
                finish()
            },100)
        },2500)

    }


}
