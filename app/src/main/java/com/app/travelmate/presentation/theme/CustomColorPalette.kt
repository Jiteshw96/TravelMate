package com.app.travelmate.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColorsPalette(
    val tertiaryColor: Color = Color.Unspecified,
    val transparent: Color = Color.Unspecified,
    val darkGrey: Color = Color.Unspecified,
    val accentColor: Color = Color.Unspecified,
    val screenBackground: Color = Color.Unspecified
)

val LocalCustomColorPalette = staticCompositionLocalOf { CustomColorsPalette() }

val LightCustomColorsPalette = CustomColorsPalette(
    tertiaryColor = colorTertiary,
    transparent = transparent,
    darkGrey = darkGrey,
    accentColor = colorAccent,
    screenBackground = windowBackground

)
val DarkCustomColorsPalette = CustomColorsPalette(
    tertiaryColor = colorTertiary,
    transparent = transparent,
    darkGrey = darkGrey,
    accentColor = colorAccent,
    screenBackground = windowBackground
)