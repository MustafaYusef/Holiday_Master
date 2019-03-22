package com.mustafayusef.holidaymaster

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import com.mustafayusef.holidaymaster.Models.modelOne


class DetailsOne : AppCompatActivity() {
    lateinit var holiday: modelOne
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_one)

        holiday= intent.getSerializableExtra("holidaysTow") as modelOne



    }
    fun backToOne(view: View){
        val intent= Intent(this@DetailsOne,showHoliday::class.java)
        startActivity(intent)

    }
}
