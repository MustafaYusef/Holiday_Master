package com.mustafayusef.holidaymaster.spicialOffers


import com.mustafayusef.holidaymaster.Models.Tours
import com.mustafayusef.holidaymaster.Models.bookResTecket.BookTicketResponse
import com.mustafayusef.holidaymaster.Models.group
import com.mustafayusef.holidaymaster.Models.ticketDetails.ticketDetails
import com.mustafayusef.holidaymaster.networks.msg

interface lesenerOffers {

        fun OnStart()
        fun onFailer(message:String)
        fun onSucsessTour(Response: List<Tours>)
        fun onSucsessGroup(Response: List<group>)


}