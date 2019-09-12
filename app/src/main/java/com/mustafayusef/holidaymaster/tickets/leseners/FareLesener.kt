package com.mustafayusef.holidaymaster.tickets.leseners

import com.mustafayusef.holidaymaster.Models.FareRules

interface FareLesener {
    fun OnStart()
    fun onFailer(message:String)
    fun onSucsessFare(BuggageResponse: FareRules)
}