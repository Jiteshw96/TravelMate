package com.app.travelmate.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = primary,
    secondary = colorPrimaryDark,
)

private val LightColorScheme = lightColorScheme(
    primary = primary,
    secondary = colorPrimaryDark,
)

@Composable
fun TravelMateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }


    val customColorsPalette = if (darkTheme) {
        DarkCustomColorsPalette
    } else {
        LightCustomColorsPalette
    }

    val typography = remember { mutableStateOf(Typography()) }
    typography.value = Typography(
        titleMedium = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp
        ),
        titleSmall = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.5.sp
        ),
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            letterSpacing = 0.5.sp
        ),
        headlineLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            letterSpacing = 0.5.sp
        ),
    )

    val view = LocalView.current
    val statusBarColor = windowBackground.toArgb()
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val insetsController = WindowCompat.getInsetsController(window, view)
            window.statusBarColor = statusBarColor
            window.navigationBarColor = Color.Black.toArgb()
            insetsController.isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalCustomColorPalette provides customColorsPalette
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography.value,
            content = content
        )
    }
}