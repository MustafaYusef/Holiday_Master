package com.mustafayusef.holidaymaster.Models.otherOrd

data class Result(
    val _id: String,
    val buy: Boolean,
    val payed: Boolean,
    val price: Int,
    val type: String,
    val uptime: String
)