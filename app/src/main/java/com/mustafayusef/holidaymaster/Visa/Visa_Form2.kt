package com.mustafayusef.holidaymaster.Visa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mustafayusef.holidaymaster.R

class Visa_Form2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_visa__form2)

    }
    fun backToTow(view: View){
        val intent= Intent(this@Visa_Form2,Visa_Form1::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
    }
}
