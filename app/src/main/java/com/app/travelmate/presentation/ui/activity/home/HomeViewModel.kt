package com.app.travelmate.presentation.ui.activity.home

import androidx.annotation.LayoutRes
import androidx.lifecycle.viewModelScope
import com.app.travelmate.R
import com.app.travelmate.data.model.destination.CityItem
import com.app.travelmate.domain.common.ResponseState
import com.app.travelmate.domain.exception.APIException
import com.app.travelmate.domain.usecase.GetDestinationCatalogUseCase
import com.app.travelmate.presentation.base.BaseViewModel
import com.app.travelmate.presentation.model.BottomSheetDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.PriorityQueue

class HomeViewModel(private val getDestinationCatalogUseCase: GetDestinationCatalogUseCase) :
    BaseViewModel() {

    init {
        setInitialState()
    }

    private val initialState by lazy { setInitialState() }

    private val _homeScreenState = MutableStateFlow(initialState)
    val homeScreenState: StateFlow<HomeScreenState> = _homeScreenState

    private val _filterItemsLiveData = MutableStateFlow<List<CityItem>>(emptyList())
    val filterItemsLiveData = _filterItemsLiveData

    private fun setInitialState() = HomeScreenState(
        isLoading = false,
        destinationsCatalog = null,
        filteredCityList = emptyList(),
        isError = null
    )

    fun fetchDestinationData() {
        viewModelScope.launch {
            getDestinationCatalogUseCase()
                .catch { cause ->
                    _homeScreenState.value = _homeScreenState.value.copy(
                        isLoading = false,
                        destinationsCatalog = null,
                        filteredCityList = emptyList(),
                        isError = APIException(genericError = R.string.generic_error_message)
                    )
                }
                .onEach { destinationData ->
                    when (destinationData) {
                        is ResponseState.Loading -> {
                            _homeScreenState.value = _homeScreenState.value.copy(
                                isLoading = true,
                                destinationsCatalog = null,
                                filteredCityList = emptyList(),
                                isError = APIException(genericError = R.string.generic_error_message)
                            )
                        }

                        is ResponseState.SuccessState -> {
                            _homeScreenState.value = _homeScreenState.value.copy(
                                isLoading = false,
                                destinationsCatalog = destinationData.data,
                                filteredCityList = emptyList(),
                                isError = null
                            )
                        }

                        is ResponseState.ErrorState -> {
                            _homeScreenState.value = _homeScreenState.value.copy(
                                isLoading = false,
                                destinationsCatalog = null,
                                filteredCityList = emptyList(),
                                isError = APIException(genericError = R.string.generic_error_message)
                            )
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    @LayoutRes
    fun getHomeCarouselData(): List<Int> {
        return _homeScreenState.value.destinationsCatalog?.items?.map {
            it.image
        } ?: emptyList()
    }

    fun getCityItems(index: Int): List<CityItem> {
        return _homeScreenState.value.destinationsCatalog?.items?.get(index)?.cityList
            ?: emptyList()
    }

    fun filterCityItems(currentIndex: Int, searchQuery: String) {
        viewModelScope.launch {
            val searchPattern = ".*$searchQuery.*"
            val regex = Regex(searchPattern, RegexOption.IGNORE_CASE)
            val filterData = if (searchQuery.isNotBlank()) {
                _homeScreenState.value.destinationsCatalog?.items?.get(currentIndex)?.cityList?.filter {
                    it.name.matches(regex)
                }
            } else {
                getCityItems(currentIndex)
            }

            _filterItemsLiveData.emit(filterData ?: emptyList())
        }
    }

    fun getBottomSheetDetails(currentIndex: Int): BottomSheetDetails {
        return BottomSheetDetails(
            cityItemCount = _homeScreenState.value.destinationsCatalog?.items?.get(currentIndex)?.cityList?.size
                ?: 0,
            topCharacters = getTopCharacters(currentIndex = currentIndex)
        )
    }

    private fun getTopCharacters(
        numberOfCharacter: Int = 3,
        currentIndex: Int
    ): List<Pair<Char, Int>> {
        val cityCharList =
            _homeScreenState.value.destinationsCatalog?.items?.get(currentIndex)?.cityList?.flatMap { cityItem ->
                cityItem.name.toCharArray().toList()
            }
        val characterMaxHeap = PriorityQueue<Pair<Char, Int>>(compareByDescending { it.second })
        val characterCountMap =
            cityCharList?.filter { !it.isWhitespace() }?.groupingBy { it }?.eachCount()
        characterCountMap?.forEach { (char, count) ->
            characterMaxHeap.add(Pair(char, count))
        }
        val topCharacters =
            characterCountMap?.entries?.sortedByDescending { it.value }?.take(numberOfCharacter)
                ?.map {
                    it.toPair()
                } ?: emptyList()
        return topCharacters
    }

}