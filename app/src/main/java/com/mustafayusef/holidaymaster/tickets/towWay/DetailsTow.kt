package com.mustafayusef.holidaymaster.tickets.towWay

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.Adapters.MainAdapter

import com.mustafayusef.holidaymaster.Models.DataTow
import com.mustafayusef.holidaymaster.Models.ResultTow
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.tickets.Buggage.buggage
import com.mustafayusef.holidaymaster.tickets.FareRules.Fare
import com.mustafayusef.holidaymaster.tickets.breakUp.breakUp
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_return_tow_way.*
import kotlinx.android.synthetic.main.nested.*


class DetailsTow : Fragment() {
    lateinit var holiday: DataTow
    var session=""
    var Id=""
    var adult=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nested, container, false)
    }

    lateinit var holidayAll:ResultTow
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        var i:Int=0

        holidayAll =  arguments?.getSerializable("tecketTow") as ResultTow
        holiday = arguments!!.getSerializable("TowWay") as DataTow
        session=  arguments!!.getString("session")!!
        adult= arguments!!.getInt("adult",0)
        Id= arguments!!.getString("Id")!!
       locationDatesList .layoutManager = LinearLayoutManager(context)
       val headers= mutableListOf<String>("Departure","Return")
        // Access RecyclerView Adapter and load the data
        val adapter = MainAdapter(context!!,holiday,headers)
        locationDatesList.adapter = adapter


        purTow.text="Purchase for "+holiday.price+" $"


        purTow?.setOnClickListener {
            var bundel= Bundle()
            bundel.putSerializable("ticket",holidayAll)
            bundel.putString("Id",holiday._id)
            view?.findNavController()?.navigate(R.id.ticketDetailstowWay,bundel)
        }



        BuggageBtnT.setOnClickListener {
            val intent = Intent(context, buggage::class.java)
//
            intent.putExtra("session", session)
            intent.putExtra("Id", holiday._id)
            intent.putExtra("type","RT")
            startActivity(intent)}

        FareBtnT.setOnClickListener {
            val intent = Intent(context, Fare::class.java)
//
            intent.putExtra("session", session)
            intent.putExtra("Id", holiday._id)
            intent.putExtra("type","RT")
            startActivity(intent)}

        breakBtnT.setOnClickListener {
            val intent = Intent(context, breakUp::class.java)
//
            intent.putExtra("adult", adult.toString())
            intent.putExtra("AdultBaseFare", holiday.AdultBaseFare.toString())
            intent.putExtra("AdultTaxFare", holiday.AdultTax.toString())
            intent.putExtra("price", holiday.price .toString())
            startActivity(intent)}




    }


}


