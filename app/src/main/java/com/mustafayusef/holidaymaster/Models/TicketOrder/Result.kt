package com.mustafayusef.holidaymaster.Models.TicketOrder

data class Result(
    val AirPNR: String,
    val _id: String,
    val flight: String,
    val issue: String,
    val price: String,
    val type: String,
    val uptime: String
)