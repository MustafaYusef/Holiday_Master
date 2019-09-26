package com.mustafayusef.holidaymaster.tickets.towWay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mustafayusef.holidaymaster.Models.DataTow
import com.mustafayusef.holidaymaster.Models.ResultTow
import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_return_tow_way.*

class ReturnTowWay : Fragment() {
    lateinit var holidays: DataTow
    lateinit var holidaysAll: ResultTow


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_return_tow_way, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        holidays= arguments?.getSerializable("TowWay") as DataTow
        holidaysAll= arguments?.getSerializable("TowWayAll") as ResultTow


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

            val intent = Intent(context, DetailsTow::class.java)
//
           intent.putExtra("TowWay",holidays)
            intent.putExtra("session", holidaysAll.sessionID)
            intent.putExtra("Id", holidays._id)
            intent.putExtra("type","RT")
            startActivity(intent)}

        purch?.setOnClickListener {
            var bundel= Bundle()
            bundel.putSerializable("ticket",holidaysAll)
            bundel.putString("Id",holidays._id)
            view?.findNavController()?.navigate(R.id.ticketDetailstowWay,bundel)
        }
        }

}
