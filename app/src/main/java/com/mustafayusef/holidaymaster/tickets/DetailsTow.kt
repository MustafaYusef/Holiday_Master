package com.mustafayusef.holidaymaster.tickets

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustafayusef.holidaymaster.Adapters.MainAdapter
import com.mustafayusef.holidaymaster.Adapters.OneDetailsAdapter
import com.mustafayusef.holidaymaster.Models.modelTow

import kotlinx.android.synthetic.main.activity_details_tow.*
import com.mustafayusef.holidaymaster.Adapters.ReturnDetails
import com.mustafayusef.holidaymaster.Adapters.mergeAdapter
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.R.layout.*
import kotlinx.android.synthetic.main.header_ticket.view.*
import kotlinx.android.synthetic.main.nested.*


class DetailsTow : AppCompatActivity() {
    lateinit var holiday: modelTow
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(nested)

        var i:Int=0
        this.holiday = intent.getSerializableExtra("holidaysTow") as modelTow


       locationDatesList .layoutManager = LinearLayoutManager(this)
       val headers= mutableListOf<String>("Departure","Return")
        // Access RecyclerView Adapter and load the data
        val adapter = MainAdapter(this@DetailsTow,holiday,headers)
        locationDatesList.adapter = adapter

        val date = this.findViewById<Button>(R.id.purTow)
        date.text="Purchase for "+holiday.price+" $"

//        detailsTowList.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager?
////        detailsTowList.addItemDecoration(detailsTowList.)
//        detailsTowList.adapter= mergeAdapter(this@DetailsTow,0, holiday.arrCityName!!,
//            holiday.depCityName!!, holiday.depDateAndTime!!, holiday.arrDateAndTime!!
//            , holiday.airlineLogo!!, holiday.totalDuration!!, holiday.layOverCity!!, holiday.airlineName!!
//            , holiday.flightModel!!)
//
//        detailsTowListRet.layoutManager= LinearLayoutManager(this)
//        detailsTowListRet.adapter= mergeAdapter(this@DetailsTow, holiday.arrCityName!!.size,
//            holiday.ReturnArrCityName!!,
//            holiday.ReturnDepCityName!!, holiday.ReturnAepDateAndTime!!, holiday.ReturnArrDateAndTime!!
//            , holiday.ReturnairlineLogo!!, holiday.ReturnTotalDuration!!, holiday.ReturnLayOverCity!!
//            , holiday.ReturnAirlineName!!, holiday.ReturnFlightModel!!
//        )


//                detailsTowList.layoutManager= LinearLayoutManager(this)
////        detailsTowList.addItemDecoration(detailsTowList.)
//        val arrCityName = ArrayList<String>()
//
//        arrCityName.addAll(holiday.arrCityName!!)
//        arrCityName.addAll(holiday.ReturnArrCityName!!)
//
//        val depCityName = ArrayList<String>()
//
//        depCityName.addAll(holiday.depCityName!!)
//        depCityName.addAll(holiday.ReturnDepCityName!!)
//
//        val depDateAndTime = ArrayList<String>()
//
//        depDateAndTime.addAll(holiday.depDateAndTime!!)
//        depDateAndTime.addAll(holiday.ReturnAepDateAndTime!!)
//
//
//        val arrDateAndTime = ArrayList<String>()
//
//        arrDateAndTime.addAll(holiday.arrDateAndTime!!)
//        arrDateAndTime.addAll(holiday.ReturnArrDateAndTime!!)
//
//        val airlineLogo = ArrayList<String>()
//
//        airlineLogo.addAll(holiday.airlineLogo!!)
//        airlineLogo.addAll(holiday.ReturnairlineLogo!!)
//
//        val totalDuration = ArrayList<String>()
//
//        totalDuration.add(holiday.totalDuration!!)
//        totalDuration.add(holiday.ReturnTotalDuration!!)
//
//        val layOverCity = ArrayList<String>()
//
//        layOverCity.addAll(holiday.layOverCity!!)
//        layOverCity.addAll(holiday.ReturnLayOverCity!!)
//
//        val airlineName = ArrayList<String>()
//        airlineName.addAll(holiday.airlineName!!)
//        airlineName.addAll(holiday.ReturnAirlineName!!)
//
//
//        val flightModel = ArrayList<String>()
//        flightModel.addAll(holiday.flightModel!!)
//        flightModel.addAll(holiday.ReturnFlightModel!!)
//
//        detailsTowList.adapter= ReturnDetails(this@DetailsTow, holiday)
//





//        val sections = ArrayList<categore>()
//
//        //Sections
//        sections.add(categore(0, "Departure"))
////        sections.add(categore(1, "Return"))
//
//
//        val sections = ArrayList<SimpleSectionedRecyclerViewAdapter.Section>()
//
//        //Sections
//        sections.add(SimpleSectionedRecyclerViewAdapter.Section(0, "Departure"))
//        sections.add(SimpleSectionedRecyclerViewAdapter.Section(1, "Return"))
//
//        //Add your adapter to the sectionAdapter
//        val dummy = arrayOfNulls<SimpleSectionedRecyclerViewAdapter.Section>(sections.size)
//        val mSectionedAdapter = SimpleSectionedRecyclerViewAdapter(this,com.mustafayusef.holidaymaster.R.layout.header_ticket
//            , com.mustafayusef.holidaymaster.R.id.header
//            , OneDetailsAdapter(this@DetailsTow, holiday.arrCityName!!,
//            holiday.depCityName!!, holiday.depDateAndTime!!, holiday.arrDateAndTime!!
//            , holiday.airlineLogo!!, holiday.totalDuration!!, holiday.layOverCity!!, holiday.airlineName!!, holiday.flightModel!!
//        ))
//
//        mSectionedAdapter.setSections(sections.toTypedArray())
//
//        //Apply this adapter to the RecyclerView
//        detailsTowList.adapter = mSectionedAdapter








    }

    fun backToTow(view: View){
        super.onBackPressed()


    }

}


