package com.mustafayusef.holidaymaster.login.profile.dashBoard

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafayusef.holidaymaster.Models.TicketOrder.TicketOrderRes
import com.mustafayusef.holidaymaster.Models.otherOrd.otherOrderRes

import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.login.LoginMember
import com.mustafayusef.holidaymaster.login.LoginViewModelFactory
import com.mustafayusef.holidaymaster.login.profile.dashBoard.adaptersProfile.OtherTicketAdapter
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.order_tickets_fragment.*
import kotlinx.android.synthetic.main.progress2.*

class OtherOrder : Fragment(),Dashlesener {
    override fun OnStart() {
        animation_viewTourPub?.visibility=View.VISIBLE
    }

    override fun onFailer(message: String) {
        animation_viewTourPub?.visibility=View.VISIBLE
    }

    override fun onSucsessTicket(Response: TicketOrderRes) {
    }

    override fun onSucsessOther(Response: otherOrderRes) {
        orderTicket?.layoutManager= LinearLayoutManager(context!!)
        orderTicket?.adapter= OtherTicketAdapter(context!!,Response)
        animation_viewTourPub?.visibility=View.GONE}

    companion object {
        fun newInstance() = OtherOrder()
    }

    private lateinit var viewModel: OrderTicketsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_tickets_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textViewqqq?.text="Other Orders"
        val networkIntercepter= networkIntercepter(context!!)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= DashViewModelFactory(repostary)
        viewModel = ViewModelProviders.of(this,factory).get(OrderTicketsViewModel::class.java)
        viewModel?.dataLesener=this
        viewModel?.GetOtherOrder(LoginMember.cacheObj.token)
    }

}
