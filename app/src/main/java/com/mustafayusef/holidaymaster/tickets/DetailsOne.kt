package com.mustafayusef.holidaymaster.tickets

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.Adapters.OneDetailsAdapter

import com.mustafayusef.holidaymaster.Models.modelOne
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_details_one.*


class DetailsOne : AppCompatActivity() {
    lateinit var holiday: modelOne
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_one)
        var i:Int=0
        holiday= intent.getSerializableExtra("holidaysTow") as modelOne




        this.detailsOneList.layoutManager= LinearLayoutManager(this)
        detailsOneList.adapter= OneDetailsAdapter(this@DetailsOne,holiday.arrCityName,
            holiday.depCityName,holiday.depDateAndTime,holiday.arrDateAndTime
            ,holiday.airlineLogo,holiday.totalDuration,holiday.layOverCity,holiday.airlineName,holiday.flightModel)


        purOne.text="Purchase for "+holiday.price+" $"
        }
        fun backToOne(view: View){
            super.onBackPressed()

    }
}