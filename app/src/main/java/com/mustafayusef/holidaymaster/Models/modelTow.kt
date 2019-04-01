package com.mustafayusef.holidaymaster.Models

import java.io.Serializable

data class modelTow(
    val id: String? = null,
    val totalDuration: String? = null,
    val stops: Long? = null,
    val airlineLogo: List<String>? = null,
    val price: String? = null,
    val logoCover: String? = null,
    val depCityName: List<String>? = null,
    val arrCityName: List<String>? = null,
    val depDateAndTime: List<String>? = null,
    val arrDateAndTime: List<String>? = null,
    val airlineName: List<String>? = null,
    val flightNumber: List<String>? = null,
    val layOverTime: List<String>? = null,
    val layOverMinutes: List<String>? = null,
    val layOverCity: List<String>? = null,
    val arrAirportName: List<String>? = null,
    val flightModel: List<String>? = null,
    val departingAirportName: List<String>? = null,
    val arrivalAirportName: List<String>? = null,
    val ReturnairlineLogo: List<String>? = null,
    val ReturnTotalDuration: String? = null,
    val ReturnStops: Long? = null,
    val ReturnDepartingAirportName: List<String>? = null,
    val ReturnArrivalAirportName: List<String>? = null,
    val ReturnLogoCover: String? = null,
    val ReturnDepCityName: List<String>? = null,
    val ReturnArrCityName: List<String>? = null,
    val ReturnAepDateAndTime: List<String>? = null,
    val ReturnArrDateAndTime: List<String>? = null,
    val ReturnAirlineName: List<String>? = null,
    val ReturnFlightNumber: List<String>? = null,
    val ReturnLayOverTime: List<String>? = null,
    val ReturnLayOverMinutes: List<String>? = null,
    val ReturnLayOverCity: List<String>? = null,
    val ReturnArrAirportName: List<String>? = null,
    val ReturnFlightModel: List<String>? = null


): Serializable