package com.mustafayusef.holidaymaster.tickets.leseners

import com.mustafayusef.holidaymaster.Models.Result
import com.mustafayusef.holidaymaster.Models.ResultTow

interface getDataLesener {

    fun OnStart()
    fun onSucsess(oneWayResponse: Result)
    fun onSucsessTow(TowWayResponse: ResultTow)
    fun onFailer(message:String)

}