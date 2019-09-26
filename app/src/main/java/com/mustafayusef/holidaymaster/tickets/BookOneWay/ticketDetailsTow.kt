package com.mustafayusef.holidaymaster.tickets.BookOneWay


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.mustafayusef.holidaymaster.Models.ticketDetails.Result
import com.mustafayusef.holidaymaster.Models.ticketDetails.ticketDetails

import com.mustafayusef.holidaymaster.R
import com.mustafayusef.holidaymaster.utils.toast
import kotlinx.android.synthetic.main.fragment_ticket_details_tow.*
import kotlinx.android.synthetic.main.show_national.view.*
import kotlinx.android.synthetic.main.ticket_popup.view.*


class ticketDetailsTow : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket_details_tow, container, false)
    }
    var details:Result?=null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        details = arguments?.getSerializable("details") as Result

        Passengers?.text=(details!!.searchParams.Adult+details!!.searchParams.Child+
                details!!.searchParams.Infant).toString()
        Departure?.text=details!!.data[0].depCityName[0]
        DepartureTime?.text=details!!.data[0].depDateAndTime[0]
        Arrival?.text=details!!.data[0].arrCityName[0]
        ArrivalTime?.text=details!!.data[0].arrDateAndTime[0]
        Class?.text=details!!.data[0].Class
        AircraftType?.text=details!!.data[0].airCraftType[0]
        FlightNumber?.text=details!!.data[0].FlightNo[0]
        DurationFlight?.text=details!!.data[0].totalDuration

        BookTicket?.setOnClickListener {
            showDilog()
        }
    }
    fun showDilog() {

        val dview: View = layoutInflater.inflate(com.mustafayusef.holidaymaster.R.layout.ticket_popup, null)

        val builder = AlertDialog.Builder(context!!).setView(dview)
        val malert= builder.show()
        malert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
      dview?.finalBook?.setOnClickListener {
          if(!dview?.emailBook?.text.toString().isNullOrEmpty()&&!dview?.PhoneBook?.toString().isNullOrEmpty()){
              var bundel=Bundle()
              bundel.putSerializable("tecket",details)
              bundel.putString("email",dview?.emailBook?.text.toString())
              bundel.putString("phone",dview?.PhoneBook?.text.toString())
              view?.findNavController()?.navigate(R.id.bookFinalTicket,bundel)
              malert?.dismiss()
          }else{
              context?.toast("fill all the field")
          }
      }

    }
}
