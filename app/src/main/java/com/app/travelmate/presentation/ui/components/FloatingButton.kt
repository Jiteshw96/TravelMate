package com.app.travelmate.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.app.travelmate.presentation.theme.LocalCustomColorPalette


@Composable
fun FloatingButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            onClick()
        },
        containerColor = LocalCustomColorPalette.current.accentColor
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "Floating action button.",
            tint = Color.White
        )
    }
}

@Preview
@Composable
fun PreviewFloatingButton() {
    FloatingButton(
        onClick = { }
    )
}