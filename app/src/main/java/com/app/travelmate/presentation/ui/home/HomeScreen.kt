package com.app.travelmate.presentation.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.travelmate.R
import com.app.travelmate.data.repository.DestinationRepositoryImpl
import com.app.travelmate.domain.usecase.GetDestinationCatalogUseCase
import com.app.travelmate.presentation.activity.home.HomeViewModel
import com.app.travelmate.presentation.theme.LocalCustomColorPalette
import com.app.travelmate.presentation.ui.components.BottomSheet
import com.app.travelmate.presentation.ui.components.CityItemView
import com.app.travelmate.presentation.ui.components.FloatingButton
import com.app.travelmate.presentation.ui.components.HomeCarousel
import com.app.travelmate.presentation.ui.components.ProgressBar
import com.app.travelmate.presentation.ui.components.SearchInputField
import com.app.travelmate.presentation.viewmodelfactory.HomeViewModelFactory


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            GetDestinationCatalogUseCase(DestinationRepositoryImpl())
        )
    )
) {

    val homeScreenState by homeViewModel.homeScreenState.collectAsStateWithLifecycle()

    val modalBottomSheetState = rememberModalBottomSheetState()

    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    val pagerState = rememberPagerState(initialPage = 0,
        pageCount = {
            homeScreenState?.getDestinationCount() ?: 0
        })

    val cityItems by remember {
        derivedStateOf {
            if (homeViewModel.searchQuery.value.isNotEmpty()) {
                homeScreenState?.filteredCityList ?: emptyList()
            } else {
                homeScreenState?.getCityItems(homeViewModel.activePage) ?: emptyList()
            }
        }
    }

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            homeViewModel.activePage = page
            homeViewModel.searchQuery.value = ""
        }
    }


    LaunchedEffect(key1 = Unit) {
        homeViewModel.fetchDestinationData()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingButton {
                showBottomSheet = true
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->

        if (homeScreenState?.destinationsCatalog != null) {
            LazyColumn(
                modifier = Modifier
                    .background(LocalCustomColorPalette.current.screenBackground)
                    .padding(
                        top = padding.calculateTopPadding(),
                        bottom = padding.calculateBottomPadding()
                    )
                    .padding(
                        start = dimensionResource(R.dimen.margin_large),
                        end = dimensionResource(R.dimen.margin_large),
                        top = dimensionResource(R.dimen.margin_large),
                        bottom = dimensionResource(id = R.dimen.dp_0),
                    )
                    .wrapContentHeight()
            ) {
                item {
                    HomeCarousel(
                        carouselItems = homeScreenState?.getCarouselItems()
                            ?: emptyList(),
                        pagerState = pagerState
                    )
                }

                stickyHeader {
                    Surface(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.margin_small))) {
                        SearchInputField(homeViewModel.searchQuery) { queryInput ->
                            homeViewModel.searchQuery.value = queryInput
                            homeViewModel.filterCityItems(queryInput)
                        }
                    }
                }


                itemsIndexed(cityItems) { index, cityItem ->
                    Column(Modifier.padding(vertical = dimensionResource(id = R.dimen.dp_3))) {
                        CityItemView(cityItem)
                    }
                }

                item {
                    Box(
                        modifier = Modifier
                            .padding(bottom = screenHeight * 3 / 4)
                    )
                }
            }
        }

        if (homeScreenState?.isLoading == true) {
            ProgressBar()
        }
    }

    if (showBottomSheet) {
        BottomSheet(
            bottomSheetDetails = homeViewModel.getBottomSheetDetails(),
            onDismiss = {
                showBottomSheet = false
            },
            modalBottomSheetState = modalBottomSheetState
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
