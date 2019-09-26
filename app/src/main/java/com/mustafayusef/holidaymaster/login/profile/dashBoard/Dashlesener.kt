package com.mustafayusef.holidaymaster.login.profile.dashBoard

import com.mustafayusef.holidaymaster.Auth.auth
import com.mustafayusef.holidaymaster.Models.Buggage
import com.mustafayusef.holidaymaster.Models.TicketOrder.TicketOrderRes
import com.mustafayusef.holidaymaster.Models.group
import com.mustafayusef.holidaymaster.Models.otherOrd.otherOrderRes
import com.mustafayusef.holidaymaster.Models.profileAuth

interface Dashlesener {

        fun OnStart()
        fun onFailer(message:String)
        fun onSucsessTicket(Response: TicketOrderRes)
    fun onSucsessOther(Response: otherOrderRes)

}