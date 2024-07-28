package com.app.travelmate.presentation.ui.home

import androidx.annotation.LayoutRes
import com.app.travelmate.data.model.destination.CityItem
import com.app.travelmate.data.model.destination.DestinationCatalog
import com.app.travelmate.domain.exception.APIException

data class HomeScreenState(
    val isLoading: Boolean,
    val destinationsCatalog: DestinationCatalog?,
    val filteredCityList: List<CityItem> = emptyList(),
    val isError: APIException? = null
)

fun HomeScreenState.getCityItems(index: Int) = destinationsCatalog?.items?.get(index)?.cityList
fun HomeScreenState.getDestinationCount() = destinationsCatalog?.items?.size

@LayoutRes
fun HomeScreenState.getCarouselItems() = destinationsCatalog?.items?.map { it.image }

