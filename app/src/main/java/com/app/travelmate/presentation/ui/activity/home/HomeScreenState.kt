package com.app.travelmate.presentation.ui.activity.home

import com.app.travelmate.data.model.destination.CityItem
import com.app.travelmate.data.model.destination.DestinationCatalog
import com.app.travelmate.domain.exception.APIException

data class HomeScreenState(
    val isLoading: Boolean,
    val destinationsCatalog: DestinationCatalog?,
    val filteredCityList: List<CityItem> = emptyList(),
    val isError: APIException? = null
)