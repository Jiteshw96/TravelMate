package com.app.travelmate.presentation.ui.components

import androidx.annotation.LayoutRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.travelmate.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeCarousel(@LayoutRes carouselItems: List<Int>, pagerState: PagerState) {
    Column(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.margin_large))) {
        HorizontalPager(state = pagerState) {
            CarouselItem(carouselItems[pagerState.currentPage])
        }
        LazyRow(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.dp_10))
                .align(Alignment.CenterHorizontally),
        ) {
            itemsIndexed(
                items = carouselItems
            ) { index, item ->
                BreadCrumb(isActive = index == pagerState.currentPage)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PreviewHomeCarousel() {
    HomeCarousel(
        carouselItems = listOf(R.drawable.india, R.drawable.usa),
        pagerState = rememberPagerState(initialPage = 0,
            pageCount = {
                2
            })
    )
}