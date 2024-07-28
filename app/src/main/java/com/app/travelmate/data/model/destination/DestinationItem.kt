package com.app.travelmate.data.model.destination

data class DestinationItem(
    val name: String,
    val image: Int,
    val cityList: List<CityItem>
)