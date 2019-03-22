package com.mustafayusef.holidaymaster

import android.content.Intent

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if( LoginMember.cacheObj.token!="")println("Token"+LoginMember.cacheObj.token)



//        member.setOnClickListener {
//            val intent=Intent(this@MainActivity,LoginMember::class.java)
//
//
//            startActivity(intent)
//        }
//        userBrows.setOnClickListener {
//            val intent=Intent(this@MainActivity,searchActivity::class.java)
//
//
//            startActivity(intent)
//        }
    }
    fun goToLogin (view: View) {
        member.animate()
            .translationXBy(-1000f)
            .duration = 2000
        val intent=Intent(this@MainActivity,LoginMember::class.java)


       startActivity(intent)
    }
    fun goToSearch (view:View){
        userBrows.animate()
            .translationXBy(-1000f)
            .duration = 2000
        val intent=Intent(this@MainActivity,searchActivity::class.java)
        startActivity(intent)

    }


}
