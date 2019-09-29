package com.mustafayusef.holidaymaster.tickets.oneWay

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.Adapters.OneDetailsAdapter
import com.mustafayusef.holidaymaster.Models.Data1
import com.mustafayusef.holidaymaster.Models.Result

import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.tickets.Buggage.buggage
import com.mustafayusef.holidaymaster.tickets.FareRules.Fare
import com.mustafayusef.holidaymaster.tickets.breakUp.breakUp

import kotlinx.android.synthetic.main.activity_details_one.*
import kotlinx.android.synthetic.main.activity_details_one.view.*
import kotlinx.android.synthetic.main.activity_profile.*


class DetailsOne : Fragment() {
    lateinit var holiday: Data1
    var session=""
    var adult=0
    var Id=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_details_one, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var i:Int=0
        holiday= arguments!!.getSerializable("oneway") as Data1
        session=arguments!!.getString("sessionId")!!
        adult= arguments!!.getInt("adult",0)
        Id= arguments!!.getString("Id")!!

       var tecket: Result =arguments!!.getSerializable("tecket") as Result


        this.detailsOneList.layoutManager= LinearLayoutManager(context)
        detailsOneList.adapter= OneDetailsAdapter(context!!,holiday.depDateAndTime,
            holiday.arrDateAndTime,holiday.layoverTime,holiday.depCityName
            ,holiday.arrCityName,holiday.Duration,holiday.logos,holiday.airNames)

        BuggageBtn.setOnClickListener {
            val intent = Intent(context, buggage::class.java)
//
            intent.putExtra("session", session)
            intent.putExtra("Id", holiday._id)
            intent.putExtra("type","OW")
        startActivity(intent)}

        FareBtn.setOnClickListener {
            val intent = Intent(context, Fare::class.java)
//
            intent.putExtra("session", session)
            intent.putExtra("Id", holiday._id)
            intent.putExtra("type","OW")
            startActivity(intent)}

        breakBtn.setOnClickListener {
            val intent = Intent(context, breakUp::class.java)
//
            intent.putExtra("adult", adult.toString())
            intent.putExtra("AdultBaseFare", holiday.AdultBaseFare.toString())
            intent.putExtra("AdultTaxFare", holiday.AdultTax.toString())
            intent.putExtra("price", holiday.price .toString())
            startActivity(intent)}

        purOneD.text="Purchase for "+holiday.price+" $"

       purOneD?.setOnClickListener {
            var bundel= Bundle()
            bundel.putSerializable("ticket",tecket)
            bundel.putString("Id",holiday._id)
           view?.findNavController()?.navigate(R.id.ticketDetails,bundel)
        }
         //   typ="OW"




        }

}
