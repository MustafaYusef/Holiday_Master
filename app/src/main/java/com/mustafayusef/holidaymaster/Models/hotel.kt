package com.mustafayusef.holidaymaster.Models

import java.io.Serializable

data class hotel (
    val id: String? = null,
    val CHILDREN: Long? = null,
    val INFANT: Long? = null,
    val name: String? = null,
    val body: String? = null,
    val country: String? = null,
    val address: String? = null,
    val stars: Double? = null,
    val img: String? = null,
    val breakfast: Boolean? = null,
    val breakfastPrice: Long? = null,
    val City: String? = null,
    val map: String? = null,
    val Nights: Long? = null,
    val options: List<Option>? = null,
    val Rooms: List<Room>? = null,
    val googleMap: String? = null,
    val uptime: String? = null
):Serializable

data class Option (
    val name: String? = null,
    val cost: Long? = null
):Serializable

data class Room (
    val name: String? = null,
    var cost: Long? = null,
    val beds: Long? = null
):Serializable