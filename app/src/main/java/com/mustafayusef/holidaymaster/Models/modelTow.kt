package com.mustafayusef.holidaymaster.Models

import java.io.Serializable

data class modelTow(
    val id: String,
    val totalDuration: String,
    val stops: Long,
    val airlineLogo: List<String>,
    val price: String,
    val logoCover: String,
    val depCityName: List<String>,
    val arrCityName: List<String>,
    val depDateAndTime: List<String>,
    val arrDateAndTime: List<String>,
    val airlineName: List<String>,
    val flightNumber: List<String>,
    val layOverTime: List<String>,
    val layOverMinutes: List<String>,
    val layOverCity: List<String>,
    val arrAirportName: List<String>,
    val flightModel: List<String>,
    val departingAirportName: List<String>,
    val arrivalAirportName: List<String>,
    val returnairlineLogo: List<String>,
    val returnTotalDuration: String,
    val returnStops: Long,
    val returnDepartingAirportName: List<String>,
    val returnArrivalAirportName: List<String>,
    val returnLogoCover: String,
    val returnDepCityName: List<String>,
    val returnArrCityName: List<String>,
    val returnAepDateAndTime: List<String>,
    val returnArrDateAndTime: List<String>,
    val returnAirlineName: List<String>,
    val returnFlightNumber: List<String>,
    val returnLayOverTime: List<String>,
    val returnLayOverMinutes: List<String>,
    val returnLayOverCity: List<String>,
    val returnArrAirportName: List<String>,
    val returnFlightModel: List<String>

):Serializable