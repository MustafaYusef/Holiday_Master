package com.mustafayusef.holidaymaster.Models

import java.io.Serializable

data class hotel (
    val id: String? = null,
    val children: Long? = null,
    val infant: Long? = null,
    val name: String? = null,
    val body: String? = null,
    val country: String? = null,
    val address: String? = null,
    val stars: Float? = null,
    val img: String? = null,
    val breakfast: Boolean? = null,
    val breakfastPrice: Long? = null,
    val city: String? = null,
    val map: String? = null,
    val nights: Long? = null,
    val options: List<Option>? = null,
    val rooms: List<Room>? = null,
    val googleMap: String? = null,
    val uptime: String? = null
):Serializable

data class Option (
    val name: String? = null,
    val cost: Long? = null
)

data class Room (
    val name: String? = null,
    val cost: Long? = null,
    val beds: Long? = null
)