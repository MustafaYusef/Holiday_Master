package com.mustafayusef.holidaymaster.Models



data class profileAuth (
    val sesson: Sesson
)

data class Sesson (
    val id: String,
    val name: String,
    val email: String,
    val role: Long,
    val money: Long,
    val country: String,
    val phone: String,
    val uptime: String,
    val v: Long
)
