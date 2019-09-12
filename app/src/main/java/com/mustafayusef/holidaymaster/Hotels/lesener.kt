package com.mustafayusef.holidaymaster.Hotels

import com.mustafayusef.holidaymaster.Models.Buggage
import com.mustafayusef.holidaymaster.Models.group
import com.mustafayusef.holidaymaster.Models.hotel

interface lesener {

        fun OnStart()
        fun onFailer(message:String)
        fun onSucsess(Response: List<hotel>)

}