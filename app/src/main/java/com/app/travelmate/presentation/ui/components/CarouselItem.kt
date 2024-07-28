package com.app.travelmate.presentation.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.travelmate.R


@Composable
fun CarouselItem(@DrawableRes resourceId: Int) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius)))
            .fillMaxSize(),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f),
            painter = painterResource(id = resourceId),
            contentDescription = "Carousel Image",
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
fun PreviewCarouselItem() {
    CarouselItem(resourceId = R.drawable.bangalore)
}