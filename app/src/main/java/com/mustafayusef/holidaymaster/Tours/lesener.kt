package com.mustafayusef.holidaymaster.Tours

import com.mustafayusef.holidaymaster.Models.TourOrder
import com.mustafayusef.holidaymaster.Models.Tours
import com.mustafayusef.holidaymaster.networks.msg

interface lesener {

        fun OnStart()
        fun onFailer(message:String)
        fun onSucsess(Response: List<Tours>)
        fun onSucsessBook(Response:tok)

    fun onSucsessGetOrder(Response:TourOrder)

    fun onSucsessBookTour(message: msg)
}
data class tok(
      var orderToken:String
)