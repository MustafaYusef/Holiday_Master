package com.mustafayusef.holidaymaster.Models

import java.io.Serializable


data class modelOne (
    val id: String,
    val price: String,
    val totalDuration: String,
    val stops: Long,
    val airlineLogo: List<String>,
    val departingAirportName: List<String>,
    val arrivalAirportName: List<String>,
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
    val flightModel: List<String>
): Serializable
