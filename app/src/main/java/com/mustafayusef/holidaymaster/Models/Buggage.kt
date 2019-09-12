package com.mustafayusef.holidaymaster.Models

data class Buggage(
    val result: List<ResultBug>
)
data class ResultBug(
    val ADTCabin: String,
    val ADTCheckIn: String,
    val CHDCabin: String,
    val CHDCheckIn: String,
    val INFCabin: String,
    val INFCheckIn: String,
    val root: String
)