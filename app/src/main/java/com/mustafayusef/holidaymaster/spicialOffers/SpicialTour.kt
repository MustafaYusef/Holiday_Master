package com.mustafayusef.holidaymaster.spicialOffers

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.Models.Tours
import com.mustafayusef.holidaymaster.Models.group

import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.Tours.ToursAdapter
import com.mustafayusef.holidaymaster.Tours.ToursViewModel
import com.mustafayusef.holidaymaster.Tours.toursViewModelFactory
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.activity_tours.*
import kotlinx.android.synthetic.main.progress.*
import kotlinx.android.synthetic.main.progress2.*
import kotlinx.android.synthetic.main.spicial_group_fragment.*

class SpicialTour : Fragment(),lesenerOffers {
    override fun OnStart() {
        animation_viewTourPub?.visibility=View.VISIBLE
    }

    override fun onFailer(message: String) {
        animation_viewTourPub?.visibility=View.GONE
    }

    override fun onSucsessTour(Response: List<Tours>) {
        SpicialOff_list?.layoutManager= LinearLayoutManager(context)
        SpicialOff_list?.adapter= ToursAdapter(context!!, Response)
        animation_viewTourPub?.visibility=View.GONE



    }

    override fun onSucsessGroup(Response: List<group>) {
    }

    companion object {
        fun newInstance() = SpicialTour()
    }

    private lateinit var viewModel: SpicialGroupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.spicial_group_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkIntercepter= networkIntercepter(context!!)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= offersViewModelFactory(repostary)
        viewModel = ViewModelProviders.of(this,factory).get(SpicialGroupViewModel::class.java)
        viewModel?.dataLesener=this
        viewModel?.GetTours()


    }

}
