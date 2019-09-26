package com.mustafayusef.holidaymaster.Tours

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.mustafayusef.holidaymaster.Groups.groupViewModelFactory
import com.mustafayusef.holidaymaster.Groups.groupsViewModel
import com.mustafayusef.holidaymaster.Models.TourOrder
import com.mustafayusef.holidaymaster.Models.Tours
import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.networks.msg
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.utils.toast
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_show_holiday.*
import kotlinx.android.synthetic.main.activity_tours.*
import kotlinx.android.synthetic.main.progress2.*
import okhttp3.*
import java.io.IOException

class Tours_main : Fragment(),lesener {
    override fun onSucsessGetOrder(Response: TourOrder) {

    }

    override fun onSucsessBookTour(message: msg) {
       // context?.toast("Done")
    }


    override fun onSucsessBook(Response: tok) {
     //   context?.toast(Response.orderToken)
    }


    var GroupviewModel:ToursViewModel?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_tours, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val networkIntercepter= networkIntercepter(context!!)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= toursViewModelFactory(repostary)
        GroupviewModel = ViewModelProviders.of(this,factory).get(ToursViewModel::class.java)
        GroupviewModel?.dataLesener=this
        GroupviewModel?.GetTours()


    }

    override fun OnStart() {
 //context?.toast("start")
        animation_viewTourPub?.visibility=View.VISIBLE
    }

    override fun onFailer(message: String) {
        animation_viewTourPub?.visibility=View.GONE
    }

    override fun onSucsess(Response: List<Tours>) {
        Tours_list?.layoutManager= LinearLayoutManager(context)
        Tours_list?.adapter= ToursAdapter(context!!, Response)
        animation_viewTourPub?.visibility=View.GONE

    }

}
