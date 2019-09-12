package com.mustafayusef.holidaymaster.Models

import java.io.Serializable

data class FareRules(
    val result: ResultRule
)
data class Description(
    val Description: String,
    val Title: String
):Serializable
data class ResultRule(
    val Airline: String,
    val BookingClass: String,
    val Description: List<Description>,
    val FareBasisCode: String,
    val FareType: String,
    val RefundType: String
):Serializable