package com.mustafayusef.holidaymaster.Visa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mustafayusef.holidaymaster.R

class searchVisa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_visa)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
    }
}
