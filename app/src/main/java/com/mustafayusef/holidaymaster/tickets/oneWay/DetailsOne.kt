package com.mustafayusef.holidaymaster.tickets.oneWay

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.Adapters.OneDetailsAdapter
import com.mustafayusef.holidaymaster.Models.Data1

import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.tickets.Buggage.buggage
import com.mustafayusef.holidaymaster.tickets.FareRules.Fare
import com.mustafayusef.holidaymaster.tickets.breakUp.breakUp

import kotlinx.android.synthetic.main.activity_details_one.*


class DetailsOne : AppCompatActivity() {
    lateinit var holiday: Data1
    var session=""
    var adult=0
    var Id=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_one)
        var i:Int=0
        holiday= intent.getSerializableExtra("oneway") as Data1
        session=intent.getStringExtra("sessionId")
        adult= intent.getIntExtra("adult",0)
        Id= intent.getStringExtra("Id")
//        holder.view?.stops.text=holidays?.stops.toString()+" Stops"
//        holder.view?.priceOne .text=holidays?.price.toString()+"$"
//        holder.view?.depTime.text= holidays?.depDateAndTime!![0]
//        holder.view?.arrTime.text=holidays?.arrDateAndTime[holidays?.arrDateAndTime.lastIndex]        //  holder.view?.AirNameDep.text=holidays?.departingAirportName[0].subSequence(0,11)
//        holder.view?.duration.text=holidays?.totalDuration
//        holder.view?.AirNameDep .text=holidays?.depCityName [0]
//        holder.view?.airNameArr .text=holidays?.arrCityName [holidays?.arrDateAndTime.lastIndex]
//        holder.view?.companyNameOne.text=holidays.AirlineName
//        holder.view?.duration .text=holidays.layoverTime[0]
//
//        Glide.with(context).load(holidays?.mainLogo).apply(RequestOptions.centerCropTransform().centerInside()).into(holder.view.LogoAir)


        this.detailsOneList.layoutManager= LinearLayoutManager(this)
        detailsOneList.adapter= OneDetailsAdapter(this@DetailsOne,holiday.depDateAndTime,
            holiday.arrDateAndTime,holiday.layoverTime,holiday.depCityName
            ,holiday.arrCityName,holiday.Duration,holiday.logos,holiday.airNames)

        BuggageBtn.setOnClickListener {
            val intent = Intent(this@DetailsOne, buggage::class.java)
//
            intent.putExtra("session", session)
            intent.putExtra("Id", holiday._id)
            intent.putExtra("type","OW")
        startActivity(intent)}

        FareBtn.setOnClickListener {
            val intent = Intent(this@DetailsOne, Fare::class.java)
//
            intent.putExtra("session", session)
            intent.putExtra("Id", holiday._id)
            intent.putExtra("type","OW")
            startActivity(intent)}

        breakBtn.setOnClickListener {
            val intent = Intent(this@DetailsOne, breakUp::class.java)
//
            intent.putExtra("adult", adult.toString())
            intent.putExtra("AdultBaseFare", holiday.AdultBaseFare.toString())
            intent.putExtra("AdultTaxFare", holiday.AdultTax.toString())
            intent.putExtra("price", holiday.price .toString())
            startActivity(intent)}

            purOne.text="Purchase for "+holiday.price+" $"
         //   typ="OW"




        }

}
