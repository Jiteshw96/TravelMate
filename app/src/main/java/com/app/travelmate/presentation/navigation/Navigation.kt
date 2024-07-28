package com.app.travelmate.presentation.navigation

private const val SLASH = "/"

sealed class Navigation(val destination: String) {
    data object Home : Navigation("home")
}

