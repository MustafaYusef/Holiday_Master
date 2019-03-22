package com.mustafayusef.holidaymaster

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chibatching.kotpref.bulk
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
       // Kotpref.init(this)
        var name=intent.getStringExtra("name")
       var email= intent.getStringExtra("email")
        var phone=intent.getStringExtra("phone")
       var money= intent.getStringExtra("money")

        username.text=name
        Email.text=email
        Phone.text=phone
        Money.text=money
     //  println(name+"     "+email)


    }
    fun backsearch(view:View){
        val intent= Intent(this@Profile,searchActivity::class.java)
        startActivity(intent)
    }
    fun Logout(view: View){
        LoginMember.cacheObj.bulk {
            token=""
        }
        val intent= Intent(this@Profile,LoginMember::class.java)
        startActivity(intent)
    }
}
