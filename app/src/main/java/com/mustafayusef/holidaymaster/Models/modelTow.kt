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
    val returnairlineLogo: List<String>? = null,
    val returnTotalDuration: String? = null,
    val returnStops: Long? = null,
    val returnDepartingAirportName: List<String>? = null,
    val returnArrivalAirportName: List<String>? = null,
    val returnLogoCover: String? = null,
    val returnDepCityName: List<String>? = null,
    val returnArrCityName: List<String>? = null,
    val returnAepDateAndTime: List<String>? = null,
    val returnArrDateAndTime: List<String>? = null,
    val returnAirlineName: List<String>? = null,
    val returnFlightNumber: List<String>? = null,
    val returnLayOverTime: List<String>? = null,
    val returnLayOverMinutes: List<String>? = null,
    val returnLayOverCity: List<String>? = null,
    val returnArrAirportName: List<String>? = null,
    val returnFlightModel: List<String>? = null


): Serializable