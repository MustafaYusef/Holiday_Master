package com.mustafayusef.holidaymaster.Groups

import com.mustafayusef.holidaymaster.Models.Buggage
import com.mustafayusef.holidaymaster.Models.TourOrder
import com.mustafayusef.holidaymaster.Models.group
import com.mustafayusef.holidaymaster.Tours.tok

interface lesener {

        fun OnStart()
        fun onFailer(message:String)
        fun onSucsess(Response: List<group>)
        fun onSucsessBook(Response: tok)
        fun onSucsessGetOrderGroup(Response: TourOrder)

}