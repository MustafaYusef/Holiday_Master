package com.mustafayusef.holidaymaster.tickets.towWay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mustafayusef.holidaymaster.Models.DataTow
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_return_tow_way.*

class ReturnTowWay : AppCompatActivity() {
    lateinit var holidays: DataTow
    var session=""
    var adult=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_return_tow_way)

        holidays= intent.getSerializableExtra("TowWay") as DataTow
        session= intent.getStringExtra("sessionId")
        adult=intent.getIntExtra("adult",0)

        priceOne .text=holidays?.price.toString()+"$"
       depTime.text= holidays?.return_depDateAndTime!![0]
       arrTime.text=holidays?.return_arrDateAndTime [holidays?.return_arrDateAndTime.lastIndex]        //  holder.view?.AirNameDep.text=holidays?.departingAirportName[0].subSequence(0,11)
       duration.text=holidays?.return_totalDuration
      AirNameDep .text=holidays?.return_depCityName [0]
       airNameArr .text=holidays?.return_arrCityName [holidays?.return_arrDateAndTime.lastIndex]
        companyNameOne.text=holidays.AirlineName
      duration .text=holidays.return_arrCityName[0]
       stops.text="Stops:"+holidays.return_stops .toString()
        Glide.with(this).load(holidays?.return_mainLogo).apply(RequestOptions.centerCropTransform().centerInside()).into(LogoAir)


        Details.setOnClickListener {

            val intent = Intent(this@ReturnTowWay, DetailsTow::class.java)
//
           intent.putExtra("TowWay",holidays)
            intent.putExtra("session", session)
            intent.putExtra("Id", holidays._id)
            intent.putExtra("type","RT")
            startActivity(intent)}
        }

}
