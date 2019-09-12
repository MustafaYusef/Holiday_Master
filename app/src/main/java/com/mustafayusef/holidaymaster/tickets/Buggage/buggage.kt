package com.mustafayusef.holidaymaster.tickets.Buggage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.Adapters.buggageAdapter
import com.mustafayusef.holidaymaster.Models.Buggage
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.tickets.leseners.buggageLesener
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.activity_buggage.*

class buggage : AppCompatActivity(), buggageLesener {



    var viewmodel:buggageViewModel?=null
    var session=""
    var Id=""
    var type=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buggage)
        session=  intent.getStringExtra("session")
        Id= intent.getStringExtra("Id")
        type=intent.getStringExtra("type")

        val networkIntercepter= networkIntercepter(this)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= BaggageViewModelFactory(repostary)
        viewmodel = ViewModelProviders.of(this,factory).get(buggageViewModel::class.java)
        viewmodel?.dataLesener=this
        viewmodel?.getBuggage(session,Id,type)

    }
    override fun OnStart() {
        animation_viewBugg.playAnimation()
        animation_viewBugg.visibility= View.VISIBLE
    }

    override fun onSucsessBuggage(BuggageResponse: Buggage) {
       // this.toast(BuggageResponse.result.toString())
        BuggageList.layoutManager= LinearLayoutManager(this)
        BuggageList.adapter=buggageAdapter(this,BuggageResponse.result)
        animation_viewBugg.visibility= View.GONE
    }

    override fun onFailer(message: String) {
        this.toast(message)
        animation_viewBugg.visibility= View.GONE
    }
}
