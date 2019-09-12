package com.mustafayusef.holidaymaster.Models

import java.io.Serializable

data class oneWay(
    val result: Result?=null
):Serializable
data class Data1(
    val AdultBaseFare: Double,
    val AdultTax: Double,
    val AirlineCode: String,
    val AirlineName: String,
    val ChildBaseFare: Double,
    val ChildTax: Double,
    val Class: String,
    val Duration: List<String>,
    val FlightNo: List<String>,
    val InfantBaseFare: Double,
    val InfantTax: Double,
    val Refundable: String,
    val _id: String,
    val airCraftType: List<String>,
    val airNames: List<String>,
    val arrAirportName: List<String>,
    val arrCityName: List<String>,
    val arrDateAndTime: List<String>,
    val depAirportName: List<String>,
    val depCityName: List<String>,
    val depDateAndTime: List<String>,
    val layoverTime: List<String>,
    val logos: List<String>,
    val mainLogo: String,
    val price: Double,
    val stops: Int,
    val totalDuration: String
):Serializable
data class Result(
    val `data`: List<Data1?>?=null,
    val sessionID: String,
    val searchParams: SearchParams
):Serializable
data class SearchParams(
    val Adult: String,
    val ArrivalPoint: String,
    val CabinCode: String,
    val Child: String,
    val DepartureDate: String,
    val DeparturePoint: String,
    val DirectFlight: String,
    val Infant: String
):Serializable