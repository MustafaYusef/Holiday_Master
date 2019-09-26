package com.mustafayusef.holidaymaster.Models.ticketDetails

import java.io.Serializable

data class Result(
    val Baggage: List<Baggage>,
    val `data`: List<Data>,
    val searchParams: SearchParams,
    val sessionID: String
):Serializable