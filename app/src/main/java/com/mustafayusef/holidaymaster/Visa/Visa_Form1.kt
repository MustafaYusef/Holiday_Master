package com.mustafayusef.holidaymaster.Visa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mustafayusef.holidaymaster.R

class Visa_Form1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visa__form1)

    }
    fun showFormTow(view:View){
        val intent= Intent(this@Visa_Form1,Visa_Form2::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)

    }
}
