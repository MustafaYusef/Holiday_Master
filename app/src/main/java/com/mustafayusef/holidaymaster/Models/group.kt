package com.mustafayusef.holidaymaster.Models

import java.io.Serializable

data class group (
    val Data: List<Data>? = null,
    val opt: List<Opt>? = null,
    val _id: String? = null,
    val name: String? = null,
    val body: String? = null,
    val img: String? = null,
    val pdf: String? = null,
    val Country: String? = null,
    val City: String? = null,
    val price: Long? = null,
    val priceCh: Long? = null,
    val offers: Boolean? = null,
    val priceINf: Long? = null,
    val priceSingle: Long? = null,
    val uptime: String? = null,
    val __v: Long? = null
): Serializable

//data class Data (
//    val id: Long? = null,
//    val ToursDate: String? = null,
//    var ToursSets: Double? = null
//):Serializable

data class Opt (
    val food: Boolean? = null,
    val Transport: Boolean? = null,
    val Visa: Boolean? = null,
    val Flight: Boolean? = null,
    val TourismProgram: Boolean? = null,
    val Hotel: Boolean? = null
):Serializable