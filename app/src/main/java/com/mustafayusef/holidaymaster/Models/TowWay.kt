package com.mustafayusef.holidaymaster.Models

import java.io.Serializable

data class TowWay(
    val result: ResultTow
):Serializable
data class DataTow(
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
    val return_Class: String,
    val return_Duration: List<String>,
    val return_FlightNo: List<String>,
    val return_airCraftType: List<String>,
    val return_airNames: List<String>,
    val return_arrAirportName: List<String>,
    val return_arrCityName: List<String>,
    val return_arrDateAndTime: List<String>,
    val return_depAirportName: List<String>,
    val return_depCityName: List<String>,
    val return_depDateAndTime: List<String>,
    val return_layoverTime: List<String>,
    val return_logos: List<String>,
    val return_mainLogo: String,
    val return_stops: Int,
    val return_totalDuration: String,
    val stops: Int,
    val totalDuration: String
):Serializable

data class ResultTow(
    var `data`: List<DataTow>,
    val searchParams: SearchParamsTow,
    val sessionID: String
):Serializable

data class SearchParamsTow(
    val Adult: String,
    val ArrivalPoint: String,
    val CabinCode: String,
    val Child: String,
    val DepartureDate: String,
    val DeparturePoint: String,
    val DirectFlight: String,
    val Infant: String
):Serializable