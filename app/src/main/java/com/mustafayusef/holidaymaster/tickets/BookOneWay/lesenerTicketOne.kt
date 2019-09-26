package com.mustafayusef.holidaymaster.tickets.BookOneWay


import com.mustafayusef.holidaymaster.Models.bookResTecket.BookTicketResponse
import com.mustafayusef.holidaymaster.Models.ticketDetails.ticketDetails
import com.mustafayusef.holidaymaster.networks.msg

interface lesenerTicketOne {

        fun OnStart()
        fun onFailer(message:String)
        fun onSucsess(Response: ticketDetails)
        fun onSucsessBook(Response: BookTicketResponse)


}