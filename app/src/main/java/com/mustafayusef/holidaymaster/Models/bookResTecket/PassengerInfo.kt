package com.mustafayusef.holidaymaster.Models.bookResTecket

data class PassengerInfo(
    val Adult: List<Adult>,
    val Child: List<Any>,
    val Infant: List<Infant>,
    val NoOfAdult: String,
    val NoOfChild: String,
    val NoOfInfant: String
)