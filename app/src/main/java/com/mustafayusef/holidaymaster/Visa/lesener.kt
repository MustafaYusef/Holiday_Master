package com.mustafayusef.holidaymaster.Visa

import com.mustafayusef.holidaymaster.Models.country

interface lesener {

        fun OnStart()
        fun onFailer(message:String)
        fun onSucsess(Response: List<country>)
        fun onSucsessSearch(Response: List<country>)

}