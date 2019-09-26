package com.mustafayusef.holidaymaster.tickets.BookOneWay

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.mustafayusef.holidaymaster.R
import kotlinx.android.synthetic.main.fragment_success_ticket.*

class successTicket : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_success_ticket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        walletBalance?.text=arguments?.getString("wallet")
        TicketPrice?.text=arguments?.getString("price")
        pnTicket?.text="PNR: "+arguments?.getString("pn")

        doneTicket?.setOnClickListener {
            view?.findNavController()?.navigate(R.id.searchActivity)
        }
    }
}
