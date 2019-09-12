package com.mustafayusef.holidaymaster.Models

data class TourOrder(
    val order: Order
)
data class Order(
    val Adults: Int,
    val Child: Int,
    val Data: List<Data2>,
    val Infant: Int,
    val __v: Int,
    val _id: String,
    val price: Int,
    val type: String,
    val uptime: String
)
data class Item(
    val Country: String,
    val Data: List<Data>,
    val __v: Int,
    val _id: String,
    val body: String,
    val city: String,
    val img: String,
    val name: String,
    val offers: Boolean,
    val price: Int,
    val priceCh: Int,
    val priceINf: Int,
    val uptime: String
)
data class Info(
    val ADULTSNumber: Int,
    val ChildrenNumber: Int,
    val ToursDate: String,
    val ToursSets: Double
)
data class Data2(
    val Date: List<Date>,
    val Item: Item,
    val info: Info
)
