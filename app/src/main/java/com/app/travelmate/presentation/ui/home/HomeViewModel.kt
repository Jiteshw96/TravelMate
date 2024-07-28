package com.app.travelmate.presentation.activity.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.travelmate.R
import com.app.travelmate.domain.common.ResponseState
import com.app.travelmate.domain.exception.APIException
import com.app.travelmate.domain.usecase.GetDestinationCatalogUseCase
import com.app.travelmate.presentation.model.BottomSheetDetails
import com.app.travelmate.presentation.ui.home.HomeScreenState
import com.app.travelmate.presentation.ui.home.getCityItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(private val getDestinationCatalogUseCase: GetDestinationCatalogUseCase) :
    ViewModel() {

    init {
        setInitialState()
    }

    private val initialState by lazy { setInitialState() }
    private val _homeScreenState = MutableStateFlow(initialState)
    val homeScreenState: StateFlow<HomeScreenState?> = _homeScreenState

    var activePage by mutableIntStateOf(0)
    var searchQuery = mutableStateOf("")

    private fun setInitialState() = HomeScreenState(
        isLoading = false,
        destinationsCatalog = null
    )

    fun fetchDestinationData() {
        viewModelScope.launch {
            getDestinationCatalogUseCase()
                .catch { cause ->
                    _homeScreenState.value = _homeScreenState.value.copy(
                        isLoading = false,
                        isError = APIException(genericError = R.string.generic_api_error)
                    )
                }
                .onEach { destinationData ->
                    when (destinationData) {
                        is ResponseState.Loading -> {
                            _homeScreenState.value = _homeScreenState.value.copy(
                                isLoading = true
                            )
                        }

                        is ResponseState.SuccessState -> {
                            _homeScreenState.value = _homeScreenState.value.copy(
                                destinationsCatalog = destinationData.data,
                                isLoading = false
                            )
                        }

                        is ResponseState.ErrorState -> {
                            _homeScreenState.value = _homeScreenState.value.copy(
                                isLoading = false,
                                isError = APIException(genericError = R.string.generic_api_error)
                            )
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    fun filterCityItems(searchQuery: String) {
        viewModelScope.launch {
            val searchPattern = ".*$searchQuery.*"
            val regex = Regex(searchPattern, RegexOption.IGNORE_CASE)
            val filterData = if (searchQuery.isNotBlank()) {
                _homeScreenState.value.getCityItems(activePage)?.filter {
                    it.name.matches(regex)
                }
            } else {
                _homeScreenState.value.getCityItems(activePage)
            }

            _homeScreenState.value = _homeScreenState.value.copy(
                filteredCityList = filterData ?: emptyList()
            )
        }
    }

    fun getBottomSheetDetails(): BottomSheetDetails {
        return BottomSheetDetails(
            cityItemCount = _homeScreenState.value.getCityItems(activePage)?.size ?: 0,
            topCharacters = getTopCharacters()
        )
    }

    private fun getTopCharacters(numberOfCharacter: Int = 3): List<Pair<Char, Int>> {
        val cityCharList = _homeScreenState.value.getCityItems(activePage)?.flatMap { cityItem ->
            cityItem.name.toCharArray().toList()
        }
        val characterCountMap =
            cityCharList?.filter { !it.isWhitespace() }?.groupingBy { it }?.eachCount()
        val topCharacters =
            characterCountMap?.entries?.sortedByDescending { it.value }?.take(numberOfCharacter)
                ?.map {
                    it.toPair()
                } ?: emptyList()
        return topCharacters
    }

}