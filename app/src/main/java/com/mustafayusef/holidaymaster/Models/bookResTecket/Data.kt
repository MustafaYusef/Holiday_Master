package com.mustafayusef.holidaymaster.Models.bookResTecket

data class Data(
    val AirPNR: String,
    val Wallet: Double,
    val passengerInfo: PassengerInfo,
    val price: String,
    val type: String
)