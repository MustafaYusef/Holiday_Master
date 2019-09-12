package com.mustafayusef.holidaymaster.tickets.FareRules

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.mustafayusef.holidaymaster.Models.FareRules
import com.mustafayusef.holidaymaster.Models.ResultRule
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.tickets.leseners.FareLesener
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.activity_fare.*

class Fare : AppCompatActivity() ,FareLesener{
    var description: ResultRule?=null
    override fun OnStart() {
        animation_viewFare.playAnimation()
        animation_viewFare.visibility=View.VISIBLE
    }

    override fun onFailer(message: String) {
        toast(message)
        animation_viewFare.visibility=View.GONE
    }

    override fun onSucsessFare(FareResponse: FareRules) {
        animation_viewFare.visibility=View.GONE
        fareCont.visibility=View.VISIBLE
        AirFare.text=FareResponse.result.Airline
        FareBase.text=FareResponse.result.FareBasisCode
        FareType.text=FareResponse.result.FareType
        FareClass.text=FareResponse.result.BookingClass
        RefundFare.text=FareResponse.result.RefundType
        description=FareResponse.result

    }

    var viewmodel:FareViewModel?=null
    var session=""
    var Id=""
    var type=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fare)
        val networkIntercepter= networkIntercepter(this)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= FareViewModelFactory(repostary)
        session=  intent.getStringExtra("session")
        Id= intent.getStringExtra("Id")
        type=intent.getStringExtra("type")

        viewmodel = ViewModelProviders.of(this,factory).get(FareViewModel::class.java)
        viewmodel?.dataLesener=this
        viewmodel?.getFare (session,Id,type)

        FareInfo.setOnClickListener {
            val intent = Intent(this@Fare, com.mustafayusef.holidaymaster.tickets.FareRules.description::class.java)
//
            intent.putExtra("des",description)

            startActivity(intent)

        }
    }
}
