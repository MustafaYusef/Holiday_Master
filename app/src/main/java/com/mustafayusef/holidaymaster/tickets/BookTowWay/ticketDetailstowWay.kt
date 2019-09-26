package com.mustafayusef.holidaymaster.tickets.BookTowWay

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.mustafayusef.holidaymaster.Models.Data1
import com.mustafayusef.holidaymaster.Models.Result
import com.mustafayusef.holidaymaster.Models.ResultTow
import com.mustafayusef.holidaymaster.Models.bookResTecket.BookTicketResponse
import com.mustafayusef.holidaymaster.Models.ticketDetails.ticketDetails

import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.login.LoginMember
import com.mustafayusef.holidaymaster.networks.msg
import com.mustafayusef.holidaymaster.networks.myApis
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.holidaymaster.tickets.BookOneWay.lesenerTicketOne
import com.mustafayusef.holidaymaster.tickets.BookOneWay.ticketDetailsViewModel
import com.mustafayusef.holidaymaster.tickets.BookOneWay.ticketDetailsViewModelFactory
import com.mustafayusef.holidaymaster.tickets.Buggage.BaggageViewModelFactory
import com.mustafayusef.holidaymaster.tickets.Buggage.buggageViewModel
import com.mustafayusef.sharay.data.networks.repostorys.userRepostary
import kotlinx.android.synthetic.main.fragment_ticket_details.*
import kotlinx.android.synthetic.main.progress.*
import kotlinx.android.synthetic.main.progress2.*

class ticketDetailstowWay : Fragment(),lesenerTicketOne {
    override fun onSucsessBook(Response: BookTicketResponse) {

    }

    override fun OnStart() {
        conD?.visibility=View.GONE
        animation_viewTourPub?.visibility=View.VISIBLE
    }

    override fun onFailer(message: String) {
        animation_viewTourPub?.visibility=View.GONE
        conD?.visibility=View.GONE
    }

    override fun onSucsess(Response: ticketDetails) {
        conD?.visibility=View.VISIBLE
        nextTicketBook?.visibility=View.VISIBLE
        animation_viewTourPub?.visibility=View.GONE
        totalPriceD?.text=Response.result.data[0].price.toString()
        TaxFareD?.text=(Response.result.data[0].AdultTax+Response.result.data[0].ChildTax+
                Response.result.data[0].InfantTax).toString()
        if(Response.result.data[0].InfantBaseFare==0.0){
            infCon?.visibility=View.GONE

        }else{
            InfantFare?.text=Response.result.data[0].InfantBaseFare.toString()
        }
        if (Response.result.data[0].ChildBaseFare==0.0){
            ChildCon?.visibility=View.GONE
        }else{
            ChildFare?.text= Response.result.data[0].ChildBaseFare.toString()
        }
        AdulFare?.text=  Response.result.data[0].AdultBaseFare.toString()
        ticket1=Response.result

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket_details, container, false)
    }
    var ticket1:com.mustafayusef.holidaymaster.Models.ticketDetails.Result?=null
  lateinit var viewmodel:ticketDetailsViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var ticket: ResultTow=arguments?.getSerializable("ticket") as ResultTow
        var id=arguments!!.getString("Id")
        val networkIntercepter= networkIntercepter(context!!)
        val api= myApis(networkIntercepter)
        val repostary= userRepostary(api)
        val factory= ticketDetailsViewModelFactory(repostary)
        viewmodel = ViewModelProviders.of(this,factory).get(ticketDetailsViewModel::class.java)
        viewmodel?.dataLesener=this
        viewmodel?.GetDetails(LoginMember.cacheObj.token,"rt",ticket.searchParams.Adult!!,ticket.searchParams.Child!!,
            ticket.searchParams.Infant!!,
            id!!,ticket.sessionID)

        nextTicketBook?.setOnClickListener {
            var bundel=Bundle()
            bundel.putSerializable("details",ticket1)
            bundel.putString("session",ticket.sessionID)
            view?.findNavController()?.navigate(R.id.ticketDetailsTowTow,bundel)
        }

    }
}
