package com.mustafayusef.holidaymaster.tickets.towWay

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.Adapters.MainAdapter

import com.mustafayusef.holidaymaster.Models.DataTow
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.tickets.Buggage.buggage
import com.mustafayusef.holidaymaster.tickets.FareRules.Fare
import com.mustafayusef.holidaymaster.tickets.breakUp.breakUp
import kotlinx.android.synthetic.main.nested.*


class DetailsTow : AppCompatActivity() {
    lateinit var holiday: DataTow
    var session=""
    var Id=""
    var adult=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nested)

        var i:Int=0

        holiday = intent.getSerializableExtra("TowWay") as DataTow
        session= intent.getStringExtra("session")
        adult=intent.getIntExtra("adult",0)
        Id=intent.getStringExtra("Id")
       locationDatesList .layoutManager = LinearLayoutManager(this)
       val headers= mutableListOf<String>("Departure","Return")
        // Access RecyclerView Adapter and load the data
        val adapter = MainAdapter(this@DetailsTow,holiday,headers)
        locationDatesList.adapter = adapter

        val date = this.findViewById<Button>(R.id.purTow)
        date.text="Purchase for "+holiday.price+" $"


        BuggageBtnT.setOnClickListener {
            val intent = Intent(this@DetailsTow, buggage::class.java)
//
            intent.putExtra("session", session)
            intent.putExtra("Id", holiday._id)
            intent.putExtra("type","RT")
            startActivity(intent)}

        FareBtnT.setOnClickListener {
            val intent = Intent(this@DetailsTow, Fare::class.java)
//
            intent.putExtra("session", session)
            intent.putExtra("Id", holiday._id)
            intent.putExtra("type","RT")
            startActivity(intent)}

        breakBtnT.setOnClickListener {
            val intent = Intent(this@DetailsTow, breakUp::class.java)
//
            intent.putExtra("adult", adult.toString())
            intent.putExtra("AdultBaseFare", holiday.AdultBaseFare.toString())
            intent.putExtra("AdultTaxFare", holiday.AdultTax.toString())
            intent.putExtra("price", holiday.price .toString())
            startActivity(intent)}





    }


}


