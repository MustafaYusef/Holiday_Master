package com.mustafayusef.holidaymaster.tickets.leseners

import com.mustafayusef.holidaymaster.Models.Buggage

interface buggageLesener {
    fun OnStart()
    fun onFailer(message:String)
    fun onSucsessBuggage(BuggageResponse: Buggage)
}