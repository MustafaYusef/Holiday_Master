package com.mustafayusef.holidaymaster.Models

import java.io.Serializable


data class Tours (
var Data: List<Data>? = null,
val id: String? = null,
val name: String? = null,
val body: String? = null,
val img: String? = null,
val Country: String? = null,
val price: Long? = null,
val priceCh: Long? = null,
val priceINf: Long? = null,
val uptime: String? = null,
val __v: Long? = null
):Serializable

data class Data (
    val id: Long? = null,
    val ToursDate: String? = null,
    var ToursSets: Double? = null
):Serializable